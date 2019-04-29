package java8.charpter8.strategy;

public class Validator {
    private final ValidateStrategy strategy;

    public Validator(ValidateStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
