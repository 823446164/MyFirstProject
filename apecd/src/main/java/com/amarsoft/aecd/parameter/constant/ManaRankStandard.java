/*
 * 文件名：ManaRankStandard.java
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
 * 〈管理类职等枚举类〉
 * @author xphe
 * @version 2020年5月19日
 * @see ManaRankStandard
 * @since
 */

public enum ManaRankStandard {
    _3("3","Band3"),
    _4("4","Band4"),
    _5("5","Band5"),
    ;
    
    public final String id;
    public final String name;
    
    private ManaRankStandard(String id, String name) {
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
        return  _3.id.equals(id) || _4.id.equals(id) || _5.id.equals(id);
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (ManaRankStandard manaRankStandard : ManaRankStandard.values()) {
            if (manaRankStandard.id.equalsIgnoreCase(id)) {
                return manaRankStandard.name;
            }
        }
        return "";
    }
}
