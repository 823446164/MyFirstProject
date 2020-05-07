package com.amarsoft.app.ems.project.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.project.template.service.ProjectInfoService;
import com.amarsoft.app.ems.project.entity.Project;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfo;
import com.amarsoft.amps.arpe.util.DateHelper;

/**
 * 项目列表Service实现类
 * @author hpli
 */
@Slf4j
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService{
    /**
     * 项目列表单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ProjectInfoQueryRsp projectInfoQuery(@Valid ProjectInfoQueryReq projectInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        Project project = bomanager.loadBusinessObject(Project.class,"projectNo",projectInfoQueryReq.getProjectNo());
        if(project!=null){
            ProjectInfoQueryRsp projectInfo = new ProjectInfoQueryRsp();
            projectInfo.setSerialNo(project.getSerialNo());
            projectInfo.setProjectName(project.getProjectName());
            projectInfo.setProjectDirector(project.getProjectDirector());
            projectInfo.setCustomerManager(project.getCustomerManager());
            projectInfo.setProjectManager(project.getProjectManager());
            projectInfo.setCreateTime(project.getCreateTime());
            projectInfo.setAcceptPassTime(project.getAcceptPassTime());
            projectInfo.setCustomer(project.getCustomer());
            projectInfo.setConsultant(project.getConsultant());
            projectInfo.setQaManager(project.getQaManager());
            projectInfo.setProjetClassification(project.getProjetClassification());
            projectInfo.setProjectType(project.getProjectType());
            projectInfo.setSignContract(project.getSignContract());
            projectInfo.setStatus(project.getStatus());
            projectInfo.setNowPhase(project.getNowPhase());
            projectInfo.setStayCity(project.getStayCity());
            projectInfo.setStartTime(project.getStartTime());
            projectInfo.setRealendTime(project.getRealendTime());
            projectInfo.setSaveStatus(project.getSaveStatus());
            projectInfo.setInputUserId(project.getInputUserId());
            projectInfo.setInputTime(project.getInputTime());
            projectInfo.setInputOrgId(project.getInputOrgId());
            projectInfo.setUpdateUserId(project.getUpdateUserId());
            projectInfo.setUpdateTime(project.getUpdateTime());
            projectInfo.setUpdateOrgId(project.getUpdateOrgId());
            return projectInfo;
        }

        return null;
    }

    /**
     * 项目列表单记录保存
     * @param request
     * @return
     */
    @Override
    public void projectInfoSave(@Valid ProjectInfoSaveReq projectInfoSaveReq) {
        projectInfoSaveAction(projectInfoSaveReq);
    }
    /**
     * 项目列表单记录保存
     * @param
     * @return
     */
    @Transactional
    public void projectInfoSaveAction(ProjectInfo projectInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(projectInfo!=null){
            Project project = bomanager.keyLoadBusinessObject(Project.class,projectInfo.getSerialNo());
            if(project==null){
                project = new Project();
                project.generateKey();
            }
            project.setProjectName(projectInfo.getProjectName());
            project.setProjectDirector(projectInfo.getProjectDirector());
            project.setCustomerManager(projectInfo.getCustomerManager());
            project.setProjectManager(projectInfo.getProjectManager());
            project.setCreateTime(DateHelper.getDate(projectInfo.getCreateTime()));
            project.setAcceptPassTime(DateHelper.getDate(projectInfo.getAcceptPassTime()));
            project.setCustomer(projectInfo.getCustomer());
            project.setConsultant(projectInfo.getConsultant());
            project.setQaManager(projectInfo.getQaManager());
            project.setProjetClassification(projectInfo.getProjetClassification());
            project.setProjectType(projectInfo.getProjectType());
            project.setSignContract(projectInfo.getSignContract());
            project.setStatus(projectInfo.getStatus());
            project.setNowPhase(projectInfo.getNowPhase());
            project.setStayCity(projectInfo.getStayCity());
            project.setStartTime(DateHelper.getDate(projectInfo.getStartTime()));
            project.setRealendTime(DateHelper.getDate(projectInfo.getRealendTime()));
            project.setSaveStatus(projectInfo.getSaveStatus());
            project.setInputUserId(projectInfo.getInputUserId());
            project.setInputTime(DateHelper.getDateTime(projectInfo.getInputTime()));
            project.setInputOrgId(projectInfo.getInputOrgId());
            project.setUpdateUserId(projectInfo.getUpdateUserId());
            project.setUpdateTime(DateHelper.getDateTime(projectInfo.getUpdateTime()));
            project.setUpdateOrgId(projectInfo.getUpdateOrgId());
            bomanager.updateBusinessObject(project);
        }
        bomanager.updateDB();
    }
}
