package springbootstarter;

import springbootstarter.validation.StandardDependencyValidationProblem;
import springbootstarter.validation.ValidationProblem;

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
        ValidationProblem problem = model.getProblems().iterator().next();
        assertTrue(problem instanceof StandardDependencyValidationProblem);
        assertEquals(StandardProjectDependency.Docker_Compose_Support, ((StandardDependencyValidationProblem) problem).getDependency());
    }

    public void testDependencyCheck() throws IOException {
        SpringBootProjectModel model = SpringBootProjectModel.initFromWeb();

        model.setParameterValue(StandardProjectParameter.DEPENDENCIES, "docker-compose");
        assertTrue(model.getProblems().isEmpty());

        model.setParameterValue(StandardProjectParameter.DEPENDENCIES, "docker-compose,WRONG_ID");
        assertFalse(model.getProblems().isEmpty());
    }
}
