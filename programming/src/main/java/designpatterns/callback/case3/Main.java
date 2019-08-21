package designpatterns.callback.case3;

/**
 * @author matthew_wu
 * @since 2019-08-19 17:41
 */
public class Main {
    public static void main(String[] args) {
        int amount = 100;
        String account = "123123";
        WechatClient wechatClient = new WechatClient(amount, account);
        wechatClient.forRecharge();
    }
}
