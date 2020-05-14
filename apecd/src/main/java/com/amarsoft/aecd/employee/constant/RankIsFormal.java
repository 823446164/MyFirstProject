package com.amarsoft.aecd.employee.constant;


/**
 * 是否正式
 * @author zcluo
 */
public enum RankIsFormal {
	Yes("1","是"),
    No("2","否"),
    ;
    public String id;
    public String name;
    
    private RankIsFormal(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Yes.id.equals(id)
                ||No.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (RankIsFormal rankIsFormal : RankIsFormal.values()) {
            if (rankIsFormal.id.equalsIgnoreCase(id)) {
                return rankIsFormal.name;
            }
        }
        return "";
    }
}
