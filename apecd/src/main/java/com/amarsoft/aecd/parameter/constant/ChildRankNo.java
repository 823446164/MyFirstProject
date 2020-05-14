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
    _1("1","T1-1最低"),
    _2("2","T1-2"),
    _3("3","T1-3"),
    _4("4","T2-1"),
    _5("5","T2-2"),
    _6("6","T2-3"),
    _7("7","T3-1"),
    _8("8","T3-2"),
    _9("9","T3-3"),
    _10("10","T4-1"),
    _11("11","T4-2"),
    _12("12","T4-3"),
    _13("13","T5-1"),
    _14("14","T5-2"),
    _15("15","T5-3"),
    _16("16","T6-1"),
    _17("17","T6-2"),
    _18("18","T6-3最高"),
    _19("19","M6-1"),
    _20("20","M6-2"),
    _21("21","M6-3最高"),
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
        return _1.id.equals(id) 
            || _2.id.equals(id) 
            || _3.id.equals(id)
            || _4.id.equals(id)
            || _5.id.equals(id) 
            || _6.id.equals(id)
            || _7.id.equals(id)
            || _8.id.equals(id) 
            || _9.id.equals(id)
            || _10.id.equals(id)
            || _11.id.equals(id) 
            || _12.id.equals(id)
            || _13.id.equals(id)
            || _14.id.equals(id) 
            || _15.id.equals(id)
            || _16.id.equals(id)
            || _17.id.equals(id) 
            || _18.id.equals(id)
            || _19.id.equals(id)
            || _20.id.equals(id)
            || _21.id.equals(id);
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
}
