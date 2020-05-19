/*
 * 文件名：RankStandardAbility.java
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
 * 〈职级能力要求〉
 * @author xphe
 * @version 2020年5月19日
 * @see RankStandardAbility
 * @since
 */

public enum RankStandardAbility {
        _1("1","合格岗位者"),
        _2("2","胜任岗位者"),
        _3("3","标杆岗位者"),
        ;
    public final String id;
    public final String name;
    
    private RankStandardAbility(String id, String name) {
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
        return _1.id.equals(id) || _2.id.equals(id) || _3.id.equals(id);
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (RankStandardAbility rankStandardAbility : RankStandardAbility.values()) {
            if (rankStandardAbility.id.equalsIgnoreCase(id)) {
                return rankStandardAbility.name;
            }
        }
        return "";
    }
}
