package com.amarsoft.aecd.apms.constant;

public enum UserAuthorityType {
    READONLY("0","只读非必须"),
    READONLY_REQUIRED("4", "只读必须"),
    WRITABLE("1","可写非必须"),
    WRITABLE_REQUIRED("2","可写必须"),
    INVISIBLE("3","不可见"),
    ;
    public String id;
    public String name;

    private UserAuthorityType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return READONLY.id.equals(id)
                ||READONLY_REQUIRED.id.equals(id)
                ||WRITABLE.id.equals(id)
                ||WRITABLE_REQUIRED.id.equals(id)
                ||INVISIBLE.id.equals(id);
    }   
}
