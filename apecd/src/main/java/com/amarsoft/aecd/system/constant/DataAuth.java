package com.amarsoft.aecd.system.constant;

/**
 * DataAuth
 * 数据权限枚举:应用于用户查看数据权限
 * @author hzhang23
 *
 */
public enum DataAuth {
    UserData("1","查看用户数据"),
    TeamData("2","查看本团队数据"),
    UnderOrgData("3","查看本级及下属机构数据"),
    OrgData("4","查看全行数据"),
    ;
    
    public String id;
    public String name;
    
    private DataAuth(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return UserData.id.equals(id)
                ||TeamData.id.equals(id)
                ||UnderOrgData.id.equals(id)
                ||OrgData.id.equals(id);
    }    
}
