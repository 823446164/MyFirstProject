package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeeDevelopTargetListService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetList;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListSaveReq;
import com.amarsoft.app.ems.demo.entity.EmployeeDevelopTarget;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListDeleteReq;

/**
 * 员工成长目标跟踪ListService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeDevelopTargetListServiceImpl implements EmployeeDevelopTargetListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeDevelopTargetListReqQuery implements RequestQuery<EmployeeDevelopTargetListQueryReq> {
        @Override
        public Query apply(EmployeeDevelopTargetListQueryReq employeeDevelopTargetListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeDevelopTargetListQueryReq, EmployeeDevelopTargetList.class);
            
            String sql = "select EDT.serialNo as serialNo,EDT.employeeNo as employeeNo,EDT.rankNo as rankNo,EDT.designTime as designTime,EDT.targetDescribe as targetDescribe,EDT.describeTime as describeTime,EDT.designerId as designerId,EDT.record as record,EDT.implStatus as implStatus,EDT.inputUserId as inputUserId,EDT.inputTime as inputTime,EDT.inputOrgId as inputOrgId,EDT.updateUserId as updateUserId,EDT.updateTime as updateTime,EDT.updateOrgId as updateOrgId"
                +" from EMPLOYEE_DEVELOP_TARGET_JFAN5 EDT"
                +" where 1=1 and EDT.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeDevelopTargetListQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeDevelopTargetListRspConvert implements Convert<EmployeeDevelopTargetList> {
        @Override
        public EmployeeDevelopTargetList apply(BusinessObject bo) {
            EmployeeDevelopTargetList temp = new EmployeeDevelopTargetList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setRankNo(bo.getString("RankNo"));
            temp.setDesignTime(bo.getString("DesignTime"));
            temp.setTargetDescribe(bo.getString("TargetDescribe"));
            temp.setDescribeTime(bo.getString("DescribeTime"));
            temp.setDesignerId(bo.getString("DesignerId"));
            temp.setRecord(bo.getString("Record"));
            temp.setImplStatus(bo.getString("ImplStatus"));
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
     * 员工成长目标跟踪List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeDevelopTargetListQueryRsp employeeDevelopTargetListQuery(@Valid EmployeeDevelopTargetListQueryReq employeeDevelopTargetListQueryReq) {
        EmployeeDevelopTargetListQueryRsp employeeDevelopTargetListQueryRsp = new EmployeeDevelopTargetListQueryRsp();
        
        Query query = new EmployeeDevelopTargetListReqQuery().apply(employeeDevelopTargetListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeDevelopTargetListRspConvert convert = new EmployeeDevelopTargetListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeDevelopTargetListQueryReq.getBegin(), employeeDevelopTargetListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeDevelopTargetList> employeeDevelopTargetLists = new ArrayList<EmployeeDevelopTargetList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeDevelopTargetLists.add(convert.apply(bo));
            }
            employeeDevelopTargetListQueryRsp.setEmployeeDevelopTargetLists(employeeDevelopTargetLists);
        }
        employeeDevelopTargetListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeDevelopTargetListQueryRsp;
    }

    /**
     * 员工成长目标跟踪List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeDevelopTargetListSave(@Valid EmployeeDevelopTargetListSaveReq employeeDevelopTargetListSaveReq) {
        employeeDevelopTargetListSaveAction(employeeDevelopTargetListSaveReq.getEmployeeDevelopTargetLists());
    }
    /**
     * 员工成长目标跟踪List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeDevelopTargetListSaveAction(List<EmployeeDevelopTargetList> employeeDevelopTargetLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeDevelopTargetLists!=null){
            for(EmployeeDevelopTargetList employeeDevelopTargetListTmp :employeeDevelopTargetLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工成长目标跟踪List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeDevelopTargetListDelete(@Valid EmployeeDevelopTargetListDeleteReq employeeDevelopTargetListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeDevelopTarget employeeDevelopTarget=bomanager.keyLoadBusinessObject(EmployeeDevelopTarget.class, employeeDevelopTargetListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeDevelopTarget);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
