package springbootstarter.validation;

import springbootstarter.Dictionary;
import springbootstarter.ProjectType;
import springbootstarter.StandardProjectDependency;

import java.util.Arrays;
import java.util.List;

public class Validator {
    public static final List<String> ALLOWED_JAVA_VERSIONS = Arrays.asList("8", "11", "17", "21");
    public static final List<String> ALLOWED_LANGUAGES = Arrays.asList("java", "kotlin", "groovy");
    public static final List<String> ALLOWED_PACKAGING = Arrays.asList("jar", "war");

    public static boolean isValidJavaVersion(String version) {
        return ALLOWED_JAVA_VERSIONS.contains(version);
    }

    public static boolean isValidLanguage(String language) {
        return ALLOWED_LANGUAGES.contains(language);
    }

    public static boolean isValidPackaging(String packaging) {
        return ALLOWED_PACKAGING.contains(packaging);
    }

    public static boolean isValidSpringBootVersion(String version) {
        try {
            new Version(version);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isValidProjectType(String id){
        for (ProjectType value : ProjectType.values()) {
            if (value.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidDependencyList(String dependencies) {
        return Arrays.stream(dependencies.split(",")).noneMatch(id -> StandardProjectDependency.find(id) == null);
    }

    public static boolean isDependencyCompatible(StandardProjectDependency dependency, String springBootVersion) {
        Version bootVersion = new Version(springBootVersion);
        String compatibility = Dictionary.getRawCompatibility(dependency);
        if (compatibility != null) {
            String[] split = compatibility.split(" and ");
            for (String limit : split) {
                if (limit.startsWith(">=") && new Version(limit.substring(2).trim()).compareTo(bootVersion) > 0) {
                    return false;
                }
                if (limit.startsWith("<") && new Version(limit.substring(1).trim()).compareTo(bootVersion) <= 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
