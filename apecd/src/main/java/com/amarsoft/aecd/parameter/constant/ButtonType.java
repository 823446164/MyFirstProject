/*
 * 文件名：ButtonType.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.parameter.constant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yrong
 * @version 2020年5月15日
 * @see ButtonType
 * @since
 */

public enum ButtonType {
    _1("1","add"),
    _2("2","edit"),
    _3("3","copy"),
    ;
    
    public final String id;
    public final String name;
    
    private ButtonType(String id, String name) {
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
        return _1.id.equals(id) 
            || _2.id.equals(id) 
            || _3.id.equals(id);
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (ButtonType buttonType : ButtonType.values()) {
            if (buttonType.id.equalsIgnoreCase(id)) {
                return buttonType.name;
            }
        }
        return "";
    }
}
