package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeDevelopTargetInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeDevelopTarget;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDto;

/**
 * 员工成长目标跟踪InfoService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeDevelopTargetInfoDtoServiceImpl implements EmployeeDevelopTargetInfoDtoService{
    /**
     * 员工成长目标跟踪Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeDevelopTargetInfoDtoQueryRsp employeeDevelopTargetInfoDtoQuery(@Valid EmployeeDevelopTargetInfoDtoQueryReq employeeDevelopTargetInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeDevelopTarget employeeDevelopTarget = bomanager.loadBusinessObject(EmployeeDevelopTarget.class,"serialNo",employeeDevelopTargetInfoDtoQueryReq.getSerialNo());
        if(employeeDevelopTarget!=null){
            EmployeeDevelopTargetInfoDtoQueryRsp employeeDevelopTargetInfoDto = new EmployeeDevelopTargetInfoDtoQueryRsp();
            employeeDevelopTargetInfoDto.setSerialNo(employeeDevelopTarget.getSerialNo());
            employeeDevelopTargetInfoDto.setEmployeeNo(employeeDevelopTarget.getEmployeeNo());
            employeeDevelopTargetInfoDto.setRankNo(employeeDevelopTarget.getRankNo());
            employeeDevelopTargetInfoDto.setDesignTime(employeeDevelopTarget.getDesignTime());
            employeeDevelopTargetInfoDto.setTargetDescribe(employeeDevelopTarget.getTargetDescribe());
            employeeDevelopTargetInfoDto.setDescribeTime(employeeDevelopTarget.getDescribeTime());
            employeeDevelopTargetInfoDto.setDesignerId(employeeDevelopTarget.getDesignerId());
            employeeDevelopTargetInfoDto.setRecord(employeeDevelopTarget.getRecord());
            employeeDevelopTargetInfoDto.setImplStatus(employeeDevelopTarget.getImplStatus());
            employeeDevelopTargetInfoDto.setInputUserId(employeeDevelopTarget.getInputUserId());
            employeeDevelopTargetInfoDto.setInputTime(employeeDevelopTarget.getInputTime());
            employeeDevelopTargetInfoDto.setInputOrgId(employeeDevelopTarget.getInputOrgId());
            employeeDevelopTargetInfoDto.setUpdateUserId(employeeDevelopTarget.getUpdateUserId());
            employeeDevelopTargetInfoDto.setUpdateTime(employeeDevelopTarget.getUpdateTime());
            employeeDevelopTargetInfoDto.setUpdateOrgId(employeeDevelopTarget.getUpdateOrgId());
            return employeeDevelopTargetInfoDto;
        }

        return null;
    }

    /**
     * 员工成长目标跟踪Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeDevelopTargetInfoDtoSave(@Valid EmployeeDevelopTargetInfoDtoSaveReq employeeDevelopTargetInfoDtoSaveReq) {
        employeeDevelopTargetInfoDtoSaveAction(employeeDevelopTargetInfoDtoSaveReq);
    }
    /**
     * 员工成长目标跟踪Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeDevelopTargetInfoDtoSaveAction(EmployeeDevelopTargetInfoDto employeeDevelopTargetInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeDevelopTargetInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
