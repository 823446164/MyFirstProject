package com.amarsoft.aecd.abfd.constant;

/**
 * 计划类型
 * @author sma4
 *
 */
public enum PlanType {
    Actual("actual","轮循"),
    Fixed("fixed","定时"),
    ;
    public final String id;
    public final String name;
    
    private PlanType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Actual.id.equals(id)
                ||Fixed.id.equals(id);
    }  
}
