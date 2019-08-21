package designpatterns.hook.case2;

/**
 * 通过一个小需求来实现回调，需求为：王钢蛋去餐厅打饭，餐厅服务员根据其饭量为其打饭。我们先构思下，首先要有个接口，接口中定义个抽象方法为 饭量（回调接口）；
 * 其次创建王钢蛋（回调类），让王钢蛋实现这个接口，并想好其饭量（回调方法）；
 * 最后创建餐厅，餐厅服务员（另一方）接待王钢蛋（回调对象）并根据其提供的饭量（回调方法）打饭（特定事件，该事件会调用回调方法）
 * @author matthew_wu
 * @since 2019-08-19 16:25
 */
public class Test1 {
    public static void main(String[] args) {
        WangGangDan laoWang = new WangGangDan();
        Restaurant waiter = new Restaurant();
        String dozenRice = waiter.dozenRice(laoWang);
        //最后老王"盛了一车米饭"
        System.out.println(dozenRice);
        //但是通常我们打饭时不会告诉服务员我们叫什么，这样太麻烦了，那么可不可以只告诉服务员
        //饭量多少就给我们打饭呢？按常理来说我们去餐厅也只会要一次饭。
        //匿名内部类多用来实现回调，简便
        String dozenRice1 = waiter.dozenRice(new Client() {
            @Override
            public String appetite() {
                return "一锅米饭";
            }
        });
        System.out.println(dozenRice1);
    }
}

interface Client {

    /**
     * 告诉服务员其饭量
     * @return java.lang.String
     **/
    String appetite();
}

class WangGangDan implements Client {

    @Override
    public String appetite() {
        return "一车米饭";
    }
}

/**
 * 食堂
 **/
class Restaurant {

    /**
     * 打饭方法，前提是客户要告知服务员你的饭量，他会根据你的饭量给你“盛”饭
     * @param client 排队的客户
     * @return
     */
    public String dozenRice(Client client) {
        return "盛了" + client.appetite();
    }
}

