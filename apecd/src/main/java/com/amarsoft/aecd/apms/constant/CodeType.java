package com.amarsoft.aecd.apms.constant;

public enum CodeType {
    EMPTY("0","空"),
    CODE_LIST("1","代码表"),
    CODE_REPOSITORY("2","代码库"),
    FUNCTION("3","函数")
    ;
    public String id;
    public String name;
    
    private CodeType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return EMPTY.id.equals(id)
                ||CODE_LIST.id.equals(id)
                ||CODE_REPOSITORY.id.equals(id)
                ||FUNCTION.id.equals(id);
    }   
}
