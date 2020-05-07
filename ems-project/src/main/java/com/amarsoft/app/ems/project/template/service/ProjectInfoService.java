package com.amarsoft.app.ems.project.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoSaveReq;

/**
 * 项目列表Service接口
 * @author hpli
 */
public interface ProjectInfoService {
    /**
     * 项目列表查询
     * @param request
     * @return
     */
    public ProjectInfoQueryRsp projectInfoQuery(@Valid ProjectInfoQueryReq projectInfoQueryReq);

    /**
     * 项目列表保存
     * @param request
     * @return
     */
    public void projectInfoSave(@Valid ProjectInfoSaveReq projectInfoSaveReq);
}
