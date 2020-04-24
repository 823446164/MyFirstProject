package com.amarsoft.app.ems.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.system.cs.dto.alldatequery.AllDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.batchdatequery.BatchDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.batchflagquery.BatchFlagQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.businessdatequery.BusinessDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.productdatequery.ProductDateQueryRsp;
import com.amarsoft.app.ems.system.entity.SystemSetup;
import com.amarsoft.app.ems.system.service.SystemService;

/**
 * 系统日期服务的处理类
 * @author hzhang23
 */
@Service
public class SystemServiceImpl  implements SystemService{
    
    /**
     * 获取SYSTEM_SETUP的系统日期
     * @return 系统日期
     */
    @Override
    public BusinessDateQueryRsp getBusinessDate() {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SystemSetup> systemSetups = bomanager.loadBusinessObjects(SystemSetup.class, "BusinessDate is not null");
        BusinessDateQueryRsp response = new BusinessDateQueryRsp();
        
        if(!check(systemSetups))
            throw new ALSException("997003");
        SystemSetup systemSetup = systemSetups.get(0);
        String businessDate = systemSetup.getBusinessDate();
        response.setBusinessDate(businessDate);
        return response;
    }
    
    /**
     * 获取SYSTEM_SETUP的批量标识
     * @return 批量标识
     */
    @Override
    public BatchFlagQueryRsp getBatchFlag() {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SystemSetup> systemSetups = bomanager.loadBusinessObjects(SystemSetup.class, "BusinessDate is not null");
        BatchFlagQueryRsp response = new BatchFlagQueryRsp();
        
        if(!check(systemSetups))
            throw new ALSException("997003");
        SystemSetup systemSetup = systemSetups.get(0);
        String batchFlag = systemSetup.getBatchFlag();
        response.setBatchFlag(batchFlag);
        return response;
    }
    
    /**
     * 获取SYSTEM_SETUP的批量日期
     * @return 批量日期
     */
    @Override
    public BatchDateQueryRsp getBatchDate() {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SystemSetup> systemSetups = bomanager.loadBusinessObjects(SystemSetup.class, "BusinessDate is not null");
        BatchDateQueryRsp response = new BatchDateQueryRsp();
        
        if(!check(systemSetups)) 
            throw new ALSException("997003");
        SystemSetup systemSetup = systemSetups.get(0);
        String batchDate = systemSetup.getBatchDate();
        response.setBatchDate(batchDate);
        return response;
    }
    
    /**
     * 获取SYSTEM_SETUP的投产日期
     * @return 投产日期
     */
    @Override
    public ProductDateQueryRsp getProductDate() {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SystemSetup> systemSetups = bomanager.loadBusinessObjects(SystemSetup.class, "BusinessDate is not null");
        ProductDateQueryRsp response = new ProductDateQueryRsp();
        
        if(!check(systemSetups))
            throw new ALSException("997003");
        SystemSetup systemSetup = systemSetups.get(0);
        String productDate = systemSetup.getProductDate();
        response.setProductDate(productDate);
        return response;
    }
    
    /**
     *  校验系统日期是否存在
     * @param systemSetups
     * @return 是、否
     */
    private boolean check(List<SystemSetup> systemSetups) {
        if (CollectionUtils.isEmpty(systemSetups)) {
            return false;
        }
        SystemSetup systemSetup = systemSetups.get(0);
        if (StringUtils.isEmpty(systemSetup.getBusinessDate())) {
            return false;
        }
        return true;
    }

    /**
     *  查询系统所有日期
     * @param systemSetups
     * @return 所有日期和标示
     */
    @Override
    public AllDateQueryRsp allDateQuery() {
        AllDateQueryRsp rsp = new AllDateQueryRsp();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SystemSetup> systemSetups = bomanager.loadBusinessObjects(SystemSetup.class, "BusinessDate is not null");
        
        if(!check(systemSetups))
            throw new ALSException("997003");
        rsp.setBusinessDate(systemSetups.get(0).getBusinessDate());
        rsp.setBatchDate(systemSetups.get(0).getBatchDate());
        rsp.setProductDate(systemSetups.get(0).getProductDate());
        rsp.setBatchFlag(systemSetups.get(0).getBatchFlag());
        return rsp;
    }
}
