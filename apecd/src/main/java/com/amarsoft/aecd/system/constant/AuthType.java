package com.amarsoft.aecd.system.constant;
/**
 * 授权类型
 * @author Amarsoft核算团队
 */
public enum AuthType {
    MENU("1","基于菜单的授权"),
    URL("2","基于URL的授权"),
    ;
    public String id;
    public String name;
    
    private AuthType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(AuthType authType : AuthType.values()) {
            if(authType.id.equals(id)) {
                return true;
            }
        }
        return false;
    }    
}
