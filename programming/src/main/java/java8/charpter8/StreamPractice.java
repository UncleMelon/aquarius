package java8.charpter8;

import java8.charpter4.Dish;

import java.util.List;
import java.util.stream.Collectors;

public class StreamPractice {

    public static void main(String[] args) {
        //从匿名类到Lambda表达式转换
        //传统匿名类
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        //Lambda表达式
        Runnable r2 = () -> System.out.println("Hello");

        //从Lambda表达式到方法引用的转换
        List<Dish> menu = Dish.dishs();
        int totalCalories  = menu.stream().collect(Collectors.summingInt(Dish::getCalories));

        //从命令式的数据处理切换到Stream
        menu.parallelStream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());

        //增加代码灵活性
        //有条件的延迟, 将查询状态封装成一个方法
        //logger.log(Level.WARN, () -> "Problem: " + generateDiagnostic());

        //环绕执行， 重用准备和清理阶段的逻辑
        //.........
    }
}
