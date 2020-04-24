package com.amarsoft.aecd.system.constant;

/**
 * 前端：布局枚举类
 * @author Amarsoft核算团队
 */
public enum Layout {
    Side("side","左右布局"),
    Top("top","上下布局"),
    One("one","上下-左右布局"),
    ;
    public String id;
    public String name;
    
    private Layout(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for (Layout userEventType : Layout.values()) {
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
        for (Layout userEventType : Layout.values()) {
            if(userEventType.id.equals(id)) {
                return userEventType.name;
            }
        }
        return "";
    }    
}
