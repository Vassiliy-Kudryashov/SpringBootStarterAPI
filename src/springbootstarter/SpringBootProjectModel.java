package springbootstarter;

import springbootstarter.util.IOUtil;
import springbootstarter.validation.StandardDependencyValidationProblem;
import springbootstarter.validation.StandardParameterValidationProblem;
import springbootstarter.validation.ValidationProblem;
import springbootstarter.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import static springbootstarter.Dictionary.*;

public class SpringBootProjectModel {
    private static final String[][] HEADERS = {{"Rel", "Description", null}, {"Parameter", "Description", "Default value"}, {"Id", "Description", "Required version"}};


    private final EnumMap<StandardProjectParameter, String> parameters = new EnumMap<>(StandardProjectParameter.class);
    private final Set<ValidationProblem> problems = new HashSet<>();

    private SpringBootProjectModel() {
    }


    public SpringBootProjectModel setParameterValue(StandardProjectParameter parameter, String value) {
        if (value == null || value.equals(defaults.get(parameter))) {
            parameters.remove(parameter);
        } else {
            parameters.put(parameter, value);
        }
        validate();
        return this;
    }

    private void validate() {
        problems.clear();
        String springBootVersion = getParameterValue(StandardProjectParameter.BOOT_VERSION);
        if (!StandardProjectParameter.BOOT_VERSION.isValid(springBootVersion)) {
            problems.add(new StandardParameterValidationProblem(StandardProjectParameter.BOOT_VERSION, "Invalid Spring Boot version: " + springBootVersion));
            return;
        }
        for (Map.Entry<StandardProjectParameter, String> entry : parameters.entrySet()) {
            StandardProjectParameter standardProjectParameter = entry.getKey();
            if (standardProjectParameter == StandardProjectParameter.DEPENDENCIES) continue;
            if (standardProjectParameter == StandardProjectParameter.BOOT_VERSION) continue;
            if (!standardProjectParameter.isValid(entry.getValue())) {
                problems.add(new StandardParameterValidationProblem(standardProjectParameter, "Invalid '" + standardProjectParameter + "' value: [" + entry.getValue()+"]"));
            }
        }
        String dependencyRawValue = getParameterValue(StandardProjectParameter.DEPENDENCIES);
        if (dependencyRawValue != null && !dependencyRawValue.isEmpty() && !StandardProjectParameter.DEPENDENCIES.isValid(dependencyRawValue)) {
            problems.add(new StandardParameterValidationProblem(StandardProjectParameter.DEPENDENCIES, "Dependency list contains unknown ID: " + dependencyRawValue));
            return;
        }

        for (StandardProjectDependency dependency : getDependencies()) {
            if (!Validator.isDependencyCompatible(dependency, springBootVersion)) {
                problems.add(new StandardDependencyValidationProblem(dependency, dependency.getName() + " is not compatible with Spring Boot version " + springBootVersion));
            }
        }
    }

    public Set<ValidationProblem> getProblems() {
        return problems;
    }

    public SpringBootProjectModel addDependency(StandardProjectDependency dependency) {
        Set<String> ids = getDependencyIds();
        if (ids.add(dependency.getId())) {
            storeDependencyIds(ids);
            validate();
        }
        return this;
    }

    public SpringBootProjectModel removeDependency(StandardProjectDependency dependency) {
        Set<String> ids = getDependencyIds();
        if (ids.remove(dependency.getId())) {
            storeDependencyIds(ids);
            validate();
        }
        return this;
    }

    private void storeDependencyIds(Set<String> ids) {
        StringJoiner joiner = new StringJoiner(",");
        ids.forEach(joiner::add);
        setParameterValue(StandardProjectParameter.DEPENDENCIES, joiner.toString());
    }

    private Set<String> getDependencyIds() {
        String dependencies = getParameterValue(StandardProjectParameter.DEPENDENCIES);
        Set<String> ids = new LinkedHashSet<>();
        if (dependencies != null && !dependencies.isEmpty()) {
            ids.addAll(Arrays.asList(dependencies.split(",")));
        }
        return ids;
    }

    public Map<StandardProjectParameter, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public String getParameterValue(StandardProjectParameter parameter) {
        return parameters.containsKey(parameter) ? parameters.get(parameter) : defaults.get(parameter);
    }

    public boolean isValueDefault(StandardProjectParameter parameter) {
        return Objects.equals(getParameterValue(parameter), defaults.get(parameter));
    }

    public Set<StandardProjectDependency> getDependencies() {
        String s = getParameterValue(StandardProjectParameter.DEPENDENCIES);
        if (s == null || s.isEmpty()) {
            return Collections.emptySet();
        }
        EnumSet<StandardProjectDependency> set = EnumSet.noneOf(StandardProjectDependency.class);
        Arrays.stream(s.split(",")).forEach(id -> set.add(StandardProjectDependency.find(id)));
        return Collections.unmodifiableSet(set);
    }

    public SpringBootProjectModel setType(ProjectType type) {
        setParameterValue(StandardProjectParameter.TYPE, type.getId());
        return this;
    }

    public static SpringBootProjectModel initFromWeb() throws IOException {
        SpringBootProjectModel model = new SpringBootProjectModel();
        List<String> lines = IOUtil.readLinesFromSpringIO();
        int stage = 0;//1 for type, 2 for parameters, 3 for dependencies
        String lastKey = null;
        mainLoop:
        for (String line : lines) {
            if (line.startsWith("+") || line.startsWith("|")) {
                String[] words = line.split("\\|");
                if (words.length > 2) {
                    String first = words[1].trim();
                    String second = words[2].trim();
                    String third = words.length > 3 ? words[3].trim() : null;
                    String[] pack = {first, second, third};
                    for (String[] header : HEADERS) {
                        if (Arrays.equals(pack, header)) {
                            stage++;
                            lastKey = null;
                            continue mainLoop;
                        }
                    }

                    if (second.trim().isEmpty()) {
                        lastKey = null;
                        continue;
                    }
                    if (first.isEmpty() && lastKey != null) {
                        first = lastKey;
                    }
                    lastKey = first;
                    switch (stage) {
                        case 1: { // Project type
                            if (first.endsWith(" *")) {
                                first = first.substring(0, first.length() - 2);
                            }
                            String descriptionPart = descriptionDictionary.get(first);
                            descriptionDictionary.put(first, descriptionPart != null ? descriptionPart + " " + second : second);
                            break;
                        }
                        case 2: { // Parameters
                            descriptionDictionary.put(first, second);
                            if ("none".equals(third) || "no base dir".equals(third)) {
                                third = "";
                            }
                            defaults.put(StandardProjectParameter.find(first), third);
                            break;
                        }
                        case 3: { // Dependencies
                            String descriptionPart = descriptionDictionary.get(first);
                            descriptionDictionary.put(first, descriptionPart != null ? descriptionPart + " " + second : second);
                            dependencyCompatibility.putIfAbsent(first, third);
                            break;
                        }
                    }
                }
            }
        }
        return model;
    }

    File downloadProjectFile(File target) throws IOException {
        IOUtil.downloadProjectFile(this, target);
        return target;
    }
}
