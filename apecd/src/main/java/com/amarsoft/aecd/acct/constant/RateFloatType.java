package com.amarsoft.aecd.acct.constant;

/**
 * 浮动幅度类型
 * @author xjzhao
 */
public enum RateFloatType {
    
    Ratio("0","浮动比（百分之）"),
    Point("1","浮动点（百分之）"),
    ;
    
    public final String id;
    public final String name;
    
    private RateFloatType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Ratio.id.equals(id) 
                || Point.id.equals(id);
    }
}
