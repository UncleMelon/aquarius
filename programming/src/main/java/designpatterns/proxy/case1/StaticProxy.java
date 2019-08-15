package designpatterns.proxy.case1;

/**
 * 静态代理
 * 由程序员创建代理类或特定工具自动生成源代码再对其编译。在程序运行前代理类的.class文件就已经存在了
 * @author matthew_wu
 * @since 2019-08-14 18:07
 */
public class StaticProxy {
    public static void main(String[] args) {
        IUserDao proxy = new UserDaoProxy();
        proxy.save();
    }
}
