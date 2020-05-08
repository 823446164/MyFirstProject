package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeTargetRankInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeTargetRank;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDto;

/**
 * 目标职级申请InfoService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeTargetRankInfoDtoServiceImpl implements EmployeeTargetRankInfoDtoService{
    /**
     * 目标职级申请Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeTargetRankInfoDtoQueryRsp employeeTargetRankInfoDtoQuery(@Valid EmployeeTargetRankInfoDtoQueryReq employeeTargetRankInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeTargetRank employeeTargetRank = bomanager.loadBusinessObject(EmployeeTargetRank.class,"serialNo",employeeTargetRankInfoDtoQueryReq.getSerialNo());
        if(employeeTargetRank!=null){
            EmployeeTargetRankInfoDtoQueryRsp employeeTargetRankInfoDto = new EmployeeTargetRankInfoDtoQueryRsp();
            employeeTargetRankInfoDto.setSerialNo(employeeTargetRank.getSerialNo());
            employeeTargetRankInfoDto.setRankSerialNo(employeeTargetRank.getRankSerialNo());
            employeeTargetRankInfoDto.setEmployeeNo(employeeTargetRank.getEmployeeNo());
            employeeTargetRankInfoDto.setEmployeeName(employeeTargetRank.getEmployeeName());
            employeeTargetRankInfoDto.setRankName(employeeTargetRank.getRankName());
            employeeTargetRankInfoDto.setEvaluationRank(employeeTargetRank.getEvaluationRank());
            employeeTargetRankInfoDto.setTargetRank(employeeTargetRank.getTargetRank());
            employeeTargetRankInfoDto.setTargetRecord(employeeTargetRank.getTargetRecord());
            employeeTargetRankInfoDto.setEvaluationRankTime(employeeTargetRank.getEvaluationRankTime());
            employeeTargetRankInfoDto.setLabel(employeeTargetRank.getLabel());
            employeeTargetRankInfoDto.setGrade(employeeTargetRank.getGrade());
            employeeTargetRankInfoDto.setMasteryDegree(employeeTargetRank.getMasteryDegree());
            employeeTargetRankInfoDto.setChangeReason(employeeTargetRank.getChangeReason());
            employeeTargetRankInfoDto.setOpinion(employeeTargetRank.getOpinion());
            employeeTargetRankInfoDto.setTeamManager(employeeTargetRank.getTeamManager());
            employeeTargetRankInfoDto.setInputUserId(employeeTargetRank.getInputUserId());
            employeeTargetRankInfoDto.setInputTime(employeeTargetRank.getInputTime());
            employeeTargetRankInfoDto.setInputOrgId(employeeTargetRank.getInputOrgId());
            employeeTargetRankInfoDto.setUpdateUserId(employeeTargetRank.getUpdateUserId());
            employeeTargetRankInfoDto.setUpdateTime(employeeTargetRank.getUpdateTime());
            employeeTargetRankInfoDto.setUpdateOrgId(employeeTargetRank.getUpdateOrgId());
            return employeeTargetRankInfoDto;
        }

        return null;
    }

    /**
     * 目标职级申请Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeTargetRankInfoDtoSave(@Valid EmployeeTargetRankInfoDtoSaveReq employeeTargetRankInfoDtoSaveReq) {
        employeeTargetRankInfoDtoSaveAction(employeeTargetRankInfoDtoSaveReq);
    }
    /**
     * 目标职级申请Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeTargetRankInfoDtoSaveAction(EmployeeTargetRankInfoDto employeeTargetRankInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeTargetRankInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
