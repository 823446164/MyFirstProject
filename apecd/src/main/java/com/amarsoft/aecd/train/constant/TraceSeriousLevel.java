package com.amarsoft.aecd.train.constant;


/**
 * 严重级别
 * @author zcluo
 */
public enum TraceSeriousLevel {
	Normal("1","正常"),
	Suggestion("2","建议"),
	Supervision("3","监督"),
	Reservation("4","留查"),
    ;
    public String id;
    public String name;
    
    private TraceSeriousLevel(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Normal.id.equals(id)
                ||Suggestion.id.equals(id)
                ||Supervision.id.equals(id)
                ||Reservation.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (TraceSeriousLevel traceSeriousLevel : TraceSeriousLevel.values()) {
            if (traceSeriousLevel.id.equalsIgnoreCase(id)) {
                return traceSeriousLevel.name;
            }
        }
        return "";
    }
}
