package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeeBelongChangeInfoService;
import com.amarsoft.app.ems.demo.entity.EmployeeBelongChange;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfo;

/**
 * 团队调整申请InfoService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeBelongChangeInfoServiceImpl implements EmployeeBelongChangeInfoService{
    /**
     * 团队调整申请Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeBelongChangeInfoQueryRsp employeeBelongChangeInfoQuery(@Valid EmployeeBelongChangeInfoQueryReq employeeBelongChangeInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeBelongChange employeeBelongChange = bomanager.loadBusinessObject(EmployeeBelongChange.class,"serialNo",employeeBelongChangeInfoQueryReq.getSerialNo());
        if(employeeBelongChange!=null){
            EmployeeBelongChangeInfoQueryRsp employeeBelongChangeInfo = new EmployeeBelongChangeInfoQueryRsp();
            employeeBelongChangeInfo.setSerialNo(employeeBelongChange.getSerialNo());
            employeeBelongChangeInfo.setTeamNo(employeeBelongChange.getTeamNo());
            employeeBelongChangeInfo.setEmployeeNo(employeeBelongChange.getEmployeeNo());
            employeeBelongChangeInfo.setEmployeeName(employeeBelongChange.getEmployeeName());
            employeeBelongChangeInfo.setEmployeeAcct(employeeBelongChange.getEmployeeAcct());
            employeeBelongChangeInfo.setObjectType(employeeBelongChange.getObjectType());
            employeeBelongChangeInfo.setDeptId(employeeBelongChange.getDeptId());
            employeeBelongChangeInfo.setDeptName(employeeBelongChange.getDeptName());
            employeeBelongChangeInfo.setBeforeTeam(employeeBelongChange.getBeforeTeam());
            employeeBelongChangeInfo.setAfterTeam(employeeBelongChange.getAfterTeam());
            employeeBelongChangeInfo.setChangeManager(employeeBelongChange.getChangeManager());
            employeeBelongChangeInfo.setAdjustReason(employeeBelongChange.getAdjustReason());
            employeeBelongChangeInfo.setInputUserId(employeeBelongChange.getInputUserId());
            employeeBelongChangeInfo.setInputTime(employeeBelongChange.getInputTime());
            employeeBelongChangeInfo.setInputOrgId(employeeBelongChange.getInputOrgId());
            employeeBelongChangeInfo.setUpdateUserId(employeeBelongChange.getUpdateUserId());
            employeeBelongChangeInfo.setUpdateTime(employeeBelongChange.getUpdateTime());
            employeeBelongChangeInfo.setUpdateOrgId(employeeBelongChange.getUpdateOrgId());
            return employeeBelongChangeInfo;
        }

        return null;
    }

    /**
     * 团队调整申请Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeBelongChangeInfoSave(@Valid EmployeeBelongChangeInfoSaveReq employeeBelongChangeInfoSaveReq) {
        employeeBelongChangeInfoSaveAction(employeeBelongChangeInfoSaveReq);
    }
    /**
     * 团队调整申请Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeBelongChangeInfoSaveAction(EmployeeBelongChangeInfo employeeBelongChangeInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeBelongChangeInfo!=null){
        }
        bomanager.updateDB();
    }
}
