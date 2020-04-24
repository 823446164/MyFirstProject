package com.amarsoft.aecd.apms.constant;

public enum ProductCommonStatus {
    VALID("1","正常"),
    LOCK("2","锁定"),
    INVALID("3","停用"),
    ;
    
    public final String id;
    public final String name;
    
    private ProductCommonStatus(String id, String name){
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
                ||LOCK.id.equals(id)
                ||INVALID.id.equals(id);
    }
}
