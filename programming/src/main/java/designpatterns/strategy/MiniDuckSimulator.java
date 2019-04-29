package designpatterns.strategy;

/**
 * IS-A 继承
 * HAS-A 组合
 * 多用组合，少用继承
 * 用组合建立系统，可以用更大的弹性，在运行时动态的改变行为
 * 策略模式定义了算法族，分别封装起来，让它们之间可以互相替换，
 * 此模式让算法的变化独立于使用算法的客户
 * 利用组合(composition)和委托(delegation)可以在 运行时具有继承行为的效果
 **/
public class MiniDuckSimulator {
    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.performQuack();
        mallardDuck.performFly();
        mallardDuck.display();

        ModelDuck modelDuck = new ModelDuck();
        modelDuck.performQuack();
        modelDuck.performFly();
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        modelDuck.performFly();
    }
}
