package springbootstarter;

import springbootstarter.util.IOUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class SpringBootProjectModel {
    private static final String[][] HEADERS = {{"Rel", "Description", null}, {"Parameter", "Description", "Default value"}, {"Id", "Description", "Required version"}};

    private final Map<String, String> descriptionDictionary = new HashMap<>();
    private final EnumMap<StandardProjectParameter, String> defaults = new EnumMap<>(StandardProjectParameter.class);
    private final Map<String, String> dependencyCompatibility = new LinkedHashMap<>();

    private final EnumMap<StandardProjectParameter, String> parameters = new EnumMap<>(StandardProjectParameter.class);

    private SpringBootProjectModel() {
    }

    //Auxiliary method
    public Set<String> getDependencyKeys() {
        return Collections.unmodifiableSet(dependencyCompatibility.keySet());
    }

    //Common description dictionary
    public String getDescription(String id) {
        return descriptionDictionary.get(id);
    }

    public void setParameterValue(StandardProjectParameter parameter, String value) {
        if (value != null && !parameter.validate(value)) {
            throw new IllegalArgumentException("'" + value + "' is invalid value for " + parameter);
        }
        if (value == null || value.equals(defaults.get(parameter))) {
            parameters.remove(parameter);
        } else {
            parameters.put(parameter, value);
        }
        //todo check if bootversion is compatible with desired dependencies, maybe add explicit validate() method
    }

    public boolean addDependency(StandardProjectDependency dependency) {
        String dependencies = getParameterValue(StandardProjectParameter.DEPENDENCIES);
        Set<String> ids = new LinkedHashSet<>();
        if (dependencies != null && !dependencies.isEmpty()) {
            ids.addAll(Arrays.asList(dependencies.split(",")));
        }
        if (!ids.add(dependency.getId())) {
            return false;
        }
        StringJoiner joiner = new StringJoiner(",");
        ids.forEach(joiner::add);
        setParameterValue(StandardProjectParameter.DEPENDENCIES, joiner.toString());
        return true;//todo return compatibility check
    }

    public Map<StandardProjectParameter, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public String getParameterValue(StandardProjectParameter parameter) {
        return parameters.containsKey(parameter) ? parameters.get(parameter) : defaults.get(parameter);
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

    public void setType(ProjectType type) {
        setParameterValue(StandardProjectParameter.TYPE, type.getId());
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
                            String descriptionPart = model.descriptionDictionary.get(first);
                            model.descriptionDictionary.put(first, descriptionPart != null ? descriptionPart + " " + second : second);
                            break;
                        }
                        case 2: { // Parameters
                            model.descriptionDictionary.put(first, second);
                            if ("none".equals(third) || "no base dir".equals(third)) {
                                third = "";
                            }
                            model.defaults.put(StandardProjectParameter.find(first), third);
                            break;
                        }
                        case 3: { // Dependencies
                            String descriptionPart = model.descriptionDictionary.get(first);
                            model.descriptionDictionary.put(first, descriptionPart != null ? descriptionPart + " " + second : second);
                            model.dependencyCompatibility.put(first, third);
                            break;
                        }
                    }
                }
            }
        }
        return model;
    }

    void downloadProjectFile(File target) throws IOException {
        IOUtil.downloadProjectFile(this, target);
    }
}
