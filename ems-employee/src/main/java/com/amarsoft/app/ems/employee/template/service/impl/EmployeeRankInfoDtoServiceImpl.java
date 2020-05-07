package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeRank;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDto;

/**
 * 员工职级InfoService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeRankInfoDtoServiceImpl implements EmployeeRankInfoDtoService{
    /**
     * 员工职级Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankInfoDtoQueryRsp employeeRankInfoDtoQuery(@Valid EmployeeRankInfoDtoQueryReq employeeRankInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeRank employeeRank = bomanager.loadBusinessObject(EmployeeRank.class,"serialNo",employeeRankInfoDtoQueryReq.getSerialNo());
        if(employeeRank!=null){
            EmployeeRankInfoDtoQueryRsp employeeRankInfoDto = new EmployeeRankInfoDtoQueryRsp();
            employeeRankInfoDto.setSerialNo(employeeRank.getSerialNo());
            employeeRankInfoDto.setEmployeeNo(employeeRank.getEmployeeNo());
            employeeRankInfoDto.setClassify(employeeRank.getClassify());
            employeeRankInfoDto.setGoalDate(employeeRank.getGoalDate());
            employeeRankInfoDto.setRank(employeeRank.getRank());
            employeeRankInfoDto.setRankVersion(employeeRank.getRankVersion());
            employeeRankInfoDto.setInputUserId(employeeRank.getInputUserId());
            employeeRankInfoDto.setInputTime(employeeRank.getInputTime());
            employeeRankInfoDto.setInputOrgId(employeeRank.getInputOrgId());
            employeeRankInfoDto.setUpdateUserId(employeeRank.getUpdateUserId());
            employeeRankInfoDto.setUpdateTime(employeeRank.getUpdateTime());
            employeeRankInfoDto.setUpdateOrgId(employeeRank.getUpdateOrgId());
            employeeRankInfoDto.setRankIsFormal(employeeRank.getRankIsFormal());
            employeeRankInfoDto.setChangeDate(employeeRank.getChangeDate());
            return employeeRankInfoDto;
        }

        return null;
    }

    /**
     * 员工职级Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeRankInfoDtoSave(@Valid EmployeeRankInfoDtoSaveReq employeeRankInfoDtoSaveReq) {
        employeeRankInfoDtoSaveAction(employeeRankInfoDtoSaveReq);
    }
    /**
     * 员工职级Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeRankInfoDtoSaveAction(EmployeeRankInfoDto employeeRankInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeRankInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
