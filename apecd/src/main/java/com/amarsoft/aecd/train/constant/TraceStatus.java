package com.amarsoft.aecd.train.constant;
/**
 * 追踪状态
 * @author zcluo
 */
public enum TraceStatus {
	Pending("1","待处理"),
	FollowUp("2","待跟进"),
    Closed("3","已关闭"),
    ;
    public String id;
    public String name;
    
    private TraceStatus(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Pending.id.equals(id)
                ||FollowUp.id.equals(id)
                ||Closed.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (TraceStatus traceStatus : TraceStatus.values()) {
            if (traceStatus.id.equalsIgnoreCase(id)) {
                return traceStatus.name;
            }
        }
        return "";
    }
}
