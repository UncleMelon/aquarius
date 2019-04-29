package java8.charpter8.strategy;

public class Main {
    public static void main(String[] args) {
        Validator numericValidator = new Validator(s -> s.matches("[a-z]+"));
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(s -> s.matches("\\d+"));
        boolean b2 = lowerCaseValidator.validate("bbbb");
    }
}
