package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeInfoListDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.entity.EmployeeInfo;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoDeleteReq;

/**
 * 员工信息ListService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeInfoListDtoServiceImpl implements EmployeeInfoListDtoService{
    /**
                   * 查询结果集
     */
    public static class EmployeeInfoListDtoReqQuery implements RequestQuery<EmployeeInfoListDtoQueryReq> {
        @Override
        public Query apply(EmployeeInfoListDtoQueryReq employeeInfoListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeInfoListDtoQueryReq, EmployeeInfoListDto.class);
            
            String sql = "select EI.employeeNo as employeeNo,EI.employeeName as employeeName,EI.employeeAcct as employeeAcct,EI.phoneNum as phoneNum,EI.nowRank as nowRank,EI.goalRank as goalRank,EI.rntryTime as rntryTime,EI.changeTime as changeTime,EI.employeeStatus as employeeStatus,EI.resignationReason as resignationReason,EI.employeeeDucation as employeeeDucation,EI.graduationTime as graduationTime,EI.graduatedSchool as graduatedSchool,EI.major as major,EI.homeTown as homeTown,EI.inputUserId as inputUserId,EI.inputTime as inputTime,EI.inputOrgId as inputOrgId,EI.updateUserId as updateUserId,EI.updateTime as updateTime,EI.updateOrgId as updateOrgId,EI.employeeWorkStatus as employeeWorkStatus"
                +" from EMPLOYEE_INFO EI"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeInfoListDtoRspConvert implements Convert<EmployeeInfoListDto> {
        @Override
        public EmployeeInfoListDto apply(BusinessObject bo) {
            EmployeeInfoListDto temp = new EmployeeInfoListDto();
                
            //查询到的数据转换为响应实体
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setEmployeeAcct(bo.getString("EmployeeAcct"));
            temp.setPhoneNum(bo.getString("PhoneNum"));
            temp.setNowRank(bo.getString("NowRank"));
            temp.setGoalRank(bo.getString("GoalRank"));
            temp.setRntryTime(bo.getString("RntryTime"));
            temp.setChangeTime(bo.getString("ChangeTime"));
            temp.setEmployeeStatus(bo.getString("EmployeeStatus"));
            temp.setResignationReason(bo.getString("ResignationReason"));
            temp.setEmployeeeDucation(bo.getString("EmployeeeDucation"));
            temp.setGraduationTime(bo.getString("GraduationTime"));
            temp.setGraduatedSchool(bo.getString("GraduatedSchool"));
            temp.setMajor(bo.getString("Major"));
            temp.setHomeTown(bo.getString("HomeTown"));
            temp.setInputUserId(bo.getString("InputUserId"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateUserId(bo.getString("UpdateUserId"));
            temp.setUpdateTime(bo.getString("UpdateTime"));
            temp.setUpdateOrgId(bo.getString("UpdateOrgId"));
            temp.setEmployeeWorkStatus(bo.getString("EmployeeWorkStatus"));
            
            return temp;
        }
    }

    /**
     * 员工信息List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeInfoListDtoQueryRsp employeeInfoListDtoQuery(@Valid EmployeeInfoListDtoQueryReq employeeInfoListDtoQueryReq) {
        EmployeeInfoListDtoQueryRsp employeeInfoListDtoQueryRsp = new EmployeeInfoListDtoQueryRsp();
        
        Query query = new EmployeeInfoListDtoReqQuery().apply(employeeInfoListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeInfoListDtoRspConvert convert = new EmployeeInfoListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeInfoListDtoQueryReq.getBegin(), employeeInfoListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeInfoListDto> employeeInfoListDtos = new ArrayList<EmployeeInfoListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeInfoListDtos.add(convert.apply(bo));
            }
            employeeInfoListDtoQueryRsp.setEmployeeInfoListDtos(employeeInfoListDtos);
        }
        employeeInfoListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeInfoListDtoQueryRsp;
    }

    /**
     * 员工信息List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeInfoListDtoSave(@Valid EmployeeInfoListDtoSaveReq employeeInfoListDtoSaveReq) {
        employeeInfoListDtoSaveAction(employeeInfoListDtoSaveReq.getEmployeeInfoListDtos());
    }
    /**
     * 员工信息List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeInfoListDtoSaveAction(List<EmployeeInfoListDto> employeeInfoListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeInfoListDtos!=null){
            for(EmployeeInfoListDto employeeInfoListDtoTmp :employeeInfoListDtos){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工信息List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeInfoListDtoDelete(@Valid EmployeeInfoListDtoDeleteReq employeeInfoListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeInfo employeeInfo=bomanager.keyLoadBusinessObject(EmployeeInfo.class, employeeInfoListDtoDeleteReq.getEmployeeNo());
        bomanager.deleteBusinessObject(employeeInfo);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
