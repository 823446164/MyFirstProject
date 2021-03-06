/*
 * 文件名：RankName.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.parameter.constant;

/**
 * 〈职级枚举类〉
 * @author xphe
 * @version 2020年5月18日
 * @see RankName
 * @since
 */

public enum RankName {
    DE1("1","助理软件工程师"),
    DE2("2","初级软件工程师"),
    DE3("3","中级软件工程师"),
    DE4("4","软件工程师"),
    DE5("5","高级软件工程师"),
    ;
    
    public final String id;
    public final String name;
    
    private RankName(String id, String name) {
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
        return DE1.id.equals(id) || DE2.id.equals(id) || DE3.id.equals(id) || DE4.id.equals(id) || DE5.id.equals(id);
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (RankName rankName : RankName.values()) {
            if (rankName.id.equalsIgnoreCase(id)) {
                return rankName.name;
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
        for (RankName rankName : RankName.values()) {
            if (rankName.name.equalsIgnoreCase(name)) {
                return rankName.id;
            }
        }
        return "";
    }
}
