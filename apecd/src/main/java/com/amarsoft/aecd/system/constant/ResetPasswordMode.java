package com.amarsoft.aecd.system.constant;

/**
 * 日历类型
 * @author hzhang23
 */
public enum ResetPasswordMode {
    URL("url","点击链接模式"),
    CODE("code","验证码模式"),
    ;
    
    public final String id;
    public final String name;
    
    private ResetPasswordMode(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return URL.id.equals(id)
                ||CODE.id.equals(id);
    }  
}
