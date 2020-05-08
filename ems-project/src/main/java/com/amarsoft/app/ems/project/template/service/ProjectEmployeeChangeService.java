package com.amarsoft.app.ems.project.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeSaveReq;

/**
 * 项目组人员变更信息Service接口
 * @author hpli
 */
public interface ProjectEmployeeChangeService {
    /**
     * 项目组人员变更信息查询
     * @param request
     * @return
     */
    public ProjectEmployeeChangeQueryRsp projectEmployeeChangeQuery(@Valid ProjectEmployeeChangeQueryReq projectEmployeeChangeQueryReq);

    /**
     * 项目组人员变更信息保存
     * @param request
     * @return
     */
    public void projectEmployeeChangeSave(@Valid ProjectEmployeeChangeSaveReq projectEmployeeChangeSaveReq);
}
