package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeStrongMemberListService;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberList;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListSaveReq;
import com.amarsoft.app.ems.project.entity.ProjectEmployee;
import com.amarsoft.app.ems.employee.entity.EmployeeInfo;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListDeleteReq;

/**
 * 培训项目参与人员列表Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeStrongMemberListServiceImpl implements EmployeeStrongMemberListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeStrongMemberListReqQuery implements RequestQuery<EmployeeStrongMemberListQueryReq> {
        @Override
        public Query apply(EmployeeStrongMemberListQueryReq employeeStrongMemberListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeStrongMemberListQueryReq, EmployeeStrongMemberList.class);
            
            String sql = "select PM.serialNo as serialNo,"
                +"EI.employeeNo as employeeNo,EI.employeeName as employeeName,EI.sex as sex,EI.nowRank as nowRank"
                +" from PROJECT_MEMEBR PM,EMPLOYEE_INFO EI"
                +" where 1=1 and PM.projectNo = :projectNo";
            return queryProperties.assembleSql(sql,"projectNo",employeeStrongMemberListQueryReq.getProjectNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeStrongMemberListRspConvert implements Convert<EmployeeStrongMemberList> {
        @Override
        public EmployeeStrongMemberList apply(BusinessObject bo) {
            EmployeeStrongMemberList temp = new EmployeeStrongMemberList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setSex(bo.getString("Sex"));
            temp.setNowRank(bo.getString("NowRank"));
            
            return temp;
        }
    }

    /**
     * 培训项目参与人员列表多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeStrongMemberListQueryRsp employeeStrongMemberListQuery(@Valid EmployeeStrongMemberListQueryReq employeeStrongMemberListQueryReq) {
        EmployeeStrongMemberListQueryRsp employeeStrongMemberListQueryRsp = new EmployeeStrongMemberListQueryRsp();
        
        Query query = new EmployeeStrongMemberListReqQuery().apply(employeeStrongMemberListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeStrongMemberListRspConvert convert = new EmployeeStrongMemberListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeStrongMemberListQueryReq.getBegin(), employeeStrongMemberListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeStrongMemberList> employeeStrongMemberLists = new ArrayList<EmployeeStrongMemberList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeStrongMemberLists.add(convert.apply(bo));
            }
            employeeStrongMemberListQueryRsp.setEmployeeStrongMemberLists(employeeStrongMemberLists);
        }
        employeeStrongMemberListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeStrongMemberListQueryRsp;
    }

    /**
     * 培训项目参与人员列表多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeStrongMemberListSave(@Valid EmployeeStrongMemberListSaveReq employeeStrongMemberListSaveReq) {
        employeeStrongMemberListSaveAction(employeeStrongMemberListSaveReq.getEmployeeStrongMemberLists());
    }
    /**
     * 培训项目参与人员列表多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeStrongMemberListSaveAction(List<EmployeeStrongMemberList> employeeStrongMemberLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeStrongMemberLists!=null){
            for(EmployeeStrongMemberList employeeStrongMemberListTmp :employeeStrongMemberLists){
                ProjectEmployee projectEmployee = bomanager.keyLoadBusinessObject(ProjectEmployee.class,employeeStrongMemberListTmp.getSerialNo());
                if(projectEmployee==null){
                    projectEmployee = new ProjectEmployee();
                    projectEmployee.generateKey();
                }
                EmployeeInfo employeeInfo = bomanager.keyLoadBusinessObject(EmployeeInfo.class,employeeStrongMemberListTmp.getEmployeeNo());
                if(employeeInfo==null){
                    employeeInfo = new EmployeeInfo();
                    employeeInfo.generateKey();
                }
                employeeInfo.setEmployeeName(employeeStrongMemberListTmp.getEmployeeName());
                employeeInfo.setSex(employeeStrongMemberListTmp.getSex());
                employeeInfo.setNowRank(employeeStrongMemberListTmp.getNowRank());
                bomanager.updateBusinessObject(projectEmployee);
                bomanager.updateBusinessObject(employeeInfo);
            }
        }
        bomanager.updateDB();
    }


    /**
     * 培训项目参与人员列表删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeStrongMemberListDelete(@Valid EmployeeStrongMemberListDeleteReq employeeStrongMemberListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        ProjectEmployee projectEmployee=bomanager.keyLoadBusinessObject(ProjectEmployee.class, employeeStrongMemberListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(projectEmployee);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
