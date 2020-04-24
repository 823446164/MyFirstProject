package com.amarsoft.aecd.acct.constant;
/**
 * 交易对手类型
 * @author xxu1
 */
public enum CounterpartyType {
    Inner_Same_Bank("01", "境内同业-银行"),
    Inner_Same_Not_Bank("02", "境内同业-非银行金融机构"),
    Inner_Not_Same_Org("03", "境内非同业-机构"),
    Inner_Not_Same_Business("04","境内非同业-个体工商户"),
    Inner_Not_Same_Person("05","境内非同业-个人"),
    Ounter("06","境外"),
    Government("07","行政事业单位"),
    ;
    public final String id;
    public final String name;
    
    private CounterpartyType(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Inner_Same_Bank.id.equals(id) 
                || Inner_Same_Not_Bank.id.equals(id)
                || Inner_Not_Same_Org.id.equals(id)
                || Inner_Not_Same_Business.id.equals(id)
                || Inner_Not_Same_Person.id.equals(id)
                || Ounter.id.equals(id)
                || Government.id.equals(id);
    }
}
