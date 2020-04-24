package com.amarsoft.aecd.abfd.constant;

/**
 * 任务标签
 * @author sma4
 */
public enum TaskTag {
    NetLoan("0","网贷"),
    Credit("1","信贷"),
    Retail("2","零售"),
    creditReport("3","征信"),
    ;
    public final String id;
    public final String name;
    
    private TaskTag(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return NetLoan.id.equals(id)
                ||Credit.id.equals(id)
                ||Retail.id.equals(id)
                ||creditReport.id.equals(id);
    }  
}
