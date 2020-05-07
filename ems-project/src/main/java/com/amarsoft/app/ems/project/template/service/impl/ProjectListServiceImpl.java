package com.amarsoft.app.ems.project.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.project.template.service.ProjectListService;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectList;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListSaveReq;
import com.amarsoft.app.ems.project.entity.Project;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListDeleteReq;

/**
 * 项目列表Service实现类
 * @author hpli
 */
@Slf4j
@Service
public class ProjectListServiceImpl implements ProjectListService{
    /**
                   * 查询结果集
     */
    public static class ProjectListReqQuery implements RequestQuery<ProjectListQueryReq> {
        @Override
        public Query apply(ProjectListQueryReq projectListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(projectListQueryReq, ProjectList.class);
            
            String sql = "select EP.serialNo as serialNo,EP.projectName as projectName,EP.projectDirector as projectDirector,EP.customerManager as customerManager,EP.projectManager as projectManager,EP.createTime as createTime,EP.acceptPassTime as acceptPassTime,EP.customer as customer,EP.consultant as consultant,EP.qaManager as qaManager,EP.projetClassification as projetClassification,EP.projectType as projectType,EP.signContract as signContract,EP.status as status,EP.nowPhase as nowPhase,EP.stayCity as stayCity,EP.startTime as startTime,EP.realendTime as realendTime,EP.saveStatus as saveStatus,EP.inputUserId as inputUserId,EP.inputTime as inputTime,EP.inputOrgId as inputOrgId,EP.updateUserId as updateUserId,EP.updateTime as updateTime,EP.updateOrgId as updateOrgId"
                +" from PROJECT_INFO EP"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class ProjectListRspConvert implements Convert<ProjectList> {
        @Override
        public ProjectList apply(BusinessObject bo) {
            ProjectList temp = new ProjectList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setProjectName(bo.getString("ProjectName"));
            temp.setProjectDirector(bo.getString("ProjectDirector"));
            temp.setCustomerManager(bo.getString("CustomerManager"));
            temp.setProjectManager(bo.getString("ProjectManager"));
            temp.setCreateTime(bo.getString("CreateTime"));
            temp.setAcceptPassTime(bo.getString("AcceptPassTime"));
            temp.setCustomer(bo.getString("Customer"));
            temp.setConsultant(bo.getString("Consultant"));
            temp.setQaManager(bo.getString("QaManager"));
            temp.setProjetClassification(bo.getString("ProjetClassification"));
            temp.setProjectType(bo.getString("ProjectType"));
            temp.setSignContract(bo.getString("SignContract"));
            temp.setStatus(bo.getString("Status"));
            temp.setNowPhase(bo.getString("NowPhase"));
            temp.setStayCity(bo.getString("StayCity"));
            temp.setStartTime(bo.getString("StartTime"));
            temp.setRealendTime(bo.getString("RealendTime"));
            temp.setSaveStatus(bo.getString("SaveStatus"));
            temp.setInputUserId(bo.getString("InputUserId"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateUserId(bo.getString("UpdateUserId"));
            temp.setUpdateTime(bo.getString("UpdateTime"));
            temp.setUpdateOrgId(bo.getString("UpdateOrgId"));
            
            return temp;
        }
    }

    /**
     * 项目列表多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ProjectListQueryRsp projectListQuery(@Valid ProjectListQueryReq projectListQueryReq) {
        ProjectListQueryRsp projectListQueryRsp = new ProjectListQueryRsp();
        
        Query query = new ProjectListReqQuery().apply(projectListQueryReq);
        String fullsql = query.getSql();
        
        ProjectListRspConvert convert = new ProjectListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(projectListQueryReq.getBegin(), projectListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<ProjectList> projectLists = new ArrayList<ProjectList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                projectLists.add(convert.apply(bo));
            }
            projectListQueryRsp.setProjectLists(projectLists);
        }
        projectListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return projectListQueryRsp;
    }

    /**
     * 项目列表多记录保存
     * @param request
     * @return
     */
    @Override
    public void projectListSave(@Valid ProjectListSaveReq projectListSaveReq) {
        projectListSaveAction(projectListSaveReq.getProjectLists());
    }
    /**
     * 项目列表多记录保存
     * @param
     * @return
     */
    @Transactional
    public void projectListSaveAction(List<ProjectList> projectLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(projectLists!=null){
            for(ProjectList projectListTmp :projectLists){
                Project project = bomanager.keyLoadBusinessObject(Project.class,projectListTmp.getSerialNo());
                if(project==null){
                    project = new Project();
                    project.generateKey();
                }
                project.setProjectName(projectListTmp.getProjectName());
                project.setProjectDirector(projectListTmp.getProjectDirector());
                project.setCustomerManager(projectListTmp.getCustomerManager());
                project.setProjectManager(projectListTmp.getProjectManager());
                project.setCreateTime(DateHelper.getDate(projectListTmp.getCreateTime()));
                project.setAcceptPassTime(DateHelper.getDate(projectListTmp.getAcceptPassTime()));
                project.setCustomer(projectListTmp.getCustomer());
                project.setConsultant(projectListTmp.getConsultant());
                project.setQaManager(projectListTmp.getQaManager());
                project.setProjetClassification(projectListTmp.getProjetClassification());
                project.setProjectType(projectListTmp.getProjectType());
                project.setSignContract(projectListTmp.getSignContract());
                project.setStatus(projectListTmp.getStatus());
                project.setNowPhase(projectListTmp.getNowPhase());
                project.setStayCity(projectListTmp.getStayCity());
                project.setStartTime(DateHelper.getDate(projectListTmp.getStartTime()));
                project.setRealendTime(DateHelper.getDate(projectListTmp.getRealendTime()));
                project.setSaveStatus(projectListTmp.getSaveStatus());
                project.setInputUserId(projectListTmp.getInputUserId());
                project.setInputTime(DateHelper.getDateTime(projectListTmp.getInputTime()));
                project.setInputOrgId(projectListTmp.getInputOrgId());
                project.setUpdateUserId(projectListTmp.getUpdateUserId());
                project.setUpdateTime(DateHelper.getDateTime(projectListTmp.getUpdateTime()));
                project.setUpdateOrgId(projectListTmp.getUpdateOrgId());
                bomanager.updateBusinessObject(project);
            }
        }
        bomanager.updateDB();
    }


    /**
     * 项目列表删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void projectListDelete(@Valid ProjectListDeleteReq projectListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        Project project=bomanager.keyLoadBusinessObject(Project.class, projectListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(project);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
