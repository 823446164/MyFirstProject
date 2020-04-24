package com.amarsoft.aecd.system.constant;
/**
 * 公司级别
 * @author hzhang23
 */
public enum CompanyType {
    Head_Compony("4","总公司"),
    Branch_Compony("5","分公司"),
    ;
    public String id;
    public String name;
    
    private CompanyType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(CompanyType orgLevel : CompanyType.values()) {
            if(orgLevel.id.equals(id)) {
                return true;
            }
        }
        return false;
    }    
}
