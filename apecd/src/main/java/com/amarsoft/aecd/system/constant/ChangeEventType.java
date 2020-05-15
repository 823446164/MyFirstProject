package com.amarsoft.aecd.system.constant;

/**
 * 机构级别
 * @author zcluo
 */
public enum ChangeEventType {
    Add("ADD","新增"),
    Delete("DELETE","删除"),
    Update("UPDATE","修改"),
    ;
    public String id;
    public String name;
    
    private ChangeEventType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(ChangeEventType changeEventType : ChangeEventType.values()) {
            if(changeEventType.id.equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (ChangeEventType changeEventType : ChangeEventType.values()) {
            if (changeEventType.id.equalsIgnoreCase(id)) {
                return changeEventType.name;
            }
        }
        return "";
    }
}
