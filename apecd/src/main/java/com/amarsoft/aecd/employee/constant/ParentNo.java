/*
 * 文件名：ParentNo.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.employee.constant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yrong
 * @version 2020年5月15日
 * @see ParentNo
 * @since
 */

public enum ParentNo {
    _1("1","root"),
    ;
    
    public final String id;
    public final String name;
    
    private ParentNo(String id, String name) {
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
        return _1.id.equals(id) ;
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (ParentNo parentNo : ParentNo.values()) {
            if (parentNo.id.equalsIgnoreCase(id)) {
                return parentNo.name;
            }
        }
        return "";
    }


}
