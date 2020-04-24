package com.amarsoft.aecd.system.constant;
/**
 * 机构迁移状态
 * @author Amarsoft核算团队
 */
public enum MigrationStatus {
    Normal("1","正常"),
    Deletable("2","待删除"),
    Modifiable("3","待变更"),
    Failed("4","变更失败"),
    BlockUp("5","停用"),
    ;
    public String id;
    public String name;
    
    private MigrationStatus(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(MigrationStatus authType : MigrationStatus.values()) {
            if(authType.id.equals(id)) {
                return true;
            }
        }
        return false;
    }    
}
