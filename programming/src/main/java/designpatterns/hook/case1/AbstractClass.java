package designpatterns.hook.case1;

/**
 * 只要重写isOpen就能干预父类方法的业务流程。相当于将isOpen挂载在了父类的operating()中。
 * @author matthew_wu
 * @since 2019-08-19 15:48
 */
public abstract class AbstractClass {

    public abstract boolean isOpen();

    public final void operating() {
        if (isOpen()) {
            System.out.println("钩子方法开启");
        } else {
            System.out.println("钩子方法关闭");
        }
    }
}


/*
钩子方法是啥
钩子顾名思义就是用来挂东西的。那么要挂东西必须有个被挂的东西，要不就是铁环、要不就是墙的边沿。
所以要能挂住东西必须要有个被勾住的铁环，要一个钩子。那么在java中也是同样的原理，你首先需要一个被挂在的东西，一个挂载的东西。

钩子的实现方法
是对于抽象方法或者接口中定义的方法的一个空实现，在实际中的应用，比如说有一个接口，这个接口里有7个方法，
而你只想用其中一个方法，那么这时，你可以写一个抽象类实现这个接口，在这个抽象类里将你要用的那个方法设置为abstract,
其它方法进行空实现，然后你再继承这个抽象类，就不需要实现其它不用的方法，这就是钩子方法的作用。
*/
