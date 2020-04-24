package com.amarsoft.aecd.apms.constant;

public enum ComponentType {
    PARAMETER_GROUP("0","参数组"),
    COMBINATION_COMPONENT("1","组合组件"),
    DECISION_TABLE("2", "决策表"),
    ;
    public String id;
    public String name;
    
    ComponentType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return PARAMETER_GROUP.id.equals(id)
                ||COMBINATION_COMPONENT.id.equals(id)
                ||DECISION_TABLE.id.equals(id);
    }   
}
