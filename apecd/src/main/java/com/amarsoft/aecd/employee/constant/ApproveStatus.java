/*
 * 文件名：ApproveStatus.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.employee.constant;

/**
 * 〈审批状态枚举类〉
 * @author xphe
 * @version 2020年5月21日
 * @see ApproveStatus
 * @since
 */

public enum ApproveStatus {
    _01("1","待审批"),
    _02("2","审批中"),
    _03("3","审批通过"),
    _04("4","已否决"),
    ;
    public String id;
    public String name;
    
    private ApproveStatus(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return _01.id.equals(id)
                ||_02.id.equals(id)
                ||_03.id.equals(id)
                ||_04.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (ApproveStatus approveStatus : ApproveStatus.values()) {
            if (approveStatus.id.equalsIgnoreCase(id)) {
                return approveStatus.name;
            }
        }
        return "";
    }
}
