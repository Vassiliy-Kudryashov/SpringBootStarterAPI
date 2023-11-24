package springbootstarter;

import java.io.IOException;

public class StandardProjectDependenciesTest extends SpringBootTestCase {
    public void testSpringBootVersions() throws IOException {
        SpringBootProjectModel model = SpringBootProjectModel.initFromWeb();
        model.setParameterValue(StandardProjectParameter.BOOT_VERSION, "2.7.17");
        assertTrue(model.getProblems().isEmpty());
    }

    public void testDependencyCompatibility() throws IOException {
        SpringBootProjectModel model = SpringBootProjectModel.initFromWeb();
        model.setParameterValue(StandardProjectParameter.BOOT_VERSION, "2.7.17");
        model.addDependency(StandardProjectDependency.Docker_Compose_Support);
        assertFalse(model.getProblems().isEmpty());
    }
}
