package com.amarsoft.aecd.apms.constant;

/**
 * 前端列表排序的类型
 */
public enum SortType {
    DESCEND("descend","降序"),
    ASCEND("ascend","升序"),
    ;
    public String id;
    public String name;

    private SortType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return DESCEND.id.equals(id)
                ||ASCEND.id.equals(id);
    }   
}
