package com.amarsoft.aecd.acct.constant;

/**
 * 利率单位
 * @author xjzhao
 */

public enum RateUnit {
     
    Day("03","日利率"),
    Month("02","月利率"),
    Year("01","年利率")
    ;
    
    public final String id;
    public final String name;
    
    private RateUnit(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Day.id.equals(id) 
                || Month.id.equals(id) 
                || Year.id.equals(id);
    }
}
