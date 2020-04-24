package com.amarsoft.aecd.system.constant;

/**
 * 日历类型
 * @author hzhang23
 */
public enum CalendarType {
    Land("Land","北京时间","GMT+08:00"),
    HK("HK","香港时间","GMT+08:00"),
    MO("Mo","澳门时间","GMT+08:00"),
    ;
    
    public final String id;
    public final String name;
    public final String zoneId;
    
    private CalendarType(String id, String name,String zoneId){
        this.id = id;
        this.name = name;
        this.zoneId = zoneId;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Land.id.equals(id)
                ||HK.id.equals(id)
                ||MO.id.equals(id);
    }
    
    /**
     * 根据传入zoneId获取id
     * @param id
     * @return
     */
    public static String getZoneId(String calendarType) {
        for (CalendarType type : CalendarType.values()) {
            if (type.id.equals(calendarType)) {
                return type.zoneId;
            }
        }
        return "";
    }  
}
