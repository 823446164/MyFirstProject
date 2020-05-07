package com.amarsoft.app.ems.project.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.project.template.service.ProjectEmployeeListService;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeList;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListSaveReq;
import com.amarsoft.app.ems.project.entity.Project;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListDeleteReq;

/**
 * 项目组人员信息Service实现类
 * @author hpli
 */
@Slf4j
@Service
public class ProjectEmployeeListServiceImpl implements ProjectEmployeeListService{
    /**
                   * 查询结果集
     */
    public static class ProjectEmployeeListReqQuery implements RequestQuery<ProjectEmployeeListQueryReq> {
        @Override
        public Query apply(ProjectEmployeeListQueryReq projectEmployeeListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(projectEmployeeListQueryReq, ProjectEmployeeList.class);
            
            String sql = "select EP.serialNo as serialNo,EP.projectName as projectName,EP.projectDirector as projectDirector,EP.projectManager as projectManager,EP.stayCity as stayCity"
                +" from PROJECT_INFO EP"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class ProjectEmployeeListRspConvert implements Convert<ProjectEmployeeList> {
        @Override
        public ProjectEmployeeList apply(BusinessObject bo) {
            ProjectEmployeeList temp = new ProjectEmployeeList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setProjectName(bo.getString("ProjectName"));
            temp.setProjectDirector(bo.getString("ProjectDirector"));
            temp.setProjectManager(bo.getString("ProjectManager"));
            temp.setStayCity(bo.getString("StayCity"));
            
            return temp;
        }
    }

    /**
     * 项目组人员信息多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ProjectEmployeeListQueryRsp projectEmployeeListQuery(@Valid ProjectEmployeeListQueryReq projectEmployeeListQueryReq) {
        ProjectEmployeeListQueryRsp projectEmployeeListQueryRsp = new ProjectEmployeeListQueryRsp();
        
        Query query = new ProjectEmployeeListReqQuery().apply(projectEmployeeListQueryReq);
        String fullsql = query.getSql();
        
        ProjectEmployeeListRspConvert convert = new ProjectEmployeeListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(projectEmployeeListQueryReq.getBegin(), projectEmployeeListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<ProjectEmployeeList> projectEmployeeLists = new ArrayList<ProjectEmployeeList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                projectEmployeeLists.add(convert.apply(bo));
            }
            projectEmployeeListQueryRsp.setProjectEmployeeLists(projectEmployeeLists);
        }
        projectEmployeeListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return projectEmployeeListQueryRsp;
    }

    /**
     * 项目组人员信息多记录保存
     * @param request
     * @return
     */
    @Override
    public void projectEmployeeListSave(@Valid ProjectEmployeeListSaveReq projectEmployeeListSaveReq) {
        projectEmployeeListSaveAction(projectEmployeeListSaveReq.getProjectEmployeeLists());
    }
    /**
     * 项目组人员信息多记录保存
     * @param
     * @return
     */
    @Transactional
    public void projectEmployeeListSaveAction(List<ProjectEmployeeList> projectEmployeeLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(projectEmployeeLists!=null){
            for(ProjectEmployeeList projectEmployeeListTmp :projectEmployeeLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 项目组人员信息删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void projectEmployeeListDelete(@Valid ProjectEmployeeListDeleteReq projectEmployeeListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        Project project=bomanager.keyLoadBusinessObject(Project.class, projectEmployeeListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(project);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
