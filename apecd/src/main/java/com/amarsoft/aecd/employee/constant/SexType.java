package com.amarsoft.aecd.employee.constant;
/**
 * 是否正式
 * @author zcluo
 */
public enum SexType {
    Male("1","男"),
    female("2","女"),
    unknown("3","未知"),
    ;
    public String id;
    public String name;
    
    private SexType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Male.id.equals(id)
                ||female.id.equals(id)
                ||unknown.id.equals(id);
    }
}