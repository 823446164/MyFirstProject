package com.amarsoft.app.ems.project.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListDeleteReq;

/**
 * 项目列表Service接口
 * @author hpli
 */
public interface ProjectListService {
    /**
     * 项目列表查询
     * @param request
     * @return
     */
    public ProjectListQueryRsp projectListQuery(@Valid ProjectListQueryReq projectListQueryReq);

    /**
     * 项目列表保存
     * @param request
     * @return
     */
    public void projectListSave(@Valid ProjectListSaveReq projectListSaveReq);

    /**
     * 项目列表删除
     * @param request
     * @return
     */
    public void projectListDelete(@Valid ProjectListDeleteReq projectListDeleteReq);
}
