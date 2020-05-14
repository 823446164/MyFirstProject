package com.amarsoft.aecd.employee.constant;
/**
 * 员工学历
 * @author zcluo
 */
public enum EmployeeDucation {
	_01("1","研究生"),
	_02("2","重点一本"),
	_03("3","普通一本"),
	_04("4","二本"),
	_05("5","其他"),
    ;
    public String id;
    public String name;
    
    private EmployeeDucation(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return _01.id.equals(id)
                ||_02.id.equals(id)
                ||_03.id.equals(id)
                ||_04.id.equals(id)
                ||_05.id.equals(id);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (EmployeeDucation employeeDucation : EmployeeDucation.values()) {
            if (employeeDucation.id.equalsIgnoreCase(id)) {
                return employeeDucation.name;
            }
        }
        return "";
    }
}
