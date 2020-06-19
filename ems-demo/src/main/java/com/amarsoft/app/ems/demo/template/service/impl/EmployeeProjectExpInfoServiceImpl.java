package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeeProjectExpInfoService;
import com.amarsoft.app.ems.demo.entity.EmployeeProjectExperience;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfo;

/**
 * 员工项目经历InfoService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeProjectExpInfoServiceImpl implements EmployeeProjectExpInfoService{
    /**
     * 员工项目经历Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeProjectExpInfoQueryRsp employeeProjectExpInfoQuery(@Valid EmployeeProjectExpInfoQueryReq employeeProjectExpInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeProjectExperience employeeProjectExperience = bomanager.loadBusinessObject(EmployeeProjectExperience.class,"serialNo",employeeProjectExpInfoQueryReq.getSerialNo());
        if(employeeProjectExperience!=null){
            EmployeeProjectExpInfoQueryRsp employeeProjectExpInfo = new EmployeeProjectExpInfoQueryRsp();
            employeeProjectExpInfo.setSerialNo(employeeProjectExperience.getSerialNo());
            employeeProjectExpInfo.setEmployeeNo(employeeProjectExperience.getEmployeeNo());
            employeeProjectExpInfo.setProjectName(employeeProjectExperience.getProjectName());
            employeeProjectExpInfo.setProjectMangerId(employeeProjectExperience.getProjectMangerId());
            employeeProjectExpInfo.setEmployeeJob(employeeProjectExperience.getEmployeeJob());
            employeeProjectExpInfo.setBegainTime(employeeProjectExperience.getBegainTime());
            employeeProjectExpInfo.setEndTime(employeeProjectExperience.getEndTime());
            employeeProjectExpInfo.setWorkDescribe(employeeProjectExperience.getWorkDescribe());
            employeeProjectExpInfo.setInputUserid(employeeProjectExperience.getInputUserid());
            employeeProjectExpInfo.setInputTime(employeeProjectExperience.getInputTime());
            employeeProjectExpInfo.setInputOrgId(employeeProjectExperience.getInputOrgId());
            employeeProjectExpInfo.setUpdateuserid(employeeProjectExperience.getUpdateuserid());
            employeeProjectExpInfo.setUpdatetime(employeeProjectExperience.getUpdatetime());
            employeeProjectExpInfo.setUpdateorgid(employeeProjectExperience.getUpdateorgid());
            return employeeProjectExpInfo;
        }

        return null;
    }

    /**
     * 员工项目经历Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeProjectExpInfoSave(@Valid EmployeeProjectExpInfoSaveReq employeeProjectExpInfoSaveReq) {
        employeeProjectExpInfoSaveAction(employeeProjectExpInfoSaveReq);
    }
    /**
     * 员工项目经历Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeProjectExpInfoSaveAction(EmployeeProjectExpInfo employeeProjectExpInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeProjectExpInfo!=null){
        }
        bomanager.updateDB();
    }
}
