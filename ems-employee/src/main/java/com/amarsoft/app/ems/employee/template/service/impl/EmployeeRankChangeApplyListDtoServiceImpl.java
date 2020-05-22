/*
 * 文件名：EmployeeRankChangeApplyListDtoServiceImpl 
 * 版权：Copyright by www.amarsoft.com 
 * 描述：
 * 员工职级调整申请ListService实现类
 * 修改人：xucheng 
 * 修改时间：2020/05/20 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.template.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.acsc.util.DTOHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.entity.EmployeeInfo;
import com.amarsoft.app.ems.employee.entity.EmployeeRankApply;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankChangeApplyListDtoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeRankChangeApplyListDtoServiceImpl implements EmployeeRankChangeApplyListDtoService{
    /**
                   * 查询结果集
     */
    public static class EmployeeRankChangeApplyListDtoReqQuery implements RequestQuery<EmployeeRankChangeApplyListDtoQueryReq> {
        @Override
        public Query apply(EmployeeRankChangeApplyListDtoQueryReq employeeRankChangeApplyListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeRankChangeApplyListDtoQueryReq, EmployeeRankChangeApplyListDto.class);
            
            String sql = "select EI.employeeNo as employeeNo,EI.employeeName as employeeName,EI.employeeAcct as employeeAcct,"
                +"ERA.serialNo as serialNo,ERA.rankName as rankName,ERA.updateRankName as updateRankName,ERA.rankTime as rankTime,ERA.inputUserId as inputUserId,ERA.inputTime as inputTime,ERA.inputOrgId as inputOrgId,ERA.updateUserId as updateUserId,ERA.updateTime as updateTime,ERA.updateOrgId as updateOrgId"
                +" from EMPLOYEE_INFO EI,EMPLOYEE_RANK_APPLY ERA"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeRankChangeApplyListDtoRspConvert implements Convert<EmployeeRankChangeApplyListDto> {
        @Override
        public EmployeeRankChangeApplyListDto apply(BusinessObject bo) {
            EmployeeRankChangeApplyListDto temp = new EmployeeRankChangeApplyListDto();
                
            //查询到的数据转换为响应实体
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setEmployeeAcct(bo.getString("EmployeeAcct"));
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setRankName(bo.getString("RankName"));
            temp.setUpdateRankName(bo.getString("UpdateRankName"));
            temp.setRankTime(bo.getString("RankTime"));
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
     * 员工职级调整申请List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankChangeApplyListDtoQueryRsp employeeRankChangeApplyListDtoQuery(@Valid EmployeeRankChangeApplyListDtoQueryReq employeeRankChangeApplyListDtoQueryReq) {
        EmployeeRankChangeApplyListDtoQueryRsp employeeRankChangeApplyListDtoQueryRsp = new EmployeeRankChangeApplyListDtoQueryRsp();
        
        Query query = new EmployeeRankChangeApplyListDtoReqQuery().apply(employeeRankChangeApplyListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeRankChangeApplyListDtoRspConvert convert = new EmployeeRankChangeApplyListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeRankChangeApplyListDtoQueryReq.getBegin(), employeeRankChangeApplyListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(!StringUtils.isEmpty(businessObjectList) && !businessObjectList.isEmpty()) {
            List<EmployeeRankChangeApplyListDto> employeeRankChangeApplyListDtos = new ArrayList<EmployeeRankChangeApplyListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeRankChangeApplyListDtos.add(convert.apply(bo));
            }
            employeeRankChangeApplyListDtoQueryRsp.setEmployeeRankChangeApplyListDtos(employeeRankChangeApplyListDtos);
        }
        employeeRankChangeApplyListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeRankChangeApplyListDtoQueryRsp;
    }

    /**
     * 员工职级调整申请List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeRankChangeApplyListDtoSave(@Valid EmployeeRankChangeApplyListDtoSaveReq employeeRankChangeApplyListDtoSaveReq) {
        employeeRankChangeApplyListDtoSaveAction(employeeRankChangeApplyListDtoSaveReq.getEmployeeRankChangeApplyListDtos());
    }
    /**
     * 员工职级调整申请List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeRankChangeApplyListDtoSaveAction(List<EmployeeRankChangeApplyListDto> employeeRankChangeApplyListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(!StringUtils.isEmpty(employeeRankChangeApplyListDtos)){
            for(EmployeeRankChangeApplyListDto employeeRankChangeApplyListDtoTmp :employeeRankChangeApplyListDtos){
                EmployeeRankApply employeeRankApply = bomanager.keyLoadBusinessObject(EmployeeRankApply.class,employeeRankChangeApplyListDtoTmp.getSerialNo());
                if(StringUtils.isEmpty(employeeRankApply)){
                    employeeRankApply = new EmployeeRankApply();
                    employeeRankApply.generateKey();
                }
                employeeRankApply.setRankName(employeeRankChangeApplyListDtoTmp.getRankName());
                employeeRankApply.setUpdateRankName(employeeRankChangeApplyListDtoTmp.getUpdateRankName());
                employeeRankApply.setRankTime(DateHelper.getDateTime(employeeRankChangeApplyListDtoTmp.getRankTime()));
                employeeRankApply.setInputUserId(employeeRankChangeApplyListDtoTmp.getInputUserId());
                employeeRankApply.setInputTime(DateHelper.getDateTime(employeeRankChangeApplyListDtoTmp.getInputTime()));
                employeeRankApply.setInputOrgId(employeeRankChangeApplyListDtoTmp.getInputOrgId());
                employeeRankApply.setUpdateUserId(employeeRankChangeApplyListDtoTmp.getUpdateUserId());
                employeeRankApply.setUpdateTime(DateHelper.getDateTime(employeeRankChangeApplyListDtoTmp.getUpdateTime()));
                employeeRankApply.setUpdateOrgId(employeeRankChangeApplyListDtoTmp.getUpdateOrgId());
                bomanager.updateBusinessObject(employeeRankApply);
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工职级调整申请List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeRankChangeApplyListDtoDelete(@Valid EmployeeRankChangeApplyListDtoDeleteReq employeeRankChangeApplyListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeInfo employeeInfo=bomanager.keyLoadBusinessObject(EmployeeInfo.class, employeeRankChangeApplyListDtoDeleteReq.getEmployeeNo());
        bomanager.deleteBusinessObject(employeeInfo);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
