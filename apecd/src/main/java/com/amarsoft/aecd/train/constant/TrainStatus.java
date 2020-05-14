package com.amarsoft.aecd.train.constant;
/**
 * 培训完成状态
 * @author zcluo
 */
public enum TrainStatus {
	Completed("1","已完成"),
	Unfinished("2","未完成"),
    ;
    public String id;
    public String name;
    
    private TrainStatus(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Completed.id.equals(id)
                ||Unfinished.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (TrainStatus trainStatus : TrainStatus.values()) {
            if (trainStatus.id.equalsIgnoreCase(id)) {
                return trainStatus.name;
            }
        }
        return "";
    }
}
