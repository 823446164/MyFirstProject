package com.amarsoft.aecd.acct.constant;
/**
 * 账户类型
 * @author jyma1
 */
public enum AccountType {
    
    DebitCard("0", "借记卡"),
    CreditCard("1", "信用卡"),
    DepositBook("2", "折"),
    AliPay("3","支付宝"),
    NetBank("4","网商银行"),
    ;
    public final String id;
    public final String name;
    
    private AccountType(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return DebitCard.id.equals(id) 
                || CreditCard.id.equals(id)
                || AliPay.id.equals(id)
                || NetBank.id.equals(id)
                || DepositBook.id.equals(id);
    }
}
