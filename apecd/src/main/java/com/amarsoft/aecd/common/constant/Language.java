package com.amarsoft.aecd.common.constant;
/**
 * 语言、国家
 * @author Amarsoft核算团队
 */
public enum Language {
    zh_CN("zh-CN","简体中文"),
    zh_TW("zh-TW","繁體中文"),
//    EN("en-US","English"),
    ;
    public String id;
    public String name;
    
    private Language(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        for(Language language : Language.values()) {
            if(language.id.equals(id)) {
                return true;
            }
        }
        return false;
    }    
}
