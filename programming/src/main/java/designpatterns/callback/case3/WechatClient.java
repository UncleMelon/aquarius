package designpatterns.callback.case3;

/**
 * @author matthew_wu
 * @since 2019-08-19 17:36
 */
public class WechatClient implements ServerInterface {
    private int amount;
    private String account;

    public WechatClient(int amount, String account) {
        this.amount = amount;
        this.account = account;
    }

    @Override
    public void sendMessage(int amount, String account) {
        System.out.println("客户:" + account + "完成账户充值, 金额: " + amount);
    }

    /**
     * 调用服务器端进行充值
     **/
    public void forRecharge() {
        System.out.println("开始调用服务器端进行充值");
        new WechatServer().recharge(amount, account, this);
    }

}
