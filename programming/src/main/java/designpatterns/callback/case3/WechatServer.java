package designpatterns.callback.case3;

/**
 * 服务器端需要完成客户端的调用
 * 并且进行回调   注意这里回调就是指的短信提示
 * 并且服务器端需要提供制定的回调方法，这就意味着所有的客户端都需要实现定义的接口
 * @author matthew_wu
 * @since 2019-08-19 17:38
 */
public class WechatServer {

    public void recharge(int amount, String account, ServerInterface wechatClient) {
        System.out.println("服务器端开始进行充值操作");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("充值操作完成");
        //充值操作完成之后需要调用客户端的回调函数通知客户端
        wechatClient.sendMessage(amount, account);
    }
}
