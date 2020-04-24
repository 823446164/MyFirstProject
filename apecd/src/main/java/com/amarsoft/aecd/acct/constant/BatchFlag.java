package com.amarsoft.aecd.acct.constant;

/**
 * 跑批标识
 * @author Amarsoft核算团队
 */
public enum BatchFlag {
    EOD("1", "批量批处理中"),
    DAY("0", "日间"),
    ;
    
    public final String id;
    public final String name;
    
    private BatchFlag(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return EOD.id.equals(id) || DAY.id.equals(id);
    }

}
