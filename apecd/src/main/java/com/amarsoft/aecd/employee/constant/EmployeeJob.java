package com.amarsoft.aecd.employee.constant;
/**
 * 工作岗位
 * @author zcluo
 */
public enum EmployeeJob {
    _01("1","项目总监"),
    _02("2","项目经理"),
    _03("3","项目成员"),
    _04("4","需求负责人"),
    _05("5","需求人员"),
    _06("6","测试负责人"),
    _07("7","测试人员"),
    ;
    public String id;
    public String name;
    
    private EmployeeJob(String id, String name){
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
                ||_07.id.equals(id);
    }
}
