package springbootstarter.validation;

import springbootstarter.StandardProjectDependency;

public class StandardDependencyValidationProblem extends ValidationProblem {
    private final StandardProjectDependency dependency;
    private final String message;

    public StandardDependencyValidationProblem(StandardProjectDependency dependency, String message) {
        this.dependency = dependency;
        this.message = message;
    }

    public StandardProjectDependency getDependency() {
        return dependency;
    }

    @Override
    String getMessage() {
        return message;
    }
}
