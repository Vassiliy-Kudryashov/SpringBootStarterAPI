package springbootstarter;

public enum ProjectType {
    GRADLE("gradle-build", "Gradle"),
    GRADLE_GROOVY("gradle-project", "Gradle - Groovy"),
    GRADLE_KOTLIN("gradle-project-kotlin", "Gradle - Kotlin"),
    MAVEN_POM("maven-build", "Maven pom.xml"),
    MAVEN_ARCHIVE("maven-project", "Maven archive");

    private final String id;
    private final String name;

    ProjectType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    static ProjectType find(String id) {
        for (ProjectType value : values()) {
            if (value.id.equals(id)) {
                return value;
            }
        }
        return ProjectType.GRADLE_GROOVY;// fallback to hardcoded default
    }

    @Override
    public String toString() {
        return name;
    }
}
