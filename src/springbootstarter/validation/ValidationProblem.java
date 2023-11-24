package springbootstarter.validation;

public abstract class ValidationProblem {
    abstract String getMessage();

    @Override
    public String toString() {
        return getMessage();
    }
}
