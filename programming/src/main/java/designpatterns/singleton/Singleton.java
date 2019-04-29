package designpatterns.singleton;

/**
 * @Author matthew_wu
 * Lazy Loading
 **/
public class Singleton {

    /**
     * 用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最的值
     **/
    private volatile static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        //在第一次延迟新建对象后，防止线程进入同步区
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                //第一次如果多个线程进入同步区，防止后面的线程重复创建单例
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
