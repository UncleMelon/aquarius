package java8.charpter14;

import java.util.function.DoubleUnaryOperator;

public class StreamPractice {

    public static void main(String[] args) {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
        DoubleUnaryOperator convertUSDToGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        double gbp = convertUSDToGBP.applyAsDouble(1000);

    }


    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }
}
