package com.amarsoft.aecd.system.constant;
/**
 * 机构类型
 * @author Amarsoft核算团队
 */
public enum OrgType {
    Organization("1","银行"),
    Department("2","部室"),
    Company("3","公司"),
    ;
    public String id;
    public String name;
    
    private OrgType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(OrgType orgLevel : OrgType.values()) {
            if(orgLevel.id.equals(id)) {
                return true;
            }
        }
        return false;
    }    
}
