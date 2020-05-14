package com.amarsoft.aecd.employee.constant;


/**
 * 是否正式
 * @author zcluo
 */
public enum SexType {
    Male("1","男"),
    Female("2","女"),
    Unknown("3","未知"),
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
                ||Female.id.equals(id)
                ||Unknown.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (SexType sexType : SexType.values()) {
            if (sexType.id.equalsIgnoreCase(id)) {
                return sexType.name;
            }
        }
        return "";
    }
}