package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankApplyListDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoSaveReq;
import com.amarsoft.app.ems.employee.entity.EmployeeRankApply;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoDeleteReq;

/**
 * 员工职级申请ListService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeRankApplyListDtoServiceImpl implements EmployeeRankApplyListDtoService{
    /**
                   * 查询结果集
     */
    public static class EmployeeRankApplyListDtoReqQuery implements RequestQuery<EmployeeRankApplyListDtoQueryReq> {
        @Override
        public Query apply(EmployeeRankApplyListDtoQueryReq employeeRankApplyListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeRankApplyListDtoQueryReq, EmployeeRankApplyListDto.class);
            
            String sql = "select ERA.serialNo as serialNo,ERA.rankNo as rankNo,ERA.employeeNo as employeeNo,ERA.employeeName as employeeName,ERA.rankName as rankName,ERA.updateRankName as updateRankName,ERA.teamManager as teamManager,ERA.changePerson as changePerson,ERA.beginTime as beginTime,ERA.changeReason as changeReason,ERA.inputUserId as inputUserId,ERA.inputTime as inputTime,ERA.inputOrgId as inputOrgId,ERA.updateUserId as updateUserId,ERA.updateTime as updateTime,ERA.updateOrgId as updateOrgId"
                +" from EMPLOYEE_RANK_APPLY ERA"
                +" where 1=1 and ERA.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeRankApplyListDtoQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeRankApplyListDtoRspConvert implements Convert<EmployeeRankApplyListDto> {
        @Override
        public EmployeeRankApplyListDto apply(BusinessObject bo) {
            EmployeeRankApplyListDto temp = new EmployeeRankApplyListDto();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setRankNo(bo.getString("RankNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setRankName(bo.getString("RankName"));
            temp.setUpdateRankName(bo.getString("UpdateRankName"));
            temp.setTeamManager(bo.getString("TeamManager"));
            temp.setChangePerson(bo.getString("ChangePerson"));
            temp.setBeginTime(bo.getString("BeginTime"));
            temp.setChangeReason(bo.getString("ChangeReason"));
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
     * 员工职级申请List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankApplyListDtoQueryRsp employeeRankApplyListDtoQuery(@Valid EmployeeRankApplyListDtoQueryReq employeeRankApplyListDtoQueryReq) {
        EmployeeRankApplyListDtoQueryRsp employeeRankApplyListDtoQueryRsp = new EmployeeRankApplyListDtoQueryRsp();
        
        Query query = new EmployeeRankApplyListDtoReqQuery().apply(employeeRankApplyListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeRankApplyListDtoRspConvert convert = new EmployeeRankApplyListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeRankApplyListDtoQueryReq.getBegin(), employeeRankApplyListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeRankApplyListDto> employeeRankApplyListDtos = new ArrayList<EmployeeRankApplyListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeRankApplyListDtos.add(convert.apply(bo));
            }
            employeeRankApplyListDtoQueryRsp.setEmployeeRankApplyListDtos(employeeRankApplyListDtos);
        }
        employeeRankApplyListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeRankApplyListDtoQueryRsp;
    }

    /**
     * 员工职级申请List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeRankApplyListDtoSave(@Valid EmployeeRankApplyListDtoSaveReq employeeRankApplyListDtoSaveReq) {
        employeeRankApplyListDtoSaveAction(employeeRankApplyListDtoSaveReq.getEmployeeRankApplyListDtos());
    }
    /**
     * 员工职级申请List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeRankApplyListDtoSaveAction(List<EmployeeRankApplyListDto> employeeRankApplyListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeRankApplyListDtos!=null){
            for(EmployeeRankApplyListDto employeeRankApplyListDtoTmp :employeeRankApplyListDtos){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工职级申请List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeRankApplyListDtoDelete(@Valid EmployeeRankApplyListDtoDeleteReq employeeRankApplyListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeRankApply employeeRankApply=bomanager.keyLoadBusinessObject(EmployeeRankApply.class, employeeRankApplyListDtoDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeRankApply);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
