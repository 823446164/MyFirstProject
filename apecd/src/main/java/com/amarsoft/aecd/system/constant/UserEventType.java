package com.amarsoft.aecd.system.constant;

/**
 * 用户事件
 * @author Amarsoft核算团队
 */
public enum UserEventType {
    LoginSuccess("LoginSuccess","登录成功"),
    LoginFailed("LoginFailed","登录失败"),
    LogoutSuccess("LogoutSuccess","登出成功"),
    LogoutFailed("LogoutFailed","登出失败"),
    ChangeRole("ChangeRole","修改角色"),
    ChangeGroup("ChangeGroup","修改角色组"),
    AddUser("AddUser","新增用户"),
    UpdateUser("UpdateUser","更新用户"),
    DeleteUser("DeleteUser","删除用户"),
    SetPassword("SetPassword","设置密码"),
    ResetPassword("ResetPassword","重置密码"),
    Leave("Leave","用户请假"),
    CancelLeave("CancelLeave","用户取消请假"),
    Substitute("Substitute","用户转授权"),
    ToggleStatus("ToggleStatus","用户状态转换"),
    CancelSubstitute("CancelSubstitute","用户取消转授权"),
    OrgMigration_Delete("OrgMigration_Delete","机构迁移-删除"),
    OrgMigration_Change("OrgMigration_Change","机构迁移-变更")
    ;
    public String id;
    public String name;
    
    private UserEventType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for (UserEventType userEventType : UserEventType.values()) {
            if(userEventType.id.equals(id)) {
                return true;
            }
        }
        return false;
    }    
    /**
     * 获取UserEventType对应的Name
     * @param id
     * @return
     */
    public static String getName(String id) {
        for (UserEventType userEventType : UserEventType.values()) {
            if(userEventType.id.equals(id)) {
                return userEventType.name;
            }
        }
        return "";
    }    
}
