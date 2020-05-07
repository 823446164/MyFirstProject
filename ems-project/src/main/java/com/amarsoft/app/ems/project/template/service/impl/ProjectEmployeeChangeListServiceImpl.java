package com.amarsoft.app.ems.project.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.project.template.service.ProjectEmployeeChangeListService;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeList;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListSaveReq;
import com.amarsoft.app.ems.project.entity.ProjectManagerChange;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListDeleteReq;

/**
 * 项目组人员变更信息Service实现类
 * @author hpli
 */
@Slf4j
@Service
public class ProjectEmployeeChangeListServiceImpl implements ProjectEmployeeChangeListService{
    /**
                   * 查询结果集
     */
    public static class ProjectEmployeeChangeListReqQuery implements RequestQuery<ProjectEmployeeChangeListQueryReq> {
        @Override
        public Query apply(ProjectEmployeeChangeListQueryReq projectEmployeeChangeListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(projectEmployeeChangeListQueryReq, ProjectEmployeeChangeList.class);
            
            String sql = "select PMC.serialNo as serialNo,PMC.changeNo as changeNo,PMC.changeType as changeType,PMC.applyReason as applyReason"
                +" from PROJECT_MEMBER_CHANGE PMC"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class ProjectEmployeeChangeListRspConvert implements Convert<ProjectEmployeeChangeList> {
        @Override
        public ProjectEmployeeChangeList apply(BusinessObject bo) {
            ProjectEmployeeChangeList temp = new ProjectEmployeeChangeList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setChangeNo(bo.getString("ChangeNo"));
            temp.setChangeType(bo.getString("ChangeType"));
            temp.setApplyReason(bo.getString("ApplyReason"));
            
            return temp;
        }
    }

    /**
     * 项目组人员变更信息多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ProjectEmployeeChangeListQueryRsp projectEmployeeChangeListQuery(@Valid ProjectEmployeeChangeListQueryReq projectEmployeeChangeListQueryReq) {
        ProjectEmployeeChangeListQueryRsp projectEmployeeChangeListQueryRsp = new ProjectEmployeeChangeListQueryRsp();
        
        Query query = new ProjectEmployeeChangeListReqQuery().apply(projectEmployeeChangeListQueryReq);
        String fullsql = query.getSql();
        
        ProjectEmployeeChangeListRspConvert convert = new ProjectEmployeeChangeListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(projectEmployeeChangeListQueryReq.getBegin(), projectEmployeeChangeListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<ProjectEmployeeChangeList> projectEmployeeChangeLists = new ArrayList<ProjectEmployeeChangeList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                projectEmployeeChangeLists.add(convert.apply(bo));
            }
            projectEmployeeChangeListQueryRsp.setProjectEmployeeChangeLists(projectEmployeeChangeLists);
        }
        projectEmployeeChangeListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return projectEmployeeChangeListQueryRsp;
    }

    /**
     * 项目组人员变更信息多记录保存
     * @param request
     * @return
     */
    @Override
    public void projectEmployeeChangeListSave(@Valid ProjectEmployeeChangeListSaveReq projectEmployeeChangeListSaveReq) {
        projectEmployeeChangeListSaveAction(projectEmployeeChangeListSaveReq.getProjectEmployeeChangeLists());
    }
    /**
     * 项目组人员变更信息多记录保存
     * @param
     * @return
     */
    @Transactional
    public void projectEmployeeChangeListSaveAction(List<ProjectEmployeeChangeList> projectEmployeeChangeLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(projectEmployeeChangeLists!=null){
            for(ProjectEmployeeChangeList projectEmployeeChangeListTmp :projectEmployeeChangeLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 项目组人员变更信息删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void projectEmployeeChangeListDelete(@Valid ProjectEmployeeChangeListDeleteReq projectEmployeeChangeListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        ProjectManagerChange projectManagerChange=bomanager.keyLoadBusinessObject(ProjectManagerChange.class, projectEmployeeChangeListDeleteReq.getChangeNo());
        bomanager.deleteBusinessObject(projectManagerChange);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
