package com.amarsoft.aecd.apms.constant;

public enum ParameterType {
    SRING("0","字符"),
    INTEGER("1","整型"),
    DOUBLE("2","浮点型"),
    DATE("3","日期")
    ;
    public String id;
    public String name;
    
    private ParameterType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return SRING.id.equals(id)
                ||INTEGER.id.equals(id)
                ||DOUBLE.id.equals(id)
                ||DATE.id.equals(id);
    }   
}
