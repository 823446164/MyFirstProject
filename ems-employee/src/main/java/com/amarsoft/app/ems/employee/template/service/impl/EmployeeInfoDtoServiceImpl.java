package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeInfo;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDto;

/**
 * 员工信息InfoService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeInfoDtoServiceImpl implements EmployeeInfoDtoService{
    /**
     * 员工信息Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeInfoDtoQueryRsp employeeInfoDtoQuery(@Valid EmployeeInfoDtoQueryReq employeeInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeInfo employeeInfo = bomanager.loadBusinessObject(EmployeeInfo.class,"serialNo",employeeInfoDtoQueryReq.getSerialNo());
        if(employeeInfo!=null){
            EmployeeInfoDtoQueryRsp employeeInfoDto = new EmployeeInfoDtoQueryRsp();
            employeeInfoDto.setEmployeeNo(employeeInfo.getEmployeeNo());
            employeeInfoDto.setEmployeeName(employeeInfo.getEmployeeName());
            employeeInfoDto.setEmployeeAcct(employeeInfo.getEmployeeAcct());
            employeeInfoDto.setPhoneNum(employeeInfo.getPhoneNum());
            employeeInfoDto.setNowRank(employeeInfo.getNowRank());
            employeeInfoDto.setGoalRank(employeeInfo.getGoalRank());
            employeeInfoDto.setRntryTime(employeeInfo.getRntryTime());
            employeeInfoDto.setChangeTime(employeeInfo.getChangeTime());
            employeeInfoDto.setEmployeeStatus(employeeInfo.getEmployeeStatus());
            employeeInfoDto.setResignationReason(employeeInfo.getResignationReason());
            employeeInfoDto.setEmployeeeDucation(employeeInfo.getEmployeeeDucation());
            employeeInfoDto.setGraduationTime(employeeInfo.getGraduationTime());
            employeeInfoDto.setGraduatedSchool(employeeInfo.getGraduatedSchool());
            employeeInfoDto.setMajor(employeeInfo.getMajor());
            employeeInfoDto.setHomeTown(employeeInfo.getHomeTown());
            employeeInfoDto.setInputUserId(employeeInfo.getInputUserId());
            employeeInfoDto.setInputTime(employeeInfo.getInputTime());
            employeeInfoDto.setInputOrgId(employeeInfo.getInputOrgId());
            employeeInfoDto.setUpdateUserId(employeeInfo.getUpdateUserId());
            employeeInfoDto.setUpdateTime(employeeInfo.getUpdateTime());
            employeeInfoDto.setUpdateOrgId(employeeInfo.getUpdateOrgId());
            employeeInfoDto.setEmployeeWorkStatus(employeeInfo.getEmployeeWorkStatus());
            return employeeInfoDto;
        }

        return null;
    }

    /**
     * 员工信息Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeInfoDtoSave(@Valid EmployeeInfoDtoSaveReq employeeInfoDtoSaveReq) {
        employeeInfoDtoSaveAction(employeeInfoDtoSaveReq);
    }
    /**
     * 员工信息Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeInfoDtoSaveAction(EmployeeInfoDto employeeInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
