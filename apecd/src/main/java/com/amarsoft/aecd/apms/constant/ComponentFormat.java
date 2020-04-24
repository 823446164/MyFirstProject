package com.amarsoft.aecd.apms.constant;

/**
 * 组件的类型
 * @author xxu1
 */
public enum ComponentFormat {
    SIMPLE_COMPONENT("1","简单组件"),
    COMBINATION_COMPONENT("2","组合组件"),
    DECISION_TABLE("3","决策表"),
    ;
    
    public final String id;
    public final String name;
    
    private ComponentFormat(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return SIMPLE_COMPONENT.id.equals(id)
                ||COMBINATION_COMPONENT.id.equals(id)
                ||DECISION_TABLE.id.equals(id);
    }
}
