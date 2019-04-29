package java8.charpter8.observer;

/**
 * @Author matthew_wu
 * @Description 观察者模式
 * @Date 22:07 2019-03-26
 * @Param
 * @return
 **/
public interface Observer {
    void notify(String tweet);
}
