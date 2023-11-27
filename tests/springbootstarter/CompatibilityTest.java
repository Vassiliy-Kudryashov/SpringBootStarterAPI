package springbootstarter;

import java.io.IOException;

public class CompatibilityTest extends SpringBootTestCase {
    public void testVersions() throws IOException {
        SpringBootProjectModel model = SpringBootProjectModel.initFromWeb();
        model.setParameterValue(StandardProjectParameter.BOOT_VERSION, "3.2.0");
        model.addDependency(StandardProjectDependency.Embedded_MongoDB_Database); // >=2.6.0 and <3.0.0-M1
        assert (!model.getProblems().isEmpty());
        model.getProblems().forEach(System.out::println);

        model.setParameterValue(StandardProjectParameter.BOOT_VERSION, "2.7.18 (SNAPSHOT)");
        assert (model.getProblems().isEmpty());

        model.addDependency(StandardProjectDependency.Hilla); // >=3.1.0-M1 and <3.2.0-M1
        assert (!model.getProblems().isEmpty());
        model.getProblems().forEach(System.out::println);

        model.removeDependency(StandardProjectDependency.Embedded_MongoDB_Database);
        model.setParameterValue(StandardProjectParameter.BOOT_VERSION, "3.1.0-M1");
        assert (model.getProblems().isEmpty());

        model.setParameterValue(StandardProjectParameter.BOOT_VERSION, "3.2.0-M1");
        assert (!model.getProblems().isEmpty());
        model.getProblems().forEach(System.out::println);

        model.setType(ProjectType.MAVEN_ARCHIVE);
    }
}
