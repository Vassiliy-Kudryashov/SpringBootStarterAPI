package springbootstarter;

import springbootstarter.util.Validator;

import java.util.function.Function;

public enum StandardProjectParameter {
    APPLICATION_NAME("applicationName"),
    ARTIFACT_ID("artifactId"),
    BASE_DIR("baseDir"),
    BOOT_VERSION("bootVersion", Validator::isValidSpringBootVersion),
    DEPENDENCIES("dependencies", Validator::isValidDependencyList),
    DESCRIPTION("description"),
    GROUP_ID("groupId"),
    JAVA_VERSION("javaVersion", Validator::isValidJavaVersion),
    LANGAGE("language", Validator::isValidLanguage),
    NAME("name"),
    PACKAGE_NAME("packageName"),
    PACKAGING("packaging", Validator::isValidPackaging),
    TYPE("type", Validator::isValidProjectType),
    VERSION("version");

    private final String id;
    private final Function<String, Boolean> validator;

    StandardProjectParameter(String id) {
        this(id, null);
    }

    StandardProjectParameter(String id, Function<String, Boolean> validator) {
        this.id = id;
        this.validator = validator;
    }

    boolean validate(String value) {
        return validator == null || validator.apply(value);
    }

    @Override
    public String toString() {
        return id;
    }

    static StandardProjectParameter find(String name) {
        for (StandardProjectParameter parameter : values()) {
            if (parameter.id.equals(name)) {
                return parameter;
            }
        }
        throw new IllegalArgumentException("No enum constant found for name " + name);
    }
}
