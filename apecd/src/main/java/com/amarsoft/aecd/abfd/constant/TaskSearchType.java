/**
 * JobSearchType.java
 * jjyue
 * 2019年7月30日
 */
package com.amarsoft.aecd.abfd.constant;

/**
 * @author jjyue
 * 2019年7月30日
 */
public enum TaskSearchType {
    taskNo("taskNo","任务编号"),
    taskName("taskName","任务名称"),
    status("status","任务状态"),
    taskTags("taskTags","任务标签"),
    ;
    public final String id;
    public final String name;
    
    private TaskSearchType(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return taskNo.id.equals(id) 
                || taskName.id.equals(id)
                || status.id.equals(id)
                || taskTags.id.equals(id);
    }
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for(TaskSearchType flowSearchType : TaskSearchType.values()) {
            if(flowSearchType.id.equalsIgnoreCase(id)) {
                return flowSearchType.name;
            }
        }
        return "";
    }
}
