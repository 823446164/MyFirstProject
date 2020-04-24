package com.amarsoft.aecd.abfd.constant;

/**
 * 任务提醒类型
 * @author sma4
 */
public enum TaskRemindType {
    NormalStart("0","任务开始"),
    NormalEnd("1","任务正常结束"),
    ExceptionEnd("2","任务异常结束"),
    ;
    public final String id;
    public final String name;
    
    private TaskRemindType(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return NormalStart.id.equals(id)
                ||NormalEnd.id.equals(id)
                ||ExceptionEnd.id.equals(id);
    }  
}
