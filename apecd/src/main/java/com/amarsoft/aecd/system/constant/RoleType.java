/*
 * 文件名：RoleType.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：hpli
 * 修改时间：2020年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.system.constant;

/**
 * @author hpli
 * @version 2020年5月14日
 * @see RoleType
 * @since
 */

public enum RoleType {
   roleA("1","A角"),
    roleB("2","B角"),
    roleC("3","C角"),
    ;
    public String id;
    public String name;
    
    private RoleType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(RoleType role : RoleType.values()) {
            if(role.id.equals(id)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for(RoleType roleType : RoleType.values()) {
            if(roleType.id.equalsIgnoreCase(id)) {
                return roleType.name;
            }
        }
        
        return "";
    }

}
