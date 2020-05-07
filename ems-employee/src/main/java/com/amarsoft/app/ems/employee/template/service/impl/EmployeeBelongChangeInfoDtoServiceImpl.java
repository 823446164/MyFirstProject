package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeBelongChangeInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeBelongChange;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDto;

/**
 * 团队调整申请InfoService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeBelongChangeInfoDtoServiceImpl implements EmployeeBelongChangeInfoDtoService{
    /**
     * 团队调整申请Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeBelongChangeInfoDtoQueryRsp employeeBelongChangeInfoDtoQuery(@Valid EmployeeBelongChangeInfoDtoQueryReq employeeBelongChangeInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeBelongChange employeeBelongChange = bomanager.loadBusinessObject(EmployeeBelongChange.class,"serialNo",employeeBelongChangeInfoDtoQueryReq.getSerialNo());
        if(employeeBelongChange!=null){
            EmployeeBelongChangeInfoDtoQueryRsp employeeBelongChangeInfoDto = new EmployeeBelongChangeInfoDtoQueryRsp();
            employeeBelongChangeInfoDto.setSerialNo(employeeBelongChange.getSerialNo());
            employeeBelongChangeInfoDto.setTeamNo(employeeBelongChange.getTeamNo());
            employeeBelongChangeInfoDto.setEmployeeNo(employeeBelongChange.getEmployeeNo());
            employeeBelongChangeInfoDto.setEmployeeName(employeeBelongChange.getEmployeeName());
            employeeBelongChangeInfoDto.setEmployeeAcct(employeeBelongChange.getEmployeeAcct());
            employeeBelongChangeInfoDto.setObjectType(employeeBelongChange.getObjectType());
            employeeBelongChangeInfoDto.setDeptId(employeeBelongChange.getDeptId());
            employeeBelongChangeInfoDto.setDeptName(employeeBelongChange.getDeptName());
            employeeBelongChangeInfoDto.setBeforeTeam(employeeBelongChange.getBeforeTeam());
            employeeBelongChangeInfoDto.setAfterTeam(employeeBelongChange.getAfterTeam());
            employeeBelongChangeInfoDto.setChangeManager(employeeBelongChange.getChangeManager());
            employeeBelongChangeInfoDto.setAdjustReason(employeeBelongChange.getAdjustReason());
            employeeBelongChangeInfoDto.setInputUserId(employeeBelongChange.getInputUserId());
            employeeBelongChangeInfoDto.setInputTime(employeeBelongChange.getInputTime());
            employeeBelongChangeInfoDto.setInputOrgId(employeeBelongChange.getInputOrgId());
            employeeBelongChangeInfoDto.setUpdateUserId(employeeBelongChange.getUpdateUserId());
            employeeBelongChangeInfoDto.setUpdateTime(employeeBelongChange.getUpdateTime());
            employeeBelongChangeInfoDto.setUpdateOrgId(employeeBelongChange.getUpdateOrgId());
            return employeeBelongChangeInfoDto;
        }

        return null;
    }

    /**
     * 团队调整申请Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeBelongChangeInfoDtoSave(@Valid EmployeeBelongChangeInfoDtoSaveReq employeeBelongChangeInfoDtoSaveReq) {
        employeeBelongChangeInfoDtoSaveAction(employeeBelongChangeInfoDtoSaveReq);
    }
    /**
     * 团队调整申请Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeBelongChangeInfoDtoSaveAction(EmployeeBelongChangeInfoDto employeeBelongChangeInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeBelongChangeInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
