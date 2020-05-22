package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeTargetRankListDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.entity.EmployeeTargetRank;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoDeleteReq;

/**
 * 目标职级申请ListService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeTargetRankListDtoServiceImpl implements EmployeeTargetRankListDtoService{
    /**
                   * 查询结果集
     */
    public static class EmployeeTargetRankListDtoReqQuery implements RequestQuery<EmployeeTargetRankListDtoQueryReq> {
        @Override
        public Query apply(EmployeeTargetRankListDtoQueryReq employeeTargetRankListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeTargetRankListDtoQueryReq, EmployeeTargetRankListDto.class);
            
            String sql = "select ETR.serialNo as serialNo,ETR.rankSerialNo as rankSerialNo,ETR.employeeNo as employeeNo,ETR.employeeName as employeeName,ETR.rankName as rankName,ETR.evaluationRank as evaluationRank,ETR.targetRank as targetRank,ETR.targetRecord as targetRecord,ETR.evaluationRankTime as evaluationRankTime,ETR.label as label,ETR.grade as grade,ETR.masteryDegree as masteryDegree,ETR.changeReason as changeReason,ETR.opinion as opinion,ETR.teamManager as teamManager,ETR.inputUserId as inputUserId,ETR.inputTime as inputTime,ETR.inputOrgId as inputOrgId,ETR.updateUserId as updateUserId,ETR.updateTime as updateTime,ETR.updateOrgId as updateOrgId"
                +" from EMPLOYEE_TARGET_RANK ETR"
                +" where 1=1 and ETR.approveStatus = :approveStatus";
            return queryProperties.assembleSql(sql,"approveStatus",employeeTargetRankListDtoQueryReq.getApproveStatus());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeTargetRankListDtoRspConvert implements Convert<EmployeeTargetRankListDto> {
        @Override
        public EmployeeTargetRankListDto apply(BusinessObject bo) {
            EmployeeTargetRankListDto temp = new EmployeeTargetRankListDto();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setRankSerialNo(bo.getString("RankSerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setRankName(bo.getString("RankName"));
            temp.setEvaluationRank(bo.getString("EvaluationRank"));
            temp.setTargetRank(bo.getString("TargetRank"));
            temp.setTargetRecord(bo.getString("TargetRecord"));
            temp.setEvaluationRankTime(bo.getString("EvaluationRankTime"));
            temp.setLabel(bo.getString("Label"));
            temp.setGrade(bo.getString("Grade"));
            temp.setMasteryDegree(bo.getString("MasteryDegree"));
            temp.setChangeReason(bo.getString("ChangeReason"));
            temp.setOpinion(bo.getString("Opinion"));
            temp.setTeamManager(bo.getString("TeamManager"));
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
     * 目标职级申请List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeTargetRankListDtoQueryRsp employeeTargetRankListDtoQuery(@Valid EmployeeTargetRankListDtoQueryReq employeeTargetRankListDtoQueryReq) {
        EmployeeTargetRankListDtoQueryRsp employeeTargetRankListDtoQueryRsp = new EmployeeTargetRankListDtoQueryRsp();
        
        Query query = new EmployeeTargetRankListDtoReqQuery().apply(employeeTargetRankListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeTargetRankListDtoRspConvert convert = new EmployeeTargetRankListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeTargetRankListDtoQueryReq.getBegin(), employeeTargetRankListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeTargetRankListDto> employeeTargetRankListDtos = new ArrayList<EmployeeTargetRankListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeTargetRankListDtos.add(convert.apply(bo));
            }
            employeeTargetRankListDtoQueryRsp.setEmployeeTargetRankListDtos(employeeTargetRankListDtos);
        }
        employeeTargetRankListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeTargetRankListDtoQueryRsp;
    }

    /**
     * 目标职级申请List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeTargetRankListDtoSave(@Valid EmployeeTargetRankListDtoSaveReq employeeTargetRankListDtoSaveReq) {
        employeeTargetRankListDtoSaveAction(employeeTargetRankListDtoSaveReq.getEmployeeTargetRankListDtos());
    }
    /**
     * 目标职级申请List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeTargetRankListDtoSaveAction(List<EmployeeTargetRankListDto> employeeTargetRankListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeTargetRankListDtos!=null){
            for(EmployeeTargetRankListDto employeeTargetRankListDtoTmp :employeeTargetRankListDtos){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 目标职级申请List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeTargetRankListDtoDelete(@Valid EmployeeTargetRankListDtoDeleteReq employeeTargetRankListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeTargetRank employeeTargetRank=bomanager.keyLoadBusinessObject(EmployeeTargetRank.class, employeeTargetRankListDtoDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeTargetRank);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
