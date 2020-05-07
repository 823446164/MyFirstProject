package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeBelongChangeListDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoSaveReq;
import com.amarsoft.app.ems.employee.entity.EmployeeBelongChange;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoDeleteReq;

/**
 * 团队调整申请ListService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeBelongChangeListDtoServiceImpl implements EmployeeBelongChangeListDtoService{
    /**
                   * 查询结果集
     */
    public static class EmployeeBelongChangeListDtoReqQuery implements RequestQuery<EmployeeBelongChangeListDtoQueryReq> {
        @Override
        public Query apply(EmployeeBelongChangeListDtoQueryReq employeeBelongChangeListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeBelongChangeListDtoQueryReq, EmployeeBelongChangeListDto.class);
            
            String sql = "select EBC.serialNo as serialNo,EBC.teamNo as teamNo,EBC.employeeNo as employeeNo,EBC.employeeName as employeeName,EBC.employeeAcct as employeeAcct,EBC.objectType as objectType,EBC.deptId as deptId,EBC.deptName as deptName,EBC.beforeTeam as beforeTeam,EBC.afterTeam as afterTeam,EBC.changeManager as changeManager,EBC.adjustReason as adjustReason,EBC.inputUserId as inputUserId,EBC.inputTime as inputTime,EBC.inputOrgId as inputOrgId,EBC.updateUserId as updateUserId,EBC.updateTime as updateTime,EBC.updateOrgId as updateOrgId"
                +" from EMPLOYEE_BELONG_CHANGE EBC"
                +" where 1=1 and EBC.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeBelongChangeListDtoQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeBelongChangeListDtoRspConvert implements Convert<EmployeeBelongChangeListDto> {
        @Override
        public EmployeeBelongChangeListDto apply(BusinessObject bo) {
            EmployeeBelongChangeListDto temp = new EmployeeBelongChangeListDto();
                
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
    public EmployeeBelongChangeListDtoQueryRsp employeeBelongChangeListDtoQuery(@Valid EmployeeBelongChangeListDtoQueryReq employeeBelongChangeListDtoQueryReq) {
        EmployeeBelongChangeListDtoQueryRsp employeeBelongChangeListDtoQueryRsp = new EmployeeBelongChangeListDtoQueryRsp();
        
        Query query = new EmployeeBelongChangeListDtoReqQuery().apply(employeeBelongChangeListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeBelongChangeListDtoRspConvert convert = new EmployeeBelongChangeListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeBelongChangeListDtoQueryReq.getBegin(), employeeBelongChangeListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeBelongChangeListDto> employeeBelongChangeListDtos = new ArrayList<EmployeeBelongChangeListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeBelongChangeListDtos.add(convert.apply(bo));
            }
            employeeBelongChangeListDtoQueryRsp.setEmployeeBelongChangeListDtos(employeeBelongChangeListDtos);
        }
        employeeBelongChangeListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeBelongChangeListDtoQueryRsp;
    }

    /**
     * 团队调整申请List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeBelongChangeListDtoSave(@Valid EmployeeBelongChangeListDtoSaveReq employeeBelongChangeListDtoSaveReq) {
        employeeBelongChangeListDtoSaveAction(employeeBelongChangeListDtoSaveReq.getEmployeeBelongChangeListDtos());
    }
    /**
     * 团队调整申请List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeBelongChangeListDtoSaveAction(List<EmployeeBelongChangeListDto> employeeBelongChangeListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeBelongChangeListDtos!=null){
            for(EmployeeBelongChangeListDto employeeBelongChangeListDtoTmp :employeeBelongChangeListDtos){
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
    public void employeeBelongChangeListDtoDelete(@Valid EmployeeBelongChangeListDtoDeleteReq employeeBelongChangeListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeBelongChange employeeBelongChange=bomanager.keyLoadBusinessObject(EmployeeBelongChange.class, employeeBelongChangeListDtoDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeBelongChange);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
