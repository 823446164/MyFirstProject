package com.amarsoft.aecd.acct.constant;
/**
 * 费用收取规则
 * @author jyma1
 */
public enum RateType {
    
    _01("01", "贷款利率"),
    _02("02", "罚息利率"),
    _I01("I01", "资金方贷款利率"),
    _I02("I02", "资金方罚息利率"),
    _P01("P01", "当期还款本金为基础的费率"),
    _P02("P02", "贷款本金余额为基础的费率"),
    _P03("P03", "贷款本金总额为基础的费率"),
    _P04("P04", "当期应付未付款项（不含罚息复利，含本金利息费用）为基础的费率"),
    _SPT("SPT", "利率拆分"),
    ;
    public final String id;
    public final String name;
    
    private RateType(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return _01.id.equals(id) 
                || _02.id.equals(id) 
                || _I01.id.equals(id) 
                || _I02.id.equals(id) 
                || _P01.id.equals(id)
                || _P02.id.equals(id)
                || _P03.id.equals(id)
                || _P04.id.equals(id);
    }
}
