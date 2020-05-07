package com.amarsoft.app.ems.project.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListDeleteReq;

/**
 * 项目组人员变更信息Service接口
 * @author hpli
 */
public interface ProjectEmployeeChangeListService {
    /**
     * 项目组人员变更信息查询
     * @param request
     * @return
     */
    public ProjectEmployeeChangeListQueryRsp projectEmployeeChangeListQuery(@Valid ProjectEmployeeChangeListQueryReq projectEmployeeChangeListQueryReq);

    /**
     * 项目组人员变更信息保存
     * @param request
     * @return
     */
    public void projectEmployeeChangeListSave(@Valid ProjectEmployeeChangeListSaveReq projectEmployeeChangeListSaveReq);

    /**
     * 项目组人员变更信息删除
     * @param request
     * @return
     */
    public void projectEmployeeChangeListDelete(@Valid ProjectEmployeeChangeListDeleteReq projectEmployeeChangeListDeleteReq);
}
