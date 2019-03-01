package java8.charpter3;

import java8.charpter2.Apple;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;

import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExample {
    public static void main(String[] args) {
        //复合Lambda表达式
        Apple apple1 = new Apple("green", 200, 100.00);
        Apple apple2 = new Apple("red", 80, 100.00);
        //1.逆序
        List<Apple> inventory = Arrays.asList(apple1, apple2);
        inventory.sort(comparing(Apple::getWeight).reversed());
        //2.比较器链
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
        //3.谓词复合
        Predicate<Apple> redApple = (Apple apple) -> "red".equals(apple.getColor());
        Predicate<Apple> notRedApple = redApple.negate();
        //a.or(b).and(c)  (a || b) && c
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150).or(a -> "green".equals(a.getColor()));

        //函数复合
        // g(f(x))
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        //f(g(x))
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> g1 = x -> x * 2;
        Function<Integer, Integer> h1 = f1.compose(g1);
        int result1 = h1.apply(1);
    }
}
