package com.amarsoft.aecd.employee.constant;
/**
 * 员工状态
 * @author zcluo
 */
public enum EmployeeStatus {
	Internship("1","实习"),
	Trial("2","试用"),
	Official("3","正式"),
	Resignation("4","离职"),
    ;
    public String id;
    public String name;
    
    private EmployeeStatus(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Internship.id.equals(id)
                ||Trial.id.equals(id)
                ||Official.id.equals(id)
                ||Resignation.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (EmployeeStatus employeeStatus : EmployeeStatus.values()) {
            if (employeeStatus.id.equalsIgnoreCase(id)) {
                return employeeStatus.name;
            }
        }
        return "";
    }
}
