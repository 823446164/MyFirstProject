package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String,String> employeeProjectExpInfoDtoSave(@Valid EmployeeProjectExpInfoDtoSaveReq employeeProjectExpInfoDtoSaveReq) {
        return employeeProjectExpInfoDtoSaveAction(employeeProjectExpInfoDtoSaveReq);
    }
    /**
     * 员工项目经历Info单记录保存-只更新工作描述
     * @param
     * @return
     */
    @Transactional
    public Map<String,String> employeeProjectExpInfoDtoSaveAction(EmployeeProjectExpInfoDto employeeProjectExpInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeProjectExpInfoDto!=null){
            //根据主键查询员工项目经历
            EmployeeProjectExperience employeeProjectExperience = bomanager.keyLoadBusinessObject(EmployeeProjectExperience.class,employeeProjectExpInfoDto.getSerialNo());
            //更新工作描述字段
            employeeProjectExperience.setWorkDescribe(employeeProjectExpInfoDto.getWorkDescribe());
            bomanager.updateBusinessObject(employeeProjectExperience);//更新操作
        }
        bomanager.updateDB();
        //定义一个map封装返回信息 - 判断是否更新成功
        Map<String,String> map = new HashMap<String,String>();
        map.put("status", "Y");
        return map;
    }
}
