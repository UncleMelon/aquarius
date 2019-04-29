package java8.charpter8.strategy;

/**
 * @Author matthew_wu
 * @Description 策略模式 Lambda版
 * @Date 21:49 2019-03-26
 * @Param
 * @return
 **/
public interface ValidateStrategy {
    boolean execute(String s);
}
