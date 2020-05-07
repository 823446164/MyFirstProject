package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeOtherInfoListDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.entity.EmployeeOtherInfo;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoDeleteReq;

/**
 * 员工其他说明ListService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeOtherInfoListDtoServiceImpl implements EmployeeOtherInfoListDtoService{
    /**
                   * 查询结果集
     */
    public static class EmployeeOtherInfoListDtoReqQuery implements RequestQuery<EmployeeOtherInfoListDtoQueryReq> {
        @Override
        public Query apply(EmployeeOtherInfoListDtoQueryReq employeeOtherInfoListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeOtherInfoListDtoQueryReq, EmployeeOtherInfoListDto.class);
            
            String sql = "select EOI.serialNo as serialNo,EOI.employeeNo as employeeNo,EOI.situationDescribe as situationDescribe,EOI.inputUserId as inputUserId,EOI.inputTime as inputTime,EOI.inputOrgId as inputOrgId,EOI.updateUserId as updateUserId,EOI.updateTime as updateTime,EOI.updateOrgId as updateOrgId"
                +" from EMPLOYEE_OTHER_INFO EOI"
                +" where 1=1 and EOI.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeOtherInfoListDtoQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeOtherInfoListDtoRspConvert implements Convert<EmployeeOtherInfoListDto> {
        @Override
        public EmployeeOtherInfoListDto apply(BusinessObject bo) {
            EmployeeOtherInfoListDto temp = new EmployeeOtherInfoListDto();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setSituationDescribe(bo.getString("SituationDescribe"));
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
     * 员工其他说明List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeOtherInfoListDtoQueryRsp employeeOtherInfoListDtoQuery(@Valid EmployeeOtherInfoListDtoQueryReq employeeOtherInfoListDtoQueryReq) {
        EmployeeOtherInfoListDtoQueryRsp employeeOtherInfoListDtoQueryRsp = new EmployeeOtherInfoListDtoQueryRsp();
        
        Query query = new EmployeeOtherInfoListDtoReqQuery().apply(employeeOtherInfoListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeOtherInfoListDtoRspConvert convert = new EmployeeOtherInfoListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeOtherInfoListDtoQueryReq.getBegin(), employeeOtherInfoListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeOtherInfoListDto> employeeOtherInfoListDtos = new ArrayList<EmployeeOtherInfoListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeOtherInfoListDtos.add(convert.apply(bo));
            }
            employeeOtherInfoListDtoQueryRsp.setEmployeeOtherInfoListDtos(employeeOtherInfoListDtos);
        }
        employeeOtherInfoListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeOtherInfoListDtoQueryRsp;
    }

    /**
     * 员工其他说明List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeOtherInfoListDtoSave(@Valid EmployeeOtherInfoListDtoSaveReq employeeOtherInfoListDtoSaveReq) {
        employeeOtherInfoListDtoSaveAction(employeeOtherInfoListDtoSaveReq.getEmployeeOtherInfoListDtos());
    }
    /**
     * 员工其他说明List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeOtherInfoListDtoSaveAction(List<EmployeeOtherInfoListDto> employeeOtherInfoListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeOtherInfoListDtos!=null){
            for(EmployeeOtherInfoListDto employeeOtherInfoListDtoTmp :employeeOtherInfoListDtos){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工其他说明List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeOtherInfoListDtoDelete(@Valid EmployeeOtherInfoListDtoDeleteReq employeeOtherInfoListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeOtherInfo employeeOtherInfo=bomanager.keyLoadBusinessObject(EmployeeOtherInfo.class, employeeOtherInfoListDtoDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeOtherInfo);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
