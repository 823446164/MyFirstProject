package com.amarsoft.app.ems.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.acct.constant.TermUnit;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.entity.SystemSetup;
import com.amarsoft.app.ems.system.service.SystemBatchService;

/**
 * 系统日期服务的处理类
 * @author hzhang23
 */
@Service
public class SystemBatchServiceImpl  implements SystemBatchService{
    
    /**
     * 修改日终状态
     * @param batchFlag
     * @param batchDate
     */
    @Override
    public void batchFlagUpdate(String batchFlag,String batchDate) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SystemSetup> systemSetups = bomanager.loadBusinessObjects(SystemSetup.class, "BusinessDate is not null");
        
        if(!check(systemSetups)) 
            throw new ALSException("997003");
        if(StringUtils.isEmpty(batchFlag)) 
            throw new ALSException("900108");
        SystemSetup systemSetup = systemSetups.get(0);
        if(!systemSetup.getBatchDate().equals(batchDate))
            throw new ALSException("900111");
        
        systemSetup.setBatchFlag(batchFlag);
        bomanager.updateBusinessObject(systemSetup);
        bomanager.updateDB();
    }
    
    /**
     * 修改批量日期
     * @param batchDate
     */
    @Override
    public void batchDateUpdate(String batchDate) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SystemSetup> systemSetups = bomanager.loadBusinessObjects(SystemSetup.class, "BusinessDate is not null");
        
        if(!check(systemSetups)) 
        throw new ALSException("997003");
        if(StringUtils.isEmpty(batchDate)) 
            throw new ALSException("900109");
        SystemSetup systemSetup = systemSetups.get(0);
        if(!DateHelper.getRelativeDate(systemSetup.getBatchDate(), TermUnit.Day.id, 1).equals(batchDate))
            throw new ALSException("900112");
        if(!systemSetup.getBusinessDate().equals(batchDate))
            throw new ALSException("900115");
        systemSetup.setBatchDate(batchDate);
        bomanager.updateBusinessObject(systemSetup);
        bomanager.updateDB();
    }
    /**
     * 修改批量日期
     * @param businessDate
     * @param batchDate
     */
    @Override
    public void businessDateUpdate(String businessDate, String batchDate) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SystemSetup> systemSetups = bomanager.loadBusinessObjects(SystemSetup.class, "BusinessDate is not null");
        if(!check(systemSetups)) {
            throw new ALSException("997003");
        } else {
            if(StringUtils.isEmpty(batchDate)||StringUtils.isEmpty(businessDate)) {
                throw new ALSException("900110");
            }
            SystemSetup systemSetup = systemSetups.get(0);
            if(!systemSetup.getBatchDate().equals(batchDate))
                throw new ALSException("900113");
            
            if(!DateHelper.getRelativeDate(systemSetup.getBusinessDate(), TermUnit.Day.id, 1).equals(businessDate))
                throw new ALSException("900114");
            
            if(!DateHelper.getRelativeDate(systemSetup.getBatchDate(), TermUnit.Day.id, 1).equals(businessDate)) 
                throw new ALSException("900114");
            
            systemSetup.setBusinessDate(businessDate);
            bomanager.updateBusinessObject(systemSetup);
            bomanager.updateDB();
        }
    }
    
    /**
     *  校验系统日期是否存在
     * @param systemSetups
     * @return 系统日期是否正常
     */
    private boolean check(List<SystemSetup> systemSetups) {
        if (StringUtils.isEmpty(systemSetups)) {
            return false;
        }
        SystemSetup systemSetup = systemSetups.get(0);
        if (StringUtils.isEmpty(systemSetup.getBusinessDate())) {
            return false;
        }
        return true;
    }
}
