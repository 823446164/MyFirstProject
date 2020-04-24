package com.amarsoft.aecd.apms.constant;

public enum GitLockFlag {
    LOCKED("1","锁定"),
    UNLOCKED("0","未锁定"),
    ;
    public String id;
    public String name;
    
    private GitLockFlag(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return LOCKED.id.equals(id)
                ||UNLOCKED.id.equals(id);
    }   
}
