package java8.charpter4;

import java.util.Arrays;
import java.util.List;

public class StreamExample {
    public static void main(String[] args) {
        //流是Java API的新成员，它允许你以声明性方式处理数据集合(通过查询语句来表达，而不是临时编写一个实现)，可以看成遍历数据集的高级迭代器
        //可以透明地并行处理数据

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );






    }
}
