/*
 * 文件名：UserRoles.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：用户角色枚举类
 * 修改人：xszhou
 * 修改时间：2020年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.system.constant;

public enum UserRoles {
    Admin("099","系统管理员"),
    DeptManager("110","部门管理员"),
    DeputyManager("120","部门副经理"),
    TeamLeader("210","团队负责人"),
    ;
    
    public String id;
    public String name;
    
    private UserRoles(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Admin.id.equals(id) || DeptManager.id.equals(id)||DeputyManager.id.equals(id)||TeamLeader.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for(UserRoles userRoles : UserRoles.values()) {
            if(userRoles.id.equalsIgnoreCase(id)) {
                return userRoles.name;
            }
        }
        return "";
    }
    
}
