package com.amarsoft.app.ems.system.service;

import com.amarsoft.app.ems.system.cs.dto.alldatequery.AllDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.batchdatequery.BatchDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.batchflagquery.BatchFlagQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.businessdatequery.BusinessDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.productdatequery.ProductDateQueryRsp;

/**
 * 系统日期服务的处理类
 * @author xxu1
 */
public interface SystemService {

    /**
     * 获取系统日期
     * @return 系统日期
     */
    BusinessDateQueryRsp getBusinessDate();
    
    /**
     * 获取批量标识
     * @return 批量标识
     */
    BatchFlagQueryRsp getBatchFlag();
    
    /**
     * 获取批量日期
     * @return 批量日期
     */
    BatchDateQueryRsp getBatchDate();
    
    /**
     * 获取投产日期
     * @return 投产日期
     */
    ProductDateQueryRsp getProductDate();
    /**
     * 获取系统日期、批量日期、投产日期、批量标示
     * @return 系统日期、批量日期、投产日期、批量标示
     */
    AllDateQueryRsp allDateQuery();
    
}
