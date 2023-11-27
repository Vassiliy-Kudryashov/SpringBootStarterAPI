package springbootstarter;

import springbootstarter.validation.StandardDependencyValidationProblem;
import springbootstarter.validation.StandardParameterValidationProblem;
import springbootstarter.validation.ValidationProblem;

import java.io.IOException;
import java.util.Set;

public class StandardProjectParameterTest extends SpringBootTestCase {
    public void testBasic() throws IOException {
        assertEquals("baseDir", StandardProjectParameter.BASE_DIR.toString());
        SpringBootProjectModel model = SpringBootProjectModel.initFromWeb();
        model.setParameterValue(StandardProjectParameter.BOOT_VERSION, "3.2.O");
        Set<ValidationProblem> problems = model.getProblems();
        assertEquals(1, problems.size());
        ValidationProblem problem = problems.iterator().next();
        assertTrue(problem instanceof StandardParameterValidationProblem);
        assertEquals(StandardProjectParameter.BOOT_VERSION, ((StandardParameterValidationProblem) problem).getParameter());
        assertEquals("Invalid Spring Boot version: 3.2.O", problem.toString());

        model.setParameterValue(StandardProjectParameter.BOOT_VERSION, "3.2.0");
        model.setParameterValue(StandardProjectParameter.TYPE, "WRONG");
        assertEquals(1, problems.size());
        problem = problems.iterator().next();
        assertTrue(problem instanceof StandardParameterValidationProblem);
        assertEquals(StandardProjectParameter.TYPE, ((StandardParameterValidationProblem) problem).getParameter());
        assertEquals("Invalid 'type' value: [WRONG]", problem.toString());
    }
}
