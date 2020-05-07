package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeProjectExpInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeProjectExperience;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDto;

/**
 * 员工项目经历InfoService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeProjectExpInfoDtoServiceImpl implements EmployeeProjectExpInfoDtoService{
    /**
     * 员工项目经历Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeProjectExpInfoDtoQueryRsp employeeProjectExpInfoDtoQuery(@Valid EmployeeProjectExpInfoDtoQueryReq employeeProjectExpInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeProjectExperience employeeProjectExperience = bomanager.loadBusinessObject(EmployeeProjectExperience.class,"serialNo",employeeProjectExpInfoDtoQueryReq.getSerialNo());
        if(employeeProjectExperience!=null){
            EmployeeProjectExpInfoDtoQueryRsp employeeProjectExpInfoDto = new EmployeeProjectExpInfoDtoQueryRsp();
            employeeProjectExpInfoDto.setSerialNo(employeeProjectExperience.getSerialNo());
            employeeProjectExpInfoDto.setEmployeeNo(employeeProjectExperience.getEmployeeNo());
            employeeProjectExpInfoDto.setProjectName(employeeProjectExperience.getProjectName());
            employeeProjectExpInfoDto.setProjectMangerId(employeeProjectExperience.getProjectMangerId());
            employeeProjectExpInfoDto.setEmployeeJob(employeeProjectExperience.getEmployeeJob());
            employeeProjectExpInfoDto.setBegainTime(employeeProjectExperience.getBegainTime());
            employeeProjectExpInfoDto.setEndTime(employeeProjectExperience.getEndTime());
            employeeProjectExpInfoDto.setWorkDescribe(employeeProjectExperience.getWorkDescribe());
            employeeProjectExpInfoDto.setInputUserid(employeeProjectExperience.getInputUserid());
            employeeProjectExpInfoDto.setInputTime(employeeProjectExperience.getInputTime());
            employeeProjectExpInfoDto.setInputOrgId(employeeProjectExperience.getInputOrgId());
            employeeProjectExpInfoDto.setUpdateuserid(employeeProjectExperience.getUpdateuserid());
            employeeProjectExpInfoDto.setUpdatetime(employeeProjectExperience.getUpdateTime());
            employeeProjectExpInfoDto.setUpdateorgid(employeeProjectExperience.getUpdateorgid());
            return employeeProjectExpInfoDto;
        }

        return null;
    }

    /**
     * 员工项目经历Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeProjectExpInfoDtoSave(@Valid EmployeeProjectExpInfoDtoSaveReq employeeProjectExpInfoDtoSaveReq) {
        employeeProjectExpInfoDtoSaveAction(employeeProjectExpInfoDtoSaveReq);
    }
    /**
     * 员工项目经历Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeProjectExpInfoDtoSaveAction(EmployeeProjectExpInfoDto employeeProjectExpInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeProjectExpInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
