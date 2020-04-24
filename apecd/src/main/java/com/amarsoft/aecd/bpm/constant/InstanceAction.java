package com.amarsoft.aecd.bpm.constant;

/**
 * 流程激活或者挂起的动作
 * @author bwli
 */
public enum InstanceAction {
    /**
     * 挂起
     */
    SUSPEND("suspend","挂起"),
    /**
     * 激活
     */
    ACTIVATE("activate","激活")
    ;
    public String id;
    public String name;

    private InstanceAction(String id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return SUSPEND.id.equals(id)
                ||ACTIVATE.id.equals(id);
    }

    /**
     * 根据id获取指定的枚举类
     * @param id
     * @return
     */
    public static InstanceAction getEnum(String id) {
        for(InstanceAction instanceAction : InstanceAction.values()){
            if(instanceAction.id.equals(id)){
                return instanceAction;
            }
        }
        return null;
    }
}
