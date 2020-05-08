package com.amarsoft.app.ems.project.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListDeleteReq;

/**
 * 项目组人员信息Service接口
 * @author hpli
 */
public interface ProjectEmployeeListService {
    /**
     * 项目组人员信息查询
     * @param request
     * @return
     */
    public ProjectEmployeeListQueryRsp projectEmployeeListQuery(@Valid ProjectEmployeeListQueryReq projectEmployeeListQueryReq);

    /**
     * 项目组人员信息保存
     * @param request
     * @return
     */
    public void projectEmployeeListSave(@Valid ProjectEmployeeListSaveReq projectEmployeeListSaveReq);

    /**
     * 项目组人员信息删除
     * @param request
     * @return
     */
    public void projectEmployeeListDelete(@Valid ProjectEmployeeListDeleteReq projectEmployeeListDeleteReq);
}
