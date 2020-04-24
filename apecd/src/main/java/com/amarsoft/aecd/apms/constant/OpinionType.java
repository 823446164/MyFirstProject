package com.amarsoft.aecd.apms.constant;

/**
 * 签署意见的类型
 * @author xxu1
 */
public enum OpinionType {
    APPROVE("0","通过"),
    REJECT("1","否决"),
    BACK("2","退回修改"),
    ;
    public String id;
    public String name;

    OpinionType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return APPROVE.id.equals(id)
                ||REJECT.id.equals(id)
                ||BACK.id.equals(id);
    }   
}
