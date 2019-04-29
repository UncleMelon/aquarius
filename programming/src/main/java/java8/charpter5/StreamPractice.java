package java8.charpter5;

import java8.charpter4.Dish;
import scala.Int;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPractice {
    public static void main(String[] args) {
        List<Dish> menu = Dish.dishs();
        //filter
        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        //distinct
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0)
                .distinct().forEach(System.out::println);
        //limit
        List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300)
                .limit(3).collect(Collectors.toList());
        //skip 扔掉前n个元素
        //map 创建一个新版本，而不是去修改原有版本
        menu.stream().filter(d -> d.getCalories() > 300)
                .skip(2).map(Dish::getName).forEach(System.out::println);
        //flatMap
        String[] arrayOfWords = {"GoodBye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        streamOfwords.flatMap(word -> Arrays.stream(word.split("")))
                .distinct().forEach(System.out::print);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        numbers1.stream().flatMap(i -> numbers2.stream()
                .filter(j -> (i + j) % 3 == 0)
                .map(j -> new int[]{i, j}))
                .map(Arrays::toString)
                .forEach(System.out::println);

        //find and match  都用到了短路技术(如&&判断)
        //至少匹配一个
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
        //匹配所有元素
        boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        //没有任何元素匹配
        boolean isHealthy1 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        //返回当前流中的任意元素
        menu.stream().filter(Dish::isVegetarian).findAny()
                .ifPresent(d -> System.out.println(d.getName()));
        //找到第一个元素
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
        //规约
        //sum
        int sum = numbers.stream().reduce(0, Integer::sum);
        //max and min
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        //数字流
        int caloris = menu.stream().mapToInt(Dish::getCalories)
                .sum();
        //转会对象流
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();
        //OptionalInt
        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        int max1 = maxCalories.orElse(1);
        //数字范围
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());
        //勾股数
        //使用boxed IntStream -> Stream(Integer)
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                    IntStream.rangeClosed(a, 100)
                            .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                            .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)})
                );
        //创建流
        //由值创建流
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        Stream<String> empty = Stream.empty();
        //由数组创建
        int[] numbers12 = {2, 3, 5, 7, 11, 13};
        int sum1 = Arrays.stream(numbers12).sum();
        //由文件生成
        //exception写try中 流会自动关闭
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                        .distinct()
                        .count();
        } catch (IOException e) {

        }
        //由函数生成
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        //生成日期
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Stream.iterate(Calendar.getInstance(),
                c -> {
                    c.add(Calendar.DAY_OF_MONTH, 1);
                    return c; })
                .limit(30)
                .forEach(date ->
                System.out.println(sf.format(date.getTime())));
        //斐波拉契数列
        Stream.iterate(new int[]{0, 1},
                x -> new int[]{x[1], x[0] + x[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream.iterate(new int[]{0, 1},
                x -> new int[]{x[1], x[0] + x[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
        //生成
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        IntStream ones = IntStream.generate(() -> 1);
        //匿名类和Lambda的区别在于，匿名类可以通过字段定义状态，可以有副作用
        //而所有的Lambda都是没有副作用的 ****,只有成员变量在堆中才可以保存值，
        //每次labmda函数调用，都是一次栈的调用，不能像成员变量一样保存值的状态
        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };



    }
}
