package com.amarsoft.aecd.apms.constant;

/**
 * 流程状态
 * @author xxu1
 *
 */
public enum FlowStatus {
    PENDING("1","待处理　"),
    PROCESSING("2","处理中"),
    BACKEDIT("3","退回修改"),
    APPROVED("4","已通过"),
    REJECTED("5","已否决")

    ;
    public String id;
    public String name;
    
    private FlowStatus(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return PENDING.id.equals(id)
                ||PROCESSING.id.equals(id)
                ||BACKEDIT.id.equals(id)
                ||APPROVED.id.equals(id)
                ||REJECTED.id.equals(id);
    }   
}
