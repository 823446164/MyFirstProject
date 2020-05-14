package com.amarsoft.aecd.employee.constant;

import com.amarsoft.aecd.parameter.constant.LabelType;

/**
 * 工作岗位
 * @author dxiao
 */
public enum EmployeePersonalPerformance {
    _01("1","工作态度"),
    _02("2","执行能力"),
    _03("3","学习能力"),
    _04("4","技术能力"),
    _05("5","业务能力"),
    _06("6","沟通能力"),
    _07("7","领导能力"),
    _08("8","团队协作"),
    _09("9","抗压能力"),
    _10("10","稳定性"),
    _11("11","综合描述"),
    ;
    public String id;
    public String name;
    
    private EmployeePersonalPerformance(String id, String name){
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
                ||_05.id.equals(id)
                ||_06.id.equals(id)
                ||_07.id.equals(id)
                ||_08.id.equals(id)
                ||_09.id.equals(id)
                ||_10.id.equals(id)
                ||_11.id.equals(id);
   
    }
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * 
     * @param id
     * @return
     */
    public static String getNameById(String id) {
        for (EmployeePersonalPerformance employeePersonalPerformance : EmployeePersonalPerformance.values()) {
            if (employeePersonalPerformance.id.equalsIgnoreCase(id)) {
                return employeePersonalPerformance.name;
            }
        }
        return "";
    }
}
