package com.amarsoft.aecd.apms.constant;

public enum ProductStatus {
    VALID("1","正常"),
    INVALID("0", "停用")
    ;
    public String id;
    public String name;
    
    private ProductStatus(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return VALID.id.equals(id)
                ||INVALID.id.equals(id);
    }   
}
