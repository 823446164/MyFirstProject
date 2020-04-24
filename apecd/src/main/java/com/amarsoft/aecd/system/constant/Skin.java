package com.amarsoft.aecd.system.constant;

/**
 * 前端：皮肤枚举类
 * @author Amarsoft核算团队
 */
public enum Skin {
    Dark("dark","黑色皮肤"),
    Default("default","默认皮肤"),
    Light("light","白色皮肤"),
    Red("red","红色皮肤"),
    Wathet("wathet","浅蓝色皮肤"),
    ;
    public String id;
    public String name;
    
    private Skin(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for (Skin userEventType : Skin.values()) {
            if(userEventType.id.equals(id)) {
                return true;
            }
        }
        return false;
    }    
    /**
     * 获取UserEventType对应的Name
     * @param id
     * @return
     */
    public static String getName(String id) {
        for (Skin userEventType : Skin.values()) {
            if(userEventType.id.equals(id)) {
                return userEventType.name;
            }
        }
        return "";
    }    
}
