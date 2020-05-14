package com.amarsoft.aecd.system.constant;


/**
 * 授权类型
 * @author zcluo
 */
public enum ArchitectureType {
    Department("110","部门"),
    Team("210","团队"),
    ;
    public String id;
    public String name;
    
    private ArchitectureType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(ArchitectureType architectureType : ArchitectureType.values()) {
            if(architectureType.id.equals(id)) {
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
        for (ArchitectureType architectureType : ArchitectureType.values()) {
            if (architectureType.id.equalsIgnoreCase(id)) {
                return architectureType.name;
            }
        }
        return "";
    }
}
