/*
 * 文件名：ManaRankName.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.parameter.constant;

/**
 * 〈管理类职级枚举类〉
 * @author xphe
 * @version 2020年5月19日
 * @see ManaRankName
 * @since
 */

public enum ManaRankName {
    MA1("3","助理项目经理"),
    MA2("4","项目经理"),
    MA3("5","项目总监"),
    ;
    
    public final String id;
    public final String name;
    
    private ManaRankName(String id, String name) {
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
        return MA1.id.equals(id) ||MA2.id.equals(id) ||MA3.id.equals(id);
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (ManaRankName manaRankName : ManaRankName.values()) {
            if (manaRankName.id.equalsIgnoreCase(id)) {
                return manaRankName.name;
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
        for (ManaRankName manaRankName : ManaRankName.values()) {
            if (manaRankName.name.equalsIgnoreCase(name)) {
                return manaRankName.id;
            }
        }
        return "";
    }
}
