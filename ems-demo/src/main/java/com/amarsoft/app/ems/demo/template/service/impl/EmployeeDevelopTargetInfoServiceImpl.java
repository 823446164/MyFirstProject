package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;
import org.springframework.stereotype.Service;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.demo.template.service.EmployeeDevelopTargetInfoService;
import com.amarsoft.app.ems.demo.entity.EmployeeDevelopTarget;
import com.amarsoft.app.ems.demo.entity.TestInfoJfan;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfo;

/**
 * 员工成长目标跟踪InfoService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeDevelopTargetInfoServiceImpl implements EmployeeDevelopTargetInfoService{
    /**
     * 员工成长目标跟踪Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeDevelopTargetInfoQueryRsp employeeDevelopTargetInfoQuery(@Valid EmployeeDevelopTargetInfoQueryReq employeeDevelopTargetInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeDevelopTarget employeeDevelopTarget = bomanager.loadBusinessObject(EmployeeDevelopTarget.class,"serialNo",employeeDevelopTargetInfoQueryReq.getSerialNo());
        if(employeeDevelopTarget!=null){
            EmployeeDevelopTargetInfoQueryRsp employeeDevelopTargetInfo = new EmployeeDevelopTargetInfoQueryRsp();
            employeeDevelopTargetInfo.setSerialNo(employeeDevelopTarget.getSerialNo());
            employeeDevelopTargetInfo.setEmployeeNo(employeeDevelopTarget.getEmployeeNo());
            employeeDevelopTargetInfo.setRankNo(employeeDevelopTarget.getRankNo());
            employeeDevelopTargetInfo.setDesignTime(employeeDevelopTarget.getDesignTime());
            employeeDevelopTargetInfo.setTargetDescribe(employeeDevelopTarget.getTargetDescribe());
            employeeDevelopTargetInfo.setDescribeTime(employeeDevelopTarget.getDescribeTime());
            employeeDevelopTargetInfo.setDesignerId(employeeDevelopTarget.getDesignerId());
            employeeDevelopTargetInfo.setRecord(employeeDevelopTarget.getRecord());
            employeeDevelopTargetInfo.setImplStatus(employeeDevelopTarget.getImplStatus());
            employeeDevelopTargetInfo.setInputUserId(employeeDevelopTarget.getInputUserId());
            employeeDevelopTargetInfo.setInputTime(employeeDevelopTarget.getInputTime());
            employeeDevelopTargetInfo.setInputOrgId(employeeDevelopTarget.getInputOrgId());
            employeeDevelopTargetInfo.setUpdateUserId(employeeDevelopTarget.getUpdateUserId());
            employeeDevelopTargetInfo.setUpdateTime(employeeDevelopTarget.getUpdateTime());
            employeeDevelopTargetInfo.setUpdateOrgId(employeeDevelopTarget.getUpdateOrgId());
            return employeeDevelopTargetInfo;
        }

        return null;
    }

    /**
     * 员工成长目标跟踪Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeDevelopTargetInfoSave(@Valid EmployeeDevelopTargetInfoSaveReq employeeDevelopTargetInfoSaveReq) {
        employeeDevelopTargetInfoSaveAction(employeeDevelopTargetInfoSaveReq);
    }
    /**
     * 员工成长目标跟踪Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeDevelopTargetInfoSaveAction(EmployeeDevelopTargetInfo employeeDevelopTargetInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeDevelopTargetInfo!=null){
        	EmployeeDevelopTarget employeeDevelopTarget = bomanager.keyLoadBusinessObject(EmployeeDevelopTarget.class,employeeDevelopTargetInfo.getSerialNo());
            if(employeeDevelopTarget==null){
            	employeeDevelopTarget = new EmployeeDevelopTarget();
            	employeeDevelopTarget.generateKey();
            }
            employeeDevelopTarget.setSerialNo(employeeDevelopTargetInfo.getSerialNo());
            employeeDevelopTarget.setEmployeeNo(employeeDevelopTargetInfo.getEmployeeNo());
            employeeDevelopTarget.setRankNo(employeeDevelopTargetInfo.getRankNo());
            employeeDevelopTarget.setDesignTime(DateHelper.getDate(employeeDevelopTargetInfo.getDesignTime()));
            employeeDevelopTarget.setTargetDescribe(employeeDevelopTargetInfo.getTargetDescribe());
            employeeDevelopTarget.setDescribeTime(DateHelper.getDate(employeeDevelopTargetInfo.getDescribeTime()));
            employeeDevelopTarget.setDesignerId(employeeDevelopTargetInfo.getDesignerId());
            employeeDevelopTarget.setRecord(employeeDevelopTargetInfo.getRecord());
            String implStatus = employeeDevelopTargetInfo.getImplStatus();
            employeeDevelopTarget.setImplStatus(employeeDevelopTargetInfo.getImplStatus());
            employeeDevelopTarget.setInputUserId(employeeDevelopTargetInfo.getInputUserId());
            employeeDevelopTarget.setInputTime(DateHelper.getDateTime(employeeDevelopTargetInfo.getInputTime()));
            employeeDevelopTarget.setInputOrgId(employeeDevelopTargetInfo.getInputOrgId());
            employeeDevelopTarget.setUpdateUserId(employeeDevelopTargetInfo.getUpdateUserId());
            employeeDevelopTarget.setUpdateTime(DateHelper.getDateTime(employeeDevelopTargetInfo.getUpdateTime()));
            employeeDevelopTarget.setUpdateOrgId(employeeDevelopTargetInfo.getUpdateOrgId());
        }
        bomanager.updateDB();
    }
    
	public String transferTime (String time){
    	
    	DateTimeFormatter df = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
    	LocalDateTime ldt = LocalDateTime.parse(time,df);
    	String formatTime = ldt.format(DateTimeFormatter.ofPattern(FormatType.DateTimeLongFormat.format));
    	return formatTime;
    }
}
