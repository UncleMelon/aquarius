package java8.charpter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AppleFunc {

    public static void main(String[] args) {
        Apple apple1 = new Apple("green", 200, 100.00);
        Apple apple2 = new Apple("red", 80, 100.00);
        List<Apple> apples = Arrays.asList(apple1, apple2);


        System.out.println("抽象条件进行筛选");
        prettyPrintApple(apples, new AppleFancyFormatter());
        System.out.println("##################");
        prettyPrintApple(apples, new AppleSimpleFormatter());

        System.out.println("##行为参数化进行筛选");
        prettyPrintApple(apples, (Apple apple) -> "An apple of " + apple.getWeight() + "g");

        System.out.println("##List类型抽象化");
        List<Apple> redApples =
                filter(apples, (Apple apple) -> "red".equals(apple.getColor()));
        List<Integer> evenNumbers =
                filter(Arrays.asList(1,2,3,4,5), (Integer i) -> i % 2 == 0);

        //排序
        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        apples.sort(Comparator.comparing(Apple::getWeight));
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for(Apple apple: inventory) {
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e: list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}

//List类型抽象化
interface Predicate<T> {
    boolean test(T t);
}


//策略模式
interface AppleFormatter {
    String accept(Apple apple);
}

class AppleFancyFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}

class AppleSimpleFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}