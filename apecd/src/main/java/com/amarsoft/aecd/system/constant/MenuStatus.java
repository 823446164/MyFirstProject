/*
 * 文件名：MenuStatus.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：菜单状态枚举类
 * 修改人：jcli2
 * 修改时间：2020年5月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.aecd.system.constant;

/**
 * 菜单状态
 * @author jcli2
 * 
 */
public enum MenuStatus {
    
    start("1","启用"),
    stop("2","停用"),
    New("0","新增"),
    ;
    
    public final String id;
    public final String name;
    
    private MenuStatus(String id, String name) {
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
        return start.id.equals(id) || stop.id.equals(id) || New.id.equals(id);
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (MenuStatus menuStatus : MenuStatus.values()) {
            if (menuStatus.id.equalsIgnoreCase(id)) {
                return menuStatus.name;
            }
        }
        return "";
    }
    

}