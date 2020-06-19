package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeeRankChangeApplyInfoService;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import java.util.List;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfo;
import com.amarsoft.app.ems.demo.entity.EmployeeRankApply;
import com.amarsoft.amps.arpe.util.DateHelper;

/**
 * 员工职级调整申请详情InfoService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeRankChangeApplyInfoServiceImpl implements EmployeeRankChangeApplyInfoService{
    /**
     * 员工职级调整申请详情Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankChangeApplyInfoQueryRsp employeeRankChangeApplyInfoQuery(@Valid EmployeeRankChangeApplyInfoQueryReq employeeRankChangeApplyInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        BusinessObjectAggregate<BusinessObject> employeeRankChangeApplyInfoQueryRspBoa = bomanager.selectBusinessObjectsBySql("select TIJ.employeeNo as employeeNo,TIJ.employeeName as employeeName,TIJ.employeeAcct as employeeAcct,"
            +"ERA.serialNo as serialNo,ERA.rankName as rankName,ERA.updateRankName as updateRankName,ERA.rankTime as rankTime,ERA.inputUserId as inputUserId,ERA.inputTime as inputTime,ERA.inputOrgId as inputOrgId,ERA.updateUserId as updateUserId,ERA.updateTime as updateTime,ERA.updateOrgId as updateOrgId"
            +" from TestInfoJfan TIJ,EmployeeRankApply ERA"
            +" where 1=1 and ERA.serialNo = :serialNo","serialNo",employeeRankChangeApplyInfoQueryReq.getSerialNo());
        List<BusinessObject> employeeRankChangeApplyInfoQueryRspBoList = employeeRankChangeApplyInfoQueryRspBoa.getBusinessObjects();
        if(employeeRankChangeApplyInfoQueryRspBoList!=null&&employeeRankChangeApplyInfoQueryRspBoList.size()>0){
            BusinessObject employeeRankChangeApplyInfoQueryRspBo = employeeRankChangeApplyInfoQueryRspBoList.get(0);
            EmployeeRankChangeApplyInfoQueryRsp employeeRankChangeApplyInfo = new EmployeeRankChangeApplyInfoQueryRsp();
            employeeRankChangeApplyInfo.setEmployeeNo(employeeRankChangeApplyInfoQueryRspBo.getString("EmployeeNo"));
            employeeRankChangeApplyInfo.setEmployeeName(employeeRankChangeApplyInfoQueryRspBo.getString("EmployeeName"));
            employeeRankChangeApplyInfo.setEmployeeAcct(employeeRankChangeApplyInfoQueryRspBo.getString("EmployeeAcct"));
            employeeRankChangeApplyInfo.setSerialNo(employeeRankChangeApplyInfoQueryRspBo.getString("SerialNo"));
            employeeRankChangeApplyInfo.setRankName(employeeRankChangeApplyInfoQueryRspBo.getString("RankName"));
            employeeRankChangeApplyInfo.setUpdateRankName(employeeRankChangeApplyInfoQueryRspBo.getString("UpdateRankName"));
            employeeRankChangeApplyInfo.setRankTime(employeeRankChangeApplyInfoQueryRspBo.getString("RankTime"));
            employeeRankChangeApplyInfo.setInputUserId(employeeRankChangeApplyInfoQueryRspBo.getString("InputUserId"));
            employeeRankChangeApplyInfo.setInputTime(employeeRankChangeApplyInfoQueryRspBo.getString("InputTime"));
            employeeRankChangeApplyInfo.setInputOrgId(employeeRankChangeApplyInfoQueryRspBo.getString("InputOrgId"));
            employeeRankChangeApplyInfo.setUpdateUserId(employeeRankChangeApplyInfoQueryRspBo.getString("UpdateUserId"));
            employeeRankChangeApplyInfo.setUpdateTime(employeeRankChangeApplyInfoQueryRspBo.getString("UpdateTime"));
            employeeRankChangeApplyInfo.setUpdateOrgId(employeeRankChangeApplyInfoQueryRspBo.getString("UpdateOrgId"));
            return employeeRankChangeApplyInfo;
        }

        return null;
    }

    /**
     * 员工职级调整申请详情Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeRankChangeApplyInfoSave(@Valid EmployeeRankChangeApplyInfoSaveReq employeeRankChangeApplyInfoSaveReq) {
        employeeRankChangeApplyInfoSaveAction(employeeRankChangeApplyInfoSaveReq);
    }
    /**
     * 员工职级调整申请详情Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeRankChangeApplyInfoSaveAction(EmployeeRankChangeApplyInfo employeeRankChangeApplyInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeRankChangeApplyInfo!=null){
            EmployeeRankApply employeeRankApply = bomanager.keyLoadBusinessObject(EmployeeRankApply.class,employeeRankChangeApplyInfo.getSerialNo());
            if(employeeRankApply==null){
                employeeRankApply = new EmployeeRankApply();
                employeeRankApply.generateKey();
            }
            employeeRankApply.setRankName(employeeRankChangeApplyInfo.getRankName());
            employeeRankApply.setUpdateRankName(employeeRankChangeApplyInfo.getUpdateRankName());
            employeeRankApply.setRankTime(DateHelper.getDateTime(employeeRankChangeApplyInfo.getRankTime()));
            employeeRankApply.setInputUserId(employeeRankChangeApplyInfo.getInputUserId());
            employeeRankApply.setInputTime(DateHelper.getDateTime(employeeRankChangeApplyInfo.getInputTime()));
            employeeRankApply.setInputOrgId(employeeRankChangeApplyInfo.getInputOrgId());
            employeeRankApply.setUpdateUserId(employeeRankChangeApplyInfo.getUpdateUserId());
            employeeRankApply.setUpdateTime(DateHelper.getDateTime(employeeRankChangeApplyInfo.getUpdateTime()));
            employeeRankApply.setUpdateOrgId(employeeRankChangeApplyInfo.getUpdateOrgId());
            bomanager.updateBusinessObject(employeeRankApply);
        }
        bomanager.updateDB();
    }
}
