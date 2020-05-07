package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankListDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.entity.EmployeeRank;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoDeleteReq;

/**
 * 员工职级ListService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeRankListDtoServiceImpl implements EmployeeRankListDtoService{
    /**
                   * 查询结果集
     */
    public static class EmployeeRankListDtoReqQuery implements RequestQuery<EmployeeRankListDtoQueryReq> {
        @Override
        public Query apply(EmployeeRankListDtoQueryReq employeeRankListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeRankListDtoQueryReq, EmployeeRankListDto.class);
            
            String sql = "select ER.serialNo as serialNo,ER.employeeNo as employeeNo,ER.classify as classify,ER.goalDate as goalDate,ER.rank as rank,ER.rankVersion as rankVersion,ER.inputUserId as inputUserId,ER.inputTime as inputTime,ER.inputOrgId as inputOrgId,ER.updateUserId as updateUserId,ER.updateTime as updateTime,ER.updateOrgId as updateOrgId,ER.rankIsFormal as rankIsFormal,ER.changeDate as changeDate"
                +" from EMPLOYEE_RANK ER"
                +" where 1=1 and ER.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeRankListDtoQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeRankListDtoRspConvert implements Convert<EmployeeRankListDto> {
        @Override
        public EmployeeRankListDto apply(BusinessObject bo) {
            EmployeeRankListDto temp = new EmployeeRankListDto();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setClassify(bo.getString("Classify"));
            temp.setGoalDate(bo.getString("GoalDate"));
            temp.setRank(bo.getString("Rank"));
            temp.setRankVersion(bo.getString("RankVersion"));
            temp.setInputUserId(bo.getString("InputUserId"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateUserId(bo.getString("UpdateUserId"));
            temp.setUpdateTime(bo.getString("UpdateTime"));
            temp.setUpdateOrgId(bo.getString("UpdateOrgId"));
            temp.setRankIsFormal(bo.getString("RankIsFormal"));
            temp.setChangeDate(bo.getString("ChangeDate"));
            
            return temp;
        }
    }

    /**
     * 员工职级List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankListDtoQueryRsp employeeRankListDtoQuery(@Valid EmployeeRankListDtoQueryReq employeeRankListDtoQueryReq) {
        EmployeeRankListDtoQueryRsp employeeRankListDtoQueryRsp = new EmployeeRankListDtoQueryRsp();
        
        Query query = new EmployeeRankListDtoReqQuery().apply(employeeRankListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeRankListDtoRspConvert convert = new EmployeeRankListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeRankListDtoQueryReq.getBegin(), employeeRankListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeRankListDto> employeeRankListDtos = new ArrayList<EmployeeRankListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeRankListDtos.add(convert.apply(bo));
            }
            employeeRankListDtoQueryRsp.setEmployeeRankListDtos(employeeRankListDtos);
        }
        employeeRankListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeRankListDtoQueryRsp;
    }

    /**
     * 员工职级List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeRankListDtoSave(@Valid EmployeeRankListDtoSaveReq employeeRankListDtoSaveReq) {
        employeeRankListDtoSaveAction(employeeRankListDtoSaveReq.getEmployeeRankListDtos());
    }
    /**
     * 员工职级List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeRankListDtoSaveAction(List<EmployeeRankListDto> employeeRankListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeRankListDtos!=null){
            for(EmployeeRankListDto employeeRankListDtoTmp :employeeRankListDtos){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工职级List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeRankListDtoDelete(@Valid EmployeeRankListDtoDeleteReq employeeRankListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeRank employeeRank=bomanager.keyLoadBusinessObject(EmployeeRank.class, employeeRankListDtoDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeRank);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
