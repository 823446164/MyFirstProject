package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeOtherInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeOtherInfo;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDto;

/**
 * 员工其他说明InfoService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeOtherInfoDtoServiceImpl implements EmployeeOtherInfoDtoService{
    /**
     * 员工其他说明Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeOtherInfoDtoQueryRsp employeeOtherInfoDtoQuery(@Valid EmployeeOtherInfoDtoQueryReq employeeOtherInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeOtherInfo employeeOtherInfo = bomanager.loadBusinessObject(EmployeeOtherInfo.class,"serialNo",employeeOtherInfoDtoQueryReq.getSerialNo());
        if(employeeOtherInfo!=null){
            EmployeeOtherInfoDtoQueryRsp employeeOtherInfoDto = new EmployeeOtherInfoDtoQueryRsp();
            employeeOtherInfoDto.setSerialNo(employeeOtherInfo.getSerialNo());
            employeeOtherInfoDto.setEmployeeNo(employeeOtherInfo.getEmployeeNo());
            employeeOtherInfoDto.setSituationDescribe(employeeOtherInfo.getSituationDescribe());
            employeeOtherInfoDto.setInputUserId(employeeOtherInfo.getInputUserId());
            employeeOtherInfoDto.setInputTime(employeeOtherInfo.getInputTime());
            employeeOtherInfoDto.setInputOrgId(employeeOtherInfo.getInputOrgId());
            employeeOtherInfoDto.setUpdateUserId(employeeOtherInfo.getUpdateUserId());
            employeeOtherInfoDto.setUpdateTime(employeeOtherInfo.getUpdateTime());
            employeeOtherInfoDto.setUpdateOrgId(employeeOtherInfo.getUpdateOrgId());
            return employeeOtherInfoDto;
        }

        return null;
    }

    /**
     * 员工其他说明Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeOtherInfoDtoSave(@Valid EmployeeOtherInfoDtoSaveReq employeeOtherInfoDtoSaveReq) {
        employeeOtherInfoDtoSaveAction(employeeOtherInfoDtoSaveReq);
    }
    /**
     * 员工其他说明Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeOtherInfoDtoSaveAction(EmployeeOtherInfoDto employeeOtherInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeOtherInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
