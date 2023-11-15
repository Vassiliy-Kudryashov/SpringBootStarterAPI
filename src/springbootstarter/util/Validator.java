package springbootstarter.util;

import springbootstarter.ProjectType;
import springbootstarter.StandardProjectDependency;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Validator {
    public static final List<String> ALLOWED_JAVA_VERSIONS = Arrays.asList("8", "11", "17", "21");
    public static final List<String> ALLOWED_LANGUAGES = Arrays.asList("java", "kotlin", "groovy");
    public static final List<String> ALLOWED_PACKAGING = Arrays.asList("jar", "war");
    public static final List<String> ALLOWED_SPRING_BOOT_VERSIONS = Arrays.asList(
            "3.2.0 (SNAPSHOT)","3.2.0 (RC2)","3.1.6 (SNAPSHOT)","3.1.5","3.0.13 (SNAPSHOT)","3.0.12","2.7.18 (SNAPSHOT)","2.7.17");

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
        return ALLOWED_SPRING_BOOT_VERSIONS.contains(version);
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
}
