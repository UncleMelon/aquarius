package java8.charpter8.chain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);

        //Lambda
        UnaryOperator<String> headerProcessing =
                s -> "From Raoul, Mario and Alan: " + s;
        UnaryOperator<String> spellCheckerProcessing =
                s -> s.replaceAll("labda", "lambda");
        Function<String, String> pipline = headerProcessing.andThen(spellCheckerProcessing);
        pipline.apply("Aren't labdas really sexy?!!");
    }
}
