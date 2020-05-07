package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeDevelopTargetListDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoSaveReq;
import com.amarsoft.app.ems.employee.entity.EmployeeDevelopTarget;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoDeleteReq;

/**
 * 员工成长目标跟踪ListService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeDevelopTargetListDtoServiceImpl implements EmployeeDevelopTargetListDtoService{
    /**
                   * 查询结果集
     */
    public static class EmployeeDevelopTargetListDtoReqQuery implements RequestQuery<EmployeeDevelopTargetListDtoQueryReq> {
        @Override
        public Query apply(EmployeeDevelopTargetListDtoQueryReq employeeDevelopTargetListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeDevelopTargetListDtoQueryReq, EmployeeDevelopTargetListDto.class);
            
            String sql = "select EDT.serialNo as serialNo,EDT.employeeNo as employeeNo,EDT.rankNo as rankNo,EDT.designTime as designTime,EDT.targetDescribe as targetDescribe,EDT.describeTime as describeTime,EDT.designerId as designerId,EDT.record as record,EDT.implStatus as implStatus,EDT.inputUserId as inputUserId,EDT.inputTime as inputTime,EDT.inputOrgId as inputOrgId,EDT.updateUserId as updateUserId,EDT.updateTime as updateTime,EDT.updateOrgId as updateOrgId"
                +" from EMPLOYEE_DEVELOP_TARGET EDT"
                +" where 1=1 and EDT.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeDevelopTargetListDtoQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeDevelopTargetListDtoRspConvert implements Convert<EmployeeDevelopTargetListDto> {
        @Override
        public EmployeeDevelopTargetListDto apply(BusinessObject bo) {
            EmployeeDevelopTargetListDto temp = new EmployeeDevelopTargetListDto();
                
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
    public EmployeeDevelopTargetListDtoQueryRsp employeeDevelopTargetListDtoQuery(@Valid EmployeeDevelopTargetListDtoQueryReq employeeDevelopTargetListDtoQueryReq) {
        EmployeeDevelopTargetListDtoQueryRsp employeeDevelopTargetListDtoQueryRsp = new EmployeeDevelopTargetListDtoQueryRsp();
        
        Query query = new EmployeeDevelopTargetListDtoReqQuery().apply(employeeDevelopTargetListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeDevelopTargetListDtoRspConvert convert = new EmployeeDevelopTargetListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeDevelopTargetListDtoQueryReq.getBegin(), employeeDevelopTargetListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeDevelopTargetListDto> employeeDevelopTargetListDtos = new ArrayList<EmployeeDevelopTargetListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeDevelopTargetListDtos.add(convert.apply(bo));
            }
            employeeDevelopTargetListDtoQueryRsp.setEmployeeDevelopTargetListDtos(employeeDevelopTargetListDtos);
        }
        employeeDevelopTargetListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeDevelopTargetListDtoQueryRsp;
    }

    /**
     * 员工成长目标跟踪List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeDevelopTargetListDtoSave(@Valid EmployeeDevelopTargetListDtoSaveReq employeeDevelopTargetListDtoSaveReq) {
        employeeDevelopTargetListDtoSaveAction(employeeDevelopTargetListDtoSaveReq.getEmployeeDevelopTargetListDtos());
    }
    /**
     * 员工成长目标跟踪List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeDevelopTargetListDtoSaveAction(List<EmployeeDevelopTargetListDto> employeeDevelopTargetListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeDevelopTargetListDtos!=null){
            for(EmployeeDevelopTargetListDto employeeDevelopTargetListDtoTmp :employeeDevelopTargetListDtos){
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
    public void employeeDevelopTargetListDtoDelete(@Valid EmployeeDevelopTargetListDtoDeleteReq employeeDevelopTargetListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeDevelopTarget employeeDevelopTarget=bomanager.keyLoadBusinessObject(EmployeeDevelopTarget.class, employeeDevelopTargetListDtoDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeDevelopTarget);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
