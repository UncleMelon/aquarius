package java8.charpter4;

import java.util.List;
import java.util.stream.Collectors;

public class StreamPractice {
    public static void main(String[] args) {
        //流是Java API的新成员，它允许你以声明性方式处理数据集合(通过查询语句来表达，而不是临时编写一个实现)，可以看成遍历数据集的高级迭代器
        //可以透明地并行处理数据
        //流只能便利一次
        //流是内部迭代

        List<Dish> menu = Dish.dishs();

        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);

        //这个例子表示stream中间操作一般都可以合并起来，在终端操作时一次性全部处理
        //终端操作: forEach count collect
        List<String> names = menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);

        long count = menu.stream().count();
        System.out.println(count);
    }
}
