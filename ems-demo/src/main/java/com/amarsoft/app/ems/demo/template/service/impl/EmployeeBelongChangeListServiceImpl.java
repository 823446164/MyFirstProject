package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeeBelongChangeListService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeList;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListSaveReq;
import com.amarsoft.app.ems.demo.entity.EmployeeBelongChange;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListDeleteReq;

/**
 * 团队调整申请ListService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeBelongChangeListServiceImpl implements EmployeeBelongChangeListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeBelongChangeListReqQuery implements RequestQuery<EmployeeBelongChangeListQueryReq> {
        @Override
        public Query apply(EmployeeBelongChangeListQueryReq employeeBelongChangeListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeBelongChangeListQueryReq, EmployeeBelongChangeList.class);
            
            String sql = "select EBC.serialNo as serialNo,EBC.teamNo as teamNo,EBC.employeeNo as employeeNo,EBC.employeeName as employeeName,EBC.employeeAcct as employeeAcct,EBC.objectType as objectType,EBC.deptId as deptId,EBC.deptName as deptName,EBC.beforeTeam as beforeTeam,EBC.afterTeam as afterTeam,EBC.changeManager as changeManager,EBC.adjustReason as adjustReason,EBC.inputUserId as inputUserId,EBC.inputTime as inputTime,EBC.inputOrgId as inputOrgId,EBC.updateUserId as updateUserId,EBC.updateTime as updateTime,EBC.updateOrgId as updateOrgId"
                +" from EMPLOYEE_BELONG_CHANGE_JFAN5 EBC"
                +" where 1=1 and EBC.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeBelongChangeListQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeBelongChangeListRspConvert implements Convert<EmployeeBelongChangeList> {
        @Override
        public EmployeeBelongChangeList apply(BusinessObject bo) {
            EmployeeBelongChangeList temp = new EmployeeBelongChangeList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setTeamNo(bo.getString("TeamNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setEmployeeAcct(bo.getString("EmployeeAcct"));
            temp.setObjectType(bo.getString("ObjectType"));
            temp.setDeptId(bo.getString("DeptId"));
            temp.setDeptName(bo.getString("DeptName"));
            temp.setBeforeTeam(bo.getString("BeforeTeam"));
            temp.setAfterTeam(bo.getString("AfterTeam"));
            temp.setChangeManager(bo.getString("ChangeManager"));
            temp.setAdjustReason(bo.getString("AdjustReason"));
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
     * 团队调整申请List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeBelongChangeListQueryRsp employeeBelongChangeListQuery(@Valid EmployeeBelongChangeListQueryReq employeeBelongChangeListQueryReq) {
        EmployeeBelongChangeListQueryRsp employeeBelongChangeListQueryRsp = new EmployeeBelongChangeListQueryRsp();
        
        Query query = new EmployeeBelongChangeListReqQuery().apply(employeeBelongChangeListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeBelongChangeListRspConvert convert = new EmployeeBelongChangeListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeBelongChangeListQueryReq.getBegin(), employeeBelongChangeListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeBelongChangeList> employeeBelongChangeLists = new ArrayList<EmployeeBelongChangeList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeBelongChangeLists.add(convert.apply(bo));
            }
            employeeBelongChangeListQueryRsp.setEmployeeBelongChangeLists(employeeBelongChangeLists);
        }
        employeeBelongChangeListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeBelongChangeListQueryRsp;
    }

    /**
     * 团队调整申请List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeBelongChangeListSave(@Valid EmployeeBelongChangeListSaveReq employeeBelongChangeListSaveReq) {
        employeeBelongChangeListSaveAction(employeeBelongChangeListSaveReq.getEmployeeBelongChangeLists());
    }
    /**
     * 团队调整申请List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeBelongChangeListSaveAction(List<EmployeeBelongChangeList> employeeBelongChangeLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeBelongChangeLists!=null){
            for(EmployeeBelongChangeList employeeBelongChangeListTmp :employeeBelongChangeLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 团队调整申请List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeBelongChangeListDelete(@Valid EmployeeBelongChangeListDeleteReq employeeBelongChangeListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeBelongChange employeeBelongChange=bomanager.keyLoadBusinessObject(EmployeeBelongChange.class, employeeBelongChangeListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeBelongChange);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
