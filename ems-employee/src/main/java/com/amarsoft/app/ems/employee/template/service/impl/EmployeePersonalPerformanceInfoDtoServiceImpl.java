package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeePersonalPerformanceInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeePersonalPerformance;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDto;

/**
 * 员工项目经历个人表现InfoService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeePersonalPerformanceInfoDtoServiceImpl implements EmployeePersonalPerformanceInfoDtoService{
    /**
     * 员工项目经历个人表现Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeePersonalPerformanceInfoDtoQueryRsp employeePersonalPerformanceInfoDtoQuery(@Valid EmployeePersonalPerformanceInfoDtoQueryReq employeePersonalPerformanceInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeePersonalPerformance employeePersonalPerformance = bomanager.loadBusinessObject(EmployeePersonalPerformance.class,"serialNo",employeePersonalPerformanceInfoDtoQueryReq.getSerialNo());
        if(employeePersonalPerformance!=null){
            EmployeePersonalPerformanceInfoDtoQueryRsp employeePersonalPerformanceInfoDto = new EmployeePersonalPerformanceInfoDtoQueryRsp();
            employeePersonalPerformanceInfoDto.setSerialNo(employeePersonalPerformance.getSerialNo());
            employeePersonalPerformanceInfoDto.setEmployeeNo(employeePersonalPerformance.getEmployeeNo());
            employeePersonalPerformanceInfoDto.setProjectNo(employeePersonalPerformance.getProjectNo());
            employeePersonalPerformanceInfoDto.setFactors(employeePersonalPerformance.getFactors());
            employeePersonalPerformanceInfoDto.setAbilityDescribe(employeePersonalPerformance.getAbilityDescribe());
            employeePersonalPerformanceInfoDto.setScore(employeePersonalPerformance.getScore());
            employeePersonalPerformanceInfoDto.setInputUserId(employeePersonalPerformance.getInputUserId());
            employeePersonalPerformanceInfoDto.setInputTime(employeePersonalPerformance.getInputTime());
            employeePersonalPerformanceInfoDto.setInputOrgId(employeePersonalPerformance.getInputOrgId());
            employeePersonalPerformanceInfoDto.setUpdateUserId(employeePersonalPerformance.getUpdateUserId());
            employeePersonalPerformanceInfoDto.setUpdateTime(employeePersonalPerformance.getUpdateTime());
            employeePersonalPerformanceInfoDto.setUpdateOrgId(employeePersonalPerformance.getUpdateOrgId());
            return employeePersonalPerformanceInfoDto;
        }

        return null;
    }

    /**
     * 员工项目经历个人表现Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeePersonalPerformanceInfoDtoSave(@Valid EmployeePersonalPerformanceInfoDtoSaveReq employeePersonalPerformanceInfoDtoSaveReq) {
        employeePersonalPerformanceInfoDtoSaveAction(employeePersonalPerformanceInfoDtoSaveReq);
    }
    /**
     * 员工项目经历个人表现Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeePersonalPerformanceInfoDtoSaveAction(EmployeePersonalPerformanceInfoDto employeePersonalPerformanceInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeePersonalPerformanceInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
