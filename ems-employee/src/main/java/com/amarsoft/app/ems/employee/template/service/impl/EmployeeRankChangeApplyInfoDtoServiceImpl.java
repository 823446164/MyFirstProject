/*
 * 文件名：EmployeeRankChangeApplyInfoDtoServiceImpl 
 * 版权：Copyright by www.amarsoft.com 
 * 描述：员工职级调整申请详情InfoService实现类 
 * 修改人：xucheng 
 * 修改时间：2020/05/20 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankChangeApplyInfoDtoService;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDto;
import com.amarsoft.app.ems.employee.entity.EmployeeRankApply;


@Slf4j
@Service
public class EmployeeRankChangeApplyInfoDtoServiceImpl implements EmployeeRankChangeApplyInfoDtoService {
    /**
     * 员工职级调整申请详情Info单记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankChangeApplyInfoDtoQueryRsp employeeRankChangeApplyInfoDtoQuery(@Valid EmployeeRankChangeApplyInfoDtoQueryReq employeeRankChangeApplyInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 根据员工ID和流水号查询员工信息和职级信息
        BusinessObjectAggregate<BusinessObject> employeeRankChangeApplyInfoDtoQueryRspBoa = bomanager.selectBusinessObjectsBySql(
            "select EI.employeeNo as employeeNo,EI.employeeName as employeeName,EI.employeeAcct as employeeAcct," + "ERA.serialNo as serialNo,ERA.rankName as rankName,ERA.updateRankName as updateRankName,ERA.rankTime as rankTime"
                                                                                                                                 + " from EmployeeInfo EI,EmployeeRankApply ERA"
                                                                                                                                 + " where 1=1 and ERA.serialNo = :serialNo and EI.employeeNo = :employeeNo",
            "serialNo", employeeRankChangeApplyInfoDtoQueryReq.getSerialNo(), "employeeNo",
            employeeRankChangeApplyInfoDtoQueryReq.getEmployeeNo());
        List<BusinessObject> employeeRankChangeApplyInfoDtoQueryRspBoList = employeeRankChangeApplyInfoDtoQueryRspBoa.getBusinessObjects();
        if (StringUtils.isEmpty(employeeRankChangeApplyInfoDtoQueryRspBoList) && employeeRankChangeApplyInfoDtoQueryRspBoList.size() > 0) {
            BusinessObject employeeRankChangeApplyInfoDtoQueryRspBo = employeeRankChangeApplyInfoDtoQueryRspBoList.get(0);
            EmployeeRankChangeApplyInfoDtoQueryRsp employeeRankChangeApplyInfoDto = new EmployeeRankChangeApplyInfoDtoQueryRsp();
            employeeRankChangeApplyInfoDto.setEmployeeNo(employeeRankChangeApplyInfoDtoQueryRspBo.getString("EmployeeNo"));
            employeeRankChangeApplyInfoDto.setEmployeeName(employeeRankChangeApplyInfoDtoQueryRspBo.getString("EmployeeName"));
            employeeRankChangeApplyInfoDto.setEmployeeAcct(employeeRankChangeApplyInfoDtoQueryRspBo.getString("EmployeeAcct"));
            employeeRankChangeApplyInfoDto.setSerialNo(employeeRankChangeApplyInfoDtoQueryRspBo.getString("SerialNo"));
            employeeRankChangeApplyInfoDto.setRankName(employeeRankChangeApplyInfoDtoQueryRspBo.getString("RankName"));
            employeeRankChangeApplyInfoDto.setUpdateRankName(employeeRankChangeApplyInfoDtoQueryRspBo.getString("UpdateRankName"));
            employeeRankChangeApplyInfoDto.setRankTime(employeeRankChangeApplyInfoDtoQueryRspBo.getString("RankTime"));
            return employeeRankChangeApplyInfoDto;
        }

        return null;
    }

    /**
     * 员工职级调整申请详情Info单记录保存
     * 
     * @param request
     * @return
     */
    @Override
    public void employeeRankChangeApplyInfoDtoSave(@Valid EmployeeRankChangeApplyInfoDtoSaveReq employeeRankChangeApplyInfoDtoSaveReq) {
        employeeRankChangeApplyInfoDtoSaveAction(employeeRankChangeApplyInfoDtoSaveReq);
    }

    /**
     * 员工职级调整申请详情Info单记录保存
     * 
     * @param
     * @return
     */
    @Transactional
    public void employeeRankChangeApplyInfoDtoSaveAction(EmployeeRankChangeApplyInfoDto employeeRankChangeApplyInfoDto) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (StringUtils.isEmpty(employeeRankChangeApplyInfoDto)) {
            EmployeeRankApply employeeRankApply = bomanager.keyLoadBusinessObject(EmployeeRankApply.class,
                employeeRankChangeApplyInfoDto.getSerialNo());
            String orgId = GlobalShareContextHolder.getOrgId();
            String userId = GlobalShareContextHolder.getUserId();
            if (StringUtils.isEmpty(employeeRankChangeApplyInfoDto.getRankTime())) {
                throw new ALSException("EMS1024");
            }
            LocalDate localDate = LocalDate.parse(employeeRankChangeApplyInfoDto.getRankTime(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            LocalDate nowDate = LocalDate.now();// 获取当前时间
            if (localDate.isBefore(nowDate)) {// 如果计划考核时间早于当前时间，提示异常信息
                throw new ALSException("EMS1023");
            }
            LocalDateTime localDateTime = localDate.atStartOfDay();
            if (StringUtils.isEmpty(employeeRankApply.getRankTime())) {// 如果数据库里没有计划时间就认为是新增
                // 根据流水号新增计划考核时间
                employeeRankApply.setRankTime(localDateTime);
                employeeRankApply.setInputOrgId(orgId);
                employeeRankApply.setInputUserId(userId);
                employeeRankApply.setInputTime(LocalDateTime.now());
                bomanager.updateBusinessObject(employeeRankApply);
            }
            else {//否则就是更新
                // 根据流水号更新计划考核时间
                employeeRankApply.setRankTime(localDateTime);
                employeeRankApply.setUpdateOrgId(orgId);
                employeeRankApply.setUpdateUserId(userId);
                employeeRankApply.setUpdateTime(LocalDateTime.now());
                bomanager.updateBusinessObject(employeeRankApply);
            }
        }
        bomanager.updateDB();
    }
}
