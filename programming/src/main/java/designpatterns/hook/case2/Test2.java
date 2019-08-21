package designpatterns.hook.case2;

/**
 * 模板方法模式：在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中的某些步骤。
 * 【钩子方法】：原理就是实现为空的方法，在某任务之前、之后、执行中、报异常后调用的方法（是不是有种熟悉的感觉）。通常钩子方法是通过抽象类或是本类中的空方法来实现的。
 *
 * 这里将利用模板方法模式来说明钩子方法是什么，许多设计模式都用到了回调，钩子之类的概念，这些基础点理解了，有些模式也就不那么晦涩难懂了
 * 业务处理类，老王去打饭
 * @author matthew_wu
 * @since 2019-08-19 16:33
 */
public class Test2 {
    public static void main(String[] args) {
        Restaurant1 waiter = new Restaurant1();
        waiter.dozenRice(new Client1() {
            @Override
            protected void before() {
                System.out.println("对服务员吹胡子瞪眼！！");
            }

            @Override
            public void appetite() {
                System.out.println("盛了一锅米饭");
            }
        });
    }
}


class Restaurant1 {

    /**
     * 打饭方法，前提是客户要告知服务员你的饭量，他会根据你的饭量给你“盛”饭
     * @param client 排队的客户
     * @return
     */
    public void dozenRice(Client1 client) {
        client.templateMethod();
    }
}

abstract class Client1 {

    /**
     * 模版方法
     **/
    public void templateMethod() {
        before();
        appetite();
        after();
    }

    /**
     * 【钩子方法】在盛饭前（一个空的实现）
     **/
    protected void before() {}

    /**
     * 【抽象方法】告诉服务员其饭量
     * @return 饭量
     */
    public abstract void appetite();

    /**
     * 【具体方法】盛饭后
     */
    private void after() {
        //实际项目这里是共有的业务逻辑
        System.out.println("拿筷子，找桌子，开吃...");
    }
}