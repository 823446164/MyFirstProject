package com.amarsoft.aecd.apms.constant;

public enum ProductType {
    BASIC_PRODUCT("0","基础产品"),
    SCHEMA_PRODUCT("1","方案产品"),
    ;
    public String id;
    public String name;
    
    private ProductType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return BASIC_PRODUCT.id.equals(id)
                ||SCHEMA_PRODUCT.id.equals(id);
    }   
}
