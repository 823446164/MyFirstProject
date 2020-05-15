/*
 * 文件名：EmployeeBelongChangeInfoDtoServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：团队调整申请InfoService实现类
 * 修改人：xszhou
 * 修改时间：2020/5/15
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.template.service.impl;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.entity.EmployeeBelongChange;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeeBelongChangeInfoDtoService;

import lombok.extern.slf4j.Slf4j;


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
     * Description: 保存员工团队申请记录表<br>
     * @param EmployeeBelongChangeInfoDtoSaveReq
     * @see
     */
    @Override
    public void employeeBelongChangeInfoDtoSave(@RequestBody @Valid EmployeeBelongChangeInfoDtoSaveReq employeeBelongChangeInfoDtoSaveReq) {
        employeeBelongChangeInfoDtoSaveAction(employeeBelongChangeInfoDtoSaveReq);
    }
    /**
     * Description: 保存员工团队申请记录表<br>
     * @param EmployeeBelongChangeInfoDtoSaveReq
     * @see
     */
    @Transactional
    public void employeeBelongChangeInfoDtoSaveAction(EmployeeBelongChangeInfoDtoSaveReq req){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String orgId = GlobalShareContextHolder.getOrgId();
        String userId = GlobalShareContextHolder.getUserId();
        if(req!=null){//如果请求体不为空，则新增记录
            EmployeeBelongChange ebc = new EmployeeBelongChange();
            ebc.generateKey();
            ebc.setAdjustReason(req.getAdjustReason());
            ebc.setEmployeeNo(req.getEmployeeNo());
            ebc.setEmployeeName(req.getEmployeeName());
            ebc.setEmployeeAcct(req.getEmployeeAcct());
            ebc.setTeamNo(req.getBeforeTeam());
            ebc.setBeforeTeam(req.getBeforeTeam());
            ebc.setAfterTeam(req.getAfterTeam());
            ebc.setObjectType(req.getObjectType());
            ebc.setChangeManager(req.getChangeManager());
            ebc.setInputOrgId(orgId);
            ebc.setInputUserId(userId);
            ebc.setInputTime(LocalDateTime.now());
            bomanager.updateBusinessObject(ebc);
        }
        bomanager.updateDB();
    }
}
