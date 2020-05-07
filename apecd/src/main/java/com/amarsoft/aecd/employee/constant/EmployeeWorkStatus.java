package com.amarsoft.aecd.employee.constant;
/**
 * 员工工作状态
 * @author zcluo
 */
public enum EmployeeWorkStatus {
    _01("1","新入职"),
    _02("2","基础培训阶段"),
    _03("3","进入项目"),
    _04("4","已返回部门"),
    _05("5","离职/转部门"),
    ;
    public String id;
    public String name;
    
    private EmployeeWorkStatus(String id, String name){
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
}
