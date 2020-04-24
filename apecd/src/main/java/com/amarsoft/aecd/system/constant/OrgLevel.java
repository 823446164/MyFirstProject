package com.amarsoft.aecd.system.constant;

/**
 * 机构级别
 * @author Amarsoft核算团队
 */
public enum OrgLevel {
    LEVEL_1("1","总行"),
    LEVEL_2("2","一级分行"),
    LEVEL_3("3","二级分行"),
    LEVEL_4("4","支行"),
    LEVEL_5("5","网点"),
    ;
    public String id;
    public String name;
    
    private OrgLevel(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(OrgLevel orgLevel : OrgLevel.values()) {
            if(orgLevel.id.equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 获取下一层级的机构级别码值
     * @param id
     * @return
     */
    public static String getNextLevel(String id) {
        for(OrgLevel orgLevel : OrgLevel.values()) {
            String levelId = (Integer.parseInt(id) + 1) + "";
            if(orgLevel.id.equals(levelId) && isExist(levelId)) {
                return orgLevel.id;
            }
        }
        return "";
    }
}
