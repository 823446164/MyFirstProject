package com.amarsoft.app.ems.project.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.project.template.service.ProjectEmployeeService;
import com.amarsoft.app.ems.project.entity.Project;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployee;

/**
 * 项目组人员信息Service实现类
 * @author hpli
 */
@Slf4j
@Service
public class ProjectEmployeeServiceImpl implements ProjectEmployeeService{
    /**
     * 项目组人员信息单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ProjectEmployeeQueryRsp projectEmployeeQuery(@Valid ProjectEmployeeQueryReq projectEmployeeQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        Project project = bomanager.loadBusinessObject(Project.class,"serialNo",projectEmployeeQueryReq.getSerialNo());
        if(project!=null){
            ProjectEmployeeQueryRsp projectEmployee = new ProjectEmployeeQueryRsp();
            projectEmployee.setSerialNo(project.getSerialNo());
            projectEmployee.setProjectName(project.getProjectName());
            projectEmployee.setProjectDirector(project.getProjectDirector());
            projectEmployee.setProjectManager(project.getProjectManager());
            projectEmployee.setStayCity(project.getStayCity());
            return projectEmployee;
        }

        return null;
    }

    /**
     * 项目组人员信息单记录保存
     * @param request
     * @return
     */
    @Override
    public void projectEmployeeSave(@Valid ProjectEmployeeSaveReq projectEmployeeSaveReq) {
        projectEmployeeSaveAction(projectEmployeeSaveReq);
    }
    /**
     * 项目组人员信息单记录保存
     * @param
     * @return
     */
    @Transactional
    public void projectEmployeeSaveAction(ProjectEmployee projectEmployee){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(projectEmployee!=null){
        }
        bomanager.updateDB();
    }
}
