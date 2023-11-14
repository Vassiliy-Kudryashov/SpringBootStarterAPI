package springbootstarter;

public enum StandardProjectParameters {
    APPLICATION_NAME("applicationName"),
    ARTIFACT_ID("artifactId"),
    BASE_DIR("baseDir"),
    BOOT_VERSION("bootVersion"),
    DEPENDENCIES("dependencies"),
    DESCRIPTION("description"),
    GROUP_ID("groupId"),
    JAVA_VERSION("javaVersion"),
    LANGAGE("language"),
    NAME("name"),
    PACKAGE_NAME("packageName"),
    PACKAGING("packaging"),
    TYPE("type"),
    VERSION("version");

    private final String name;

    StandardProjectParameters(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    static StandardProjectParameters find(String name) {
        for (StandardProjectParameters parameter : values()) {
            if (parameter.name.equals(name)) {
                return parameter;
            }
        }
        throw new IllegalArgumentException("No enum constant found for name " + name);
    }
}
