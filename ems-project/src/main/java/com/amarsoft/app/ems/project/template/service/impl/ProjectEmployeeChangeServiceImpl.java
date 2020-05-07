package com.amarsoft.app.ems.project.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.project.template.service.ProjectEmployeeChangeService;
import com.amarsoft.app.ems.project.entity.ProjectManagerChange;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChange;

/**
 * 项目组人员变更信息Service实现类
 * @author hpli
 */
@Slf4j
@Service
public class ProjectEmployeeChangeServiceImpl implements ProjectEmployeeChangeService{
    /**
     * 项目组人员变更信息单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ProjectEmployeeChangeQueryRsp projectEmployeeChangeQuery(@Valid ProjectEmployeeChangeQueryReq projectEmployeeChangeQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        ProjectManagerChange projectManagerChange = bomanager.loadBusinessObject(ProjectManagerChange.class,"changeNo",projectEmployeeChangeQueryReq.getChangeNo());
        if(projectManagerChange!=null){
            ProjectEmployeeChangeQueryRsp projectEmployeeChange = new ProjectEmployeeChangeQueryRsp();
            projectEmployeeChange.setSerialNo(projectManagerChange.getSerialNo());
            projectEmployeeChange.setChangeNo(projectManagerChange.getChangeNo());
            projectEmployeeChange.setChangeType(projectManagerChange.getChangeType());
            projectEmployeeChange.setApplyReason(projectManagerChange.getApplyReason());
            return projectEmployeeChange;
        }

        return null;
    }

    /**
     * 项目组人员变更信息单记录保存
     * @param request
     * @return
     */
    @Override
    public void projectEmployeeChangeSave(@Valid ProjectEmployeeChangeSaveReq projectEmployeeChangeSaveReq) {
        projectEmployeeChangeSaveAction(projectEmployeeChangeSaveReq);
    }
    /**
     * 项目组人员变更信息单记录保存
     * @param
     * @return
     */
    @Transactional
    public void projectEmployeeChangeSaveAction(ProjectEmployeeChange projectEmployeeChange){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(projectEmployeeChange!=null){
        }
        bomanager.updateDB();
    }
}
