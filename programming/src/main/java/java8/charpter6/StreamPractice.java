package java8.charpter6;

import java8.charpter4.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamPractice {
    public static void main(String[] args) {
        //Collector收集器提供三大功能
        // 1.将流元素规约和汇总为一个值
        // 2.元素分组
        // 3.元素分区
        //计数
        List<Dish> menu = Dish.dishs();
        long howManyDishes = menu.stream().collect(Collectors.counting());
        long howManyDishes1 = menu.stream().count();
        //查找流中的最大值和最小值
        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        //汇总
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        //求平均数
        double avgCalories = menu.stream().collect(Collectors.averagingLong(Dish::getCalories));
        IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        //连接字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        //广义的规约汇总  Collectors.reducing
        int totalCalories1 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories,
                (i, j) -> i + j));
        Optional<Dish> mostCalorieDish1 = menu.stream().collect(Collectors.reducing(
                (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        //分组
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
        //多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        })));
        //按子组收集数据
        Map<Dish.Type, Long> typesCount = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));

        //将收集器的结果转换为另一种类型
        Map<Dish.Type, Dish> mostCaloricByType1 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)
        ));

        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, Collectors.toSet()
        )));

        //给定指定的Set
        menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT; }, Collectors.toCollection(HashSet::new)
            )));

        //分区
        //可以把分区看作分组一种特殊情况
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));

        Map<Boolean, Dish> mostCaloricPartitionByVegetarian = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));

        //自定义收集器
        List<Dish> dishes11 = menu.stream().collect(ArrayList::new, List::add, List::addAll);
    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    //将数字按质数和非质数分区
    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }
}

enum CaloricLevel { DIET, NORMAL, FAT }