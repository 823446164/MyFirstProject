package com.amarsoft.aecd.employee.constant;


/**
 * 职级版本
 * @author zcluo
 */
public enum RankVersion {
	History("1","历史"),
	Current("2","当前"),
	Target("3","目标"),
    ;
    public String id;
    public String name;
    
    private RankVersion(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return History.id.equals(id)
                ||Current.id.equals(id)
                ||Target.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (RankVersion rankVersion : RankVersion.values()) {
            if (rankVersion.id.equalsIgnoreCase(id)) {
                return rankVersion.name;
            }
        }
        return "";
    }
}
