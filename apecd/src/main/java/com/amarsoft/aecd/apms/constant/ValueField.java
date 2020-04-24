package com.amarsoft.aecd.apms.constant;

public enum ValueField {
    DEFAULT("Default", "默认值"),
    MAX("Max", "最大值"),
    MIN("Min", "最小值"),
    OPTIONAL("Optional", "可选值"),
    MULTIPLE("Multiple", "多选值")
    ;
    public String id;
    public String name;
    
    private ValueField(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return DEFAULT.id.equals(id)
                ||MAX.id.equals(id)
                ||MIN.id.equals(id)
                ||OPTIONAL.id.equals(id)
                ||MULTIPLE.id.equals(id);
    }   
}
