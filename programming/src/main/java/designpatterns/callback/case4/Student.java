package designpatterns.callback.case4;

/**
 * 幼师在黑板上写一个式子 “1 + 1 = ”，由小明同学来填空。
 * 由于已经学习了10以内的加法，小明同学可以完全靠自己来计算这个题目
 * @author matthew_wu
 * @since 2019-08-19 17:43
 */
public class Student {

    private String name = null;

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int calcAdd(int a, int b) {
        return a + b;
    }

    private int useCalculator(int a, int b) {
        return new Calculator().add(a, b);
    }

    public void callHelp(int a, int b) {
        new SuperCalculator().add(a, b, new doHomeWork());
    }

    public class doHomeWork implements doJob {
        @Override
        public void fillBlank(int a, int b, int result) {
            System.out.println(name + "求助小红计算:" + a + " + " + b + " = " + result);
        }
    }


}
