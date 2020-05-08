package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankApplyInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeRankApply;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDto;

/**
 * 员工职级申请InfoService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeRankApplyInfoDtoServiceImpl implements EmployeeRankApplyInfoDtoService{
    /**
     * 员工职级申请Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankApplyInfoDtoQueryRsp employeeRankApplyInfoDtoQuery(@Valid EmployeeRankApplyInfoDtoQueryReq employeeRankApplyInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeRankApply employeeRankApply = bomanager.loadBusinessObject(EmployeeRankApply.class,"serialNo",employeeRankApplyInfoDtoQueryReq.getSerialNo());
        if(employeeRankApply!=null){
            EmployeeRankApplyInfoDtoQueryRsp employeeRankApplyInfoDto = new EmployeeRankApplyInfoDtoQueryRsp();
            employeeRankApplyInfoDto.setSerialNo(employeeRankApply.getSerialNo());
            employeeRankApplyInfoDto.setRankNo(employeeRankApply.getRankNo());
            employeeRankApplyInfoDto.setEmployeeNo(employeeRankApply.getEmployeeNo());
            employeeRankApplyInfoDto.setEmployeeName(employeeRankApply.getEmployeeName());
            employeeRankApplyInfoDto.setRankName(employeeRankApply.getRankName());
            employeeRankApplyInfoDto.setUpdateRankName(employeeRankApply.getUpdateRankName());
            employeeRankApplyInfoDto.setTeamManager(employeeRankApply.getTeamManager());
            employeeRankApplyInfoDto.setChangePerson(employeeRankApply.getChangePerson());
            employeeRankApplyInfoDto.setBeginTime(employeeRankApply.getBeginTime());
            employeeRankApplyInfoDto.setChangeReason(employeeRankApply.getChangeReason());
            employeeRankApplyInfoDto.setInputUserId(employeeRankApply.getInputUserId());
            employeeRankApplyInfoDto.setInputTime(employeeRankApply.getInputTime());
            employeeRankApplyInfoDto.setInputOrgId(employeeRankApply.getInputOrgId());
            employeeRankApplyInfoDto.setUpdateUserId(employeeRankApply.getUpdateUserId());
            employeeRankApplyInfoDto.setUpdateTime(employeeRankApply.getUpdateTime());
            employeeRankApplyInfoDto.setUpdateOrgId(employeeRankApply.getUpdateOrgId());
            return employeeRankApplyInfoDto;
        }

        return null;
    }

    /**
     * 员工职级申请Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeRankApplyInfoDtoSave(@Valid EmployeeRankApplyInfoDtoSaveReq employeeRankApplyInfoDtoSaveReq) {
        employeeRankApplyInfoDtoSaveAction(employeeRankApplyInfoDtoSaveReq);
    }
    /**
     * 员工职级申请Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeRankApplyInfoDtoSaveAction(EmployeeRankApplyInfoDto employeeRankApplyInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeRankApplyInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
