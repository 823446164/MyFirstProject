package com.amarsoft.aecd.system.constant;
/**
 * 迁移操作类型
 * @author Amarsoft核算团队
 */
public enum MigrationOptional {
    Delete("2","删除机构"),
    Change("3","变更机构"),
    Pass("pass","不检查的迁移服务"),
    Check("check","检查迁移服务")
    ;
    public String id;
    public String name;
    
    private MigrationOptional(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(MigrationOptional authType : MigrationOptional.values()) {
            if(authType.id.equals(id)) {
                return true;
            }
        }
        return false;
    }    
}
