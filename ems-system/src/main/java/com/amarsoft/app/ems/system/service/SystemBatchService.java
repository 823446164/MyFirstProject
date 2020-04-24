package com.amarsoft.app.ems.system.service;

public interface SystemBatchService {
    /**
     * 更新批量标识
     * 
     * */
    public void batchFlagUpdate(String batchFlag,String batchDate);
    /**
     * 更新系统批量日期
     * 
     * */
    public void batchDateUpdate(String batchDate);
    /**
     * 系统日期修改
     * 
     * */
    public void businessDateUpdate(String businessDate,String batchDate);
}
