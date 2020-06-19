package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeePersonalPerformanceInfoService;
import com.amarsoft.app.ems.demo.entity.EmployeePersonalPerformance;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfo;

/**
 * 员工项目经历个人表现InfoService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeePersonalPerformanceInfoServiceImpl implements EmployeePersonalPerformanceInfoService{
    /**
     * 员工项目经历个人表现Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeePersonalPerformanceInfoQueryRsp employeePersonalPerformanceInfoQuery(@Valid EmployeePersonalPerformanceInfoQueryReq employeePersonalPerformanceInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeePersonalPerformance employeePersonalPerformance = bomanager.loadBusinessObject(EmployeePersonalPerformance.class,"serialNo",employeePersonalPerformanceInfoQueryReq.getSerialNo());
        if(employeePersonalPerformance!=null){
            EmployeePersonalPerformanceInfoQueryRsp employeePersonalPerformanceInfo = new EmployeePersonalPerformanceInfoQueryRsp();
            employeePersonalPerformanceInfo.setSerialNo(employeePersonalPerformance.getSerialNo());
            employeePersonalPerformanceInfo.setEmployeeNo(employeePersonalPerformance.getEmployeeNo());
            employeePersonalPerformanceInfo.setProjectNo(employeePersonalPerformance.getProjectNo());
            employeePersonalPerformanceInfo.setFactors(employeePersonalPerformance.getFactors());
            employeePersonalPerformanceInfo.setAbilityDescribe(employeePersonalPerformance.getAbilityDescribe());
            employeePersonalPerformanceInfo.setScore(employeePersonalPerformance.getScore());
            employeePersonalPerformanceInfo.setInputUserId(employeePersonalPerformance.getInputUserId());
            employeePersonalPerformanceInfo.setInputTime(employeePersonalPerformance.getInputTime());
            employeePersonalPerformanceInfo.setInputOrgId(employeePersonalPerformance.getInputOrgId());
            employeePersonalPerformanceInfo.setUpdateUserId(employeePersonalPerformance.getUpdateUserId());
            employeePersonalPerformanceInfo.setUpdateTime(employeePersonalPerformance.getUpdateTime());
            employeePersonalPerformanceInfo.setUpdateOrgId(employeePersonalPerformance.getUpdateOrgId());
            return employeePersonalPerformanceInfo;
        }

        return null;
    }

    /**
     * 员工项目经历个人表现Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeePersonalPerformanceInfoSave(@Valid EmployeePersonalPerformanceInfoSaveReq employeePersonalPerformanceInfoSaveReq) {
        employeePersonalPerformanceInfoSaveAction(employeePersonalPerformanceInfoSaveReq);
    }
    /**
     * 员工项目经历个人表现Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeePersonalPerformanceInfoSaveAction(EmployeePersonalPerformanceInfo employeePersonalPerformanceInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeePersonalPerformanceInfo!=null){
        }
        bomanager.updateDB();
    }
}
