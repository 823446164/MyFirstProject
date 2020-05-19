/*
 * 文件名：RankStandard.java
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
 * 〈职等枚举类〉
 * 〈Band1..Band6〉
 * @author xphe
 * @version 2020年5月14日
 * @see RankStandard
 * @since
 */

public enum RankStandard {
    _1("1","Band1"),
    _2("2","Band2"),
    _3("3","Band3"),
    _4("4","Band4"),
    _5("5","Band5"),
    ;
    
    public final String id;
    public final String name;
    
    private RankStandard(String id, String name) {
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
        return _1.id.equals(id) || _2.id.equals(id) || _3.id.equals(id) || _4.id.equals(id) || _5.id.equals(id);
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (RankStandard rankStandard : RankStandard.values()) {
            if (rankStandard.id.equalsIgnoreCase(id)) {
                return rankStandard.name;
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
        for (RankStandard rankStandard : RankStandard.values()) {
            if (rankStandard.name.equalsIgnoreCase(name)) {
                return rankStandard.id;
            }
        }
        return "";
    }
}
