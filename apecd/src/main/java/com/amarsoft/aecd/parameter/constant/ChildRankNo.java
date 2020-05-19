/*
 * 文件名：ChildRankNo.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.parameter.constant;

/**
 * 〈为子职级建立枚举类〉
 * 〈T1-1....M6-3〉
 * @author xphe
 * @version 2020年5月14日
 * @see ChildRankNo
 * @since
 */

public enum ChildRankNo {
    B1_1("11","T1-1最低"),
    B1_2("12","T1-2"),
    B1_3("13","T1-3"),
    B2_1("21","T2-1"),
    B2_2("22","T2-2"),
    B2_3("23","T2-3"),
    B3_1("31","T3-1"),
    B3_2("32","T3-2"),
    B3_3("33","T3-3"),
    B4_1("41","T4-1"),
    B4_2("42","T4-2"),
    B4_3("43","T4-3"),
    B5_1("51","T5-1"),
    B5_2("52","T5-2"),
    B5_3("53","T5-3"),
    B6_1("61","T6-1"),
    B6_2("62","T6-2"),
    B6_3("63","T6-3最高"),
    B7_1("71","M6-1"),
    B7_2("72","M6-2"),
    B7_3("73","M6-3最高"),
    ;
    
    public final String id;
    public final String name;
    
    private ChildRankNo(String id, String name) {
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
        return B1_1.id.equals(id) 
            || B1_2.id.equals(id) 
            || B1_3.id.equals(id)
            || B2_1.id.equals(id)
            || B2_2.id.equals(id) 
            || B2_3.id.equals(id)
            || B3_1.id.equals(id)
            || B3_2.id.equals(id) 
            || B3_3.id.equals(id)
            || B4_1.id.equals(id)
            || B4_2.id.equals(id) 
            || B4_3.id.equals(id)
            || B5_1.id.equals(id)
            || B5_2.id.equals(id) 
            || B5_3.id.equals(id)
            || B6_1.id.equals(id)
            || B6_2.id.equals(id) 
            || B6_3.id.equals(id)
            || B7_1.id.equals(id)
            || B7_2.id.equals(id)
            || B7_3.id.equals(id);
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (ChildRankNo childRankNo : ChildRankNo.values()) {
            if (childRankNo.id.equalsIgnoreCase(id)) {
                return childRankNo.name;
            }
        }
        return "";
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param name
     * @return
     */
    public static String getIdByName(String name) {
        for (ChildRankNo childRankNo : ChildRankNo.values()) {
            if (childRankNo.name.equalsIgnoreCase(name)) {
                return childRankNo.id;
            }
        }
        return "";
    }
}
