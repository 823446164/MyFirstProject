/**
 * PLanSearchType.java
 * jjyue
 * 2019年7月30日
 */
package com.amarsoft.aecd.abfd.constant;

/**
 * @author jjyue
 * 2019年7月30日
 */
public enum PLanSearchType {

    planNo("planNo","计划编号"),
    planName("planName","计划名称"),
    status("status","计划状态"),
    type("planType","计划状态"),
    ;
    public final String id;
    public final String name;
    
    private PLanSearchType(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return planNo.id.equals(id) 
                || planName.id.equals(id)
                || status.id.equals(id) 
                || type.id.equals(id);
    }
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for(PLanSearchType pLanSearchType : PLanSearchType.values()) {
            if(pLanSearchType.id.equalsIgnoreCase(id)) {
                return pLanSearchType.name;
            }
        }
        return "";
    }
}
