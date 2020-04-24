package com.amarsoft.aecd.apms.constant;

public enum SchemaQueryType {
    APPLY("1","申请"),
    ADJUST("2","调整"),
    ;
    
    public final String id;
    public final String name;
    
    private SchemaQueryType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return APPLY.id.equals(id)
                ||ADJUST.id.equals(id);
    }
}
