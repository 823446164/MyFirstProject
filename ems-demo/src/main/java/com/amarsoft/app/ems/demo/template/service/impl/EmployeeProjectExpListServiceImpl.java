package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeeProjectExpListService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpList;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListSaveReq;
import com.amarsoft.app.ems.demo.entity.EmployeeProjectExperience;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListDeleteReq;

/**
 * 员工项目经历ListService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeProjectExpListServiceImpl implements EmployeeProjectExpListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeProjectExpListReqQuery implements RequestQuery<EmployeeProjectExpListQueryReq> {
        @Override
        public Query apply(EmployeeProjectExpListQueryReq employeeProjectExpListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeProjectExpListQueryReq, EmployeeProjectExpList.class);
            
            String sql = "select EPE.serialNo as serialNo,EPE.employeeNo as employeeNo,EPE.projectName as projectName,EPE.projectMangerId as projectMangerId,EPE.employeeJob as employeeJob,EPE.begainTime as begainTime,EPE.endTime as endTime,EPE.workDescribe as workDescribe,EPE.inputUserid as inputUserid,EPE.inputTime as inputTime,EPE.inputOrgId as inputOrgId,EPE.updateuserid as updateuserid,EPE.updatetime as updatetime,EPE.updateorgid as updateorgid"
                +" from EMPLOYEE_PROJECT_EXPERIENCE_JFAN5 EPE"
                +" where 1=1 and EPE.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeProjectExpListQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeProjectExpListRspConvert implements Convert<EmployeeProjectExpList> {
        @Override
        public EmployeeProjectExpList apply(BusinessObject bo) {
            EmployeeProjectExpList temp = new EmployeeProjectExpList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setProjectName(bo.getString("ProjectName"));
            temp.setProjectMangerId(bo.getString("ProjectMangerId"));
            temp.setEmployeeJob(bo.getString("EmployeeJob"));
            temp.setBegainTime(bo.getString("BegainTime"));
            temp.setEndTime(bo.getString("EndTime"));
            temp.setWorkDescribe(bo.getString("WorkDescribe"));
            temp.setInputUserid(bo.getString("InputUserid"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateuserid(bo.getString("Updateuserid"));
            temp.setUpdatetime(bo.getString("Updatetime"));
            temp.setUpdateorgid(bo.getString("Updateorgid"));
            
            return temp;
        }
    }

    /**
     * 员工项目经历List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeProjectExpListQueryRsp employeeProjectExpListQuery(@Valid EmployeeProjectExpListQueryReq employeeProjectExpListQueryReq) {
        EmployeeProjectExpListQueryRsp employeeProjectExpListQueryRsp = new EmployeeProjectExpListQueryRsp();
        
        Query query = new EmployeeProjectExpListReqQuery().apply(employeeProjectExpListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeProjectExpListRspConvert convert = new EmployeeProjectExpListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeProjectExpListQueryReq.getBegin(), employeeProjectExpListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeProjectExpList> employeeProjectExpLists = new ArrayList<EmployeeProjectExpList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeProjectExpLists.add(convert.apply(bo));
            }
            employeeProjectExpListQueryRsp.setEmployeeProjectExpLists(employeeProjectExpLists);
        }
        employeeProjectExpListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeProjectExpListQueryRsp;
    }

    /**
     * 员工项目经历List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeProjectExpListSave(@Valid EmployeeProjectExpListSaveReq employeeProjectExpListSaveReq) {
        employeeProjectExpListSaveAction(employeeProjectExpListSaveReq.getEmployeeProjectExpLists());
    }
    /**
     * 员工项目经历List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeProjectExpListSaveAction(List<EmployeeProjectExpList> employeeProjectExpLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeProjectExpLists!=null){
            for(EmployeeProjectExpList employeeProjectExpListTmp :employeeProjectExpLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工项目经历List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeProjectExpListDelete(@Valid EmployeeProjectExpListDeleteReq employeeProjectExpListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeProjectExperience employeeProjectExperience=bomanager.keyLoadBusinessObject(EmployeeProjectExperience.class, employeeProjectExpListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeProjectExperience);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
