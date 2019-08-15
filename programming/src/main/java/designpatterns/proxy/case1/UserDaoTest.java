package designpatterns.proxy.case1;

import java.lang.reflect.Proxy;

/**
 * @author matthew_wu
 * @since 2019-08-14 18:11
 */
public class UserDaoTest {
    public static void main(String[] args) {
        IUserDao pDao = new UserDao();
        UserDaoHandler handler = new UserDaoHandler(pDao);
        IUserDao proxy = (IUserDao) Proxy.newProxyInstance(pDao.getClass().getClassLoader(), pDao.getClass().getInterfaces(), handler);
        proxy.save();
    }
}

/*
由此总结一下动态代理实现过程

    1.通过 Proxy.newProxyInstance() 方法，返回一个继承Proxy类，并且最终实现了指定接口的代理类的实例。

     在这里分为两个大的步骤:

     1) 通过getProxyClass0()方法生成代理类。在这个方法里面,
     ProxyGenerator.generateProxyClass()的方法是最终生成代理类的字节码.
     defineClass0()方法把相应的字节码生成代理类
     2) 调用 newInstance(Constructor<?> cons, InvocationHandler h)方法 创建一个新的代理类实例，创建对象时，传入我们定义好的代理处理类，InvocationHandle类。

    2.调用新实例的方法，即此例中的add(), update()方法，即原InvocationHandler类中的invoke()方法。

动态代理的优点

    1) 最直观的，类少了很多
    2) 代理内容也就是InvocationHandler接口的实现类可以复用，可以给A接口用、也可以给B接口用，A接口用了InvocationHandler接口实现类A的代理，不想用了，可以方便地换成InvocationHandler接口实现B的代理
    3)最重要的，用了动态代理，就可以在不修改原来代码的基础上，就在原来代码的基础上做操作，这就是AOP即面向切面编程

动态代理的缺点

    动态代理的缺点，就是前面我们读源代码的时候遇到的。它只能针对接口生成代理，不能只针对某一个类生成代理。
    如果需要为某一个单独的类实现一个代理的话，考虑使用CGLIB等第三方字节码（一种字节码增强技术）。
*/



/*
1，什么是代理模式？
代理模式的作用是：为其他对象提供一种代理以控制对这个对象的访问。
2，代理模式有什么好处？
在某些情况下，一个客户不想或者不能直接引用另一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用。
3，代理模式一般涉及到的角色有：
抽象角色：声明真实对象和代理对象的共同接口,这样一来在任何可以使用目标对象的地方都可以使用代理对象。
代理角色：代理对象内部含有目标对象的引用，从而可以在任何时候操作目标对象；代理对象提供一个与目标对象相同的接口，以便可以在任何时候替*代目标对象。代理对象通常在客户端调用传递给目标对象之前或之后，执行某个操作，而不是单纯地将调用传递给目标对象，同时，代理对象可以在执行真实对象操作时，附加其他的操作，相当于对真实对象进行封装。
4，静态代理：
由程序员创建代理类或特定工具自动生成源代码再对其编译。在程序运行前代理类的.class文件就已经存在了
5，动态代理：
需要实现业务接口Interface、业务实现类target、业务处理类Handler、JVM在内存中生成的动态代理类$Proxy0
*/

