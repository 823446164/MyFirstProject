package com.amarsoft.aecd.system.constant;
/**
 * 目录状态
 * @author zcluo
 */
public enum CatalogueStatus {
	Show("1","显示"),
	Hide("2","隐藏"),
    ;
    public String id;
    public String name;
    
    private CatalogueStatus(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Show.id.equals(id)
                ||Hide.id.equals(id);
    }
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (CatalogueStatus catalogueStatus : CatalogueStatus.values()) {
            if (catalogueStatus.id.equalsIgnoreCase(id)) {
                return catalogueStatus.name;
            }
        }
        return "";
    }
}
