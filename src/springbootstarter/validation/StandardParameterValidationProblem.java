package springbootstarter.validation;

import springbootstarter.StandardProjectParameter;

public class StandardParameterValidationProblem extends ValidationProblem {
    private final StandardProjectParameter parameter;
    private final String message;

    public StandardParameterValidationProblem(StandardProjectParameter parameter, String message) {
        this.parameter = parameter;
        this.message = message;
    }

    public StandardProjectParameter getParameter() {
        return parameter;
    }

    @Override
    String getMessage() {
        return message;
    }
}
