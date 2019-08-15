package designpatterns.proxy.case2;

import java.lang.reflect.Proxy;

/**
 * java dynamic proxy
 * @author matthew_wu
 * @since 2019-08-15 14:36
 */
public class JavaDynamicProxy {
    public static void main(String[] args) {
        JavaDeveloper zack = new JavaDeveloper("Zack");

        Developer zackProxy = (Developer) Proxy.newProxyInstance(zack.getClass().getClassLoader(), zack.getClass().getInterfaces(), ((proxy, method, args1) -> {
            if (method.getName().equals("code")) {
                System.out.println("Zack is praying for the code!");
                return method.invoke(zack, args);
            }
            if (method.getName().equals("debug")) {
                System.out.println("Zack's have no bug! No need to debug");
                return null;
            }
            return null;
        }));

        zackProxy.code();
        zackProxy.debug();
    }
}


/*
动态代理的好处我们从例子就能看出来，它比较灵活，可以在运行的时候才切入改变类的方法，而不需要预先定义它。
动态代理一般我们比较少去手写，但我们用得其实非常多。在Spring项目中用的注解，例如依赖注入的@Bean、@Autowired，
事务注解@Transactional等都有用到，换言之就是Srping的AOP（切面编程）。
这种场景的使用是动态代理最佳的落地点，可以非常灵活地在某个类，某个方法，某个代码点上切入我们想要的内容，
就是动态代理其中的内容。所以下一篇我们来细致了解下Spring的AOP到底是怎么使用动态代理的。

作者：公众号_Zack说码
链接：https://www.jianshu.com/p/95970b089360
*/
