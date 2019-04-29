package java8.charpter3;

import java8.charpter2.Apple;
import scala.Int;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;

public class LambdaFun {
    public static void main(String[] args) {
        //函数接口使用lambda函数 (函数式接口只定义一个抽象方法的接口)
        Runnable r1 = () -> System.out.println("Hello World");
        Predicate<Integer> p = i -> i == 2;
        Comparator<Integer> c = (i1, i2) ->  i1.compareTo(i2);

        //函数描述符 (函数式接口抽象方法)
        process(() -> System.out.println("this is awesome!"));

        //java.util.function函数接口
        //Predict.test  T -> boolean
        Predicate<String> nonEmptyStringPredict = (String s) -> !s.isEmpty();
        //使用方法引用，简化代码
        Predicate<String>  emptyStringPredict = String::isEmpty;

        //Consumer.accept T -> void
        forEach(Arrays.asList(1,2,3,4,5), i -> System.out.println(i));
        //Function.apply T -> R
        List<Integer> l = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
        //Java类型要么是引用类型(比如Byte、Integer、Object、List),要么是原始类型(比如int、double、byte、char)。
        //但是泛型(比如Consumer<T>中的T)只能绑定到引用类型, 这是由泛型内部的实现方式造成的。
        //因此,在Java里有一个将原始类型转换为对应的引用类型的机制。这个机制叫作装箱(boxing)。

        //构造函数引用
        Supplier<Apple> c1 = Apple::new;
        Supplier<Apple> c2 = () -> new Apple();
        Function<Integer, Apple> c3 = Apple::new;
        Function<Integer, Apple> c4 = (weight) -> new Apple(weight);
    }

    public static void process(Runnable r) {
        r.run();
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i: list) {
            c.accept(i);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s: list) {
            result.add(f.apply(s));
        }
        return result;
    }

}



interface Predicate<T> {
    boolean test (T t);
}

interface Comparator<T> {
    int compare(T o1, T o2);
}

interface Runnable {
    void run();
}
