package com.amarsoft.app.ems.project.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeSaveReq;

/**
 * 项目组人员信息Service接口
 * @author hpli
 */
public interface ProjectEmployeeService {
    /**
     * 项目组人员信息查询
     * @param request
     * @return
     */
    public ProjectEmployeeQueryRsp projectEmployeeQuery(@Valid ProjectEmployeeQueryReq projectEmployeeQueryReq);

    /**
     * 项目组人员信息保存
     * @param request
     * @return
     */
    public void projectEmployeeSave(@Valid ProjectEmployeeSaveReq projectEmployeeSaveReq);
}
