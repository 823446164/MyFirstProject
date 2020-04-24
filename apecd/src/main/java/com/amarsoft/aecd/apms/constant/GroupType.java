package com.amarsoft.aecd.apms.constant;

public enum GroupType {
    COMMON("0","方案组件"),
    COMPONENT("1","基础组件"),
    ;
    public String id;
    public String name;
    
    private GroupType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return COMMON.id.equals(id)
                ||COMPONENT.id.equals(id);
    }   
}
