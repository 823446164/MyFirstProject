package com.amarsoft.aecd.abfd.constant;

/**
 * 计划启动标识
 * @author sma4
 *
 */
public enum PlanStartSign {
    Startup("startup","启动"),
    UnStart("unStart","不启动"),
    ;
    public final String id;
    public final String name;
    
    private PlanStartSign(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Startup.id.equals(id)
                ||UnStart.id.equals(id);
    }  
}
