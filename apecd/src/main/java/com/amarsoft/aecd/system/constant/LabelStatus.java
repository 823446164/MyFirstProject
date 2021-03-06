package com.amarsoft.aecd.system.constant;

/**
 * 标签状态（SYS_TEAM_USER表）
 * @author bjmeng
 *
 */
public enum LabelStatus {
	
	New("1","新增"),
    Enabled("2","生效"),
    Disabled("3","失效"),
    ;
    public final String id;
    public final String name;
    
    private LabelStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return New.id.equals(id) 
                || Enabled.id.equals(id)
                ||Disabled.id.equals(id);
    }
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for(LabelStatus labelStatus : LabelStatus.values()) {
            if(labelStatus.id.equalsIgnoreCase(id)) {
                return labelStatus.name;
            }
        }
        return "";
    }
	

}
