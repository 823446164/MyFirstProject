package com.amarsoft.app.ems.employee.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeDevelopTargetInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeDevelopTarget;
import com.amarsoft.app.ems.employee.entity.EmployeeProjectExperience;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDto;


/**
 * 员工成长目标跟踪InfoService实现类
 * 
 * @author lding
 */
@Slf4j
@Service
public class EmployeeDevelopTargetInfoDtoServiceImpl implements EmployeeDevelopTargetInfoDtoService {
    /**
     * 员工成长目标跟踪Info单记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeDevelopTargetInfoDtoQueryRsp employeeDevelopTargetInfoDtoQuery(@Valid EmployeeDevelopTargetInfoDtoQueryReq employeeDevelopTargetInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        EmployeeDevelopTarget employeeDevelopTarget = bomanager.loadBusinessObject(EmployeeDevelopTarget.class, "serialNo",
            employeeDevelopTargetInfoDtoQueryReq.getSerialNo());
        if (employeeDevelopTarget != null) {
            EmployeeDevelopTargetInfoDtoQueryRsp employeeDevelopTargetInfoDto = new EmployeeDevelopTargetInfoDtoQueryRsp();
            employeeDevelopTargetInfoDto.setSerialNo(employeeDevelopTarget.getSerialNo());
            employeeDevelopTargetInfoDto.setEmployeeNo(employeeDevelopTarget.getEmployeeNo());
            employeeDevelopTargetInfoDto.setRankNo(employeeDevelopTarget.getRankNo());
            employeeDevelopTargetInfoDto.setDesignTime(employeeDevelopTarget.getDesignTime());
            employeeDevelopTargetInfoDto.setTargetDescribe(employeeDevelopTarget.getTargetDescribe());
            employeeDevelopTargetInfoDto.setDescribeTime(employeeDevelopTarget.getDescribeTime());
            employeeDevelopTargetInfoDto.setDesignerId(employeeDevelopTarget.getDesignerId());
            employeeDevelopTargetInfoDto.setRecord(employeeDevelopTarget.getRecord());
            employeeDevelopTargetInfoDto.setImplStatus(employeeDevelopTarget.getImplStatus());
            employeeDevelopTargetInfoDto.setInputUserId(employeeDevelopTarget.getInputUserId());
            employeeDevelopTargetInfoDto.setInputTime(employeeDevelopTarget.getInputTime());
            employeeDevelopTargetInfoDto.setInputOrgId(employeeDevelopTarget.getInputOrgId());
            employeeDevelopTargetInfoDto.setUpdateUserId(employeeDevelopTarget.getUpdateUserId());
            employeeDevelopTargetInfoDto.setUpdateTime(employeeDevelopTarget.getUpdateTime());
            employeeDevelopTargetInfoDto.setUpdateOrgId(employeeDevelopTarget.getUpdateOrgId());
            return employeeDevelopTargetInfoDto;
        }

        return null;
    }

    /**
     * 员工成长目标跟踪Info单记录保存
     * 
     * @param request
     * @return
     */
    @Override
    public Map<String, String> employeeDevelopTargetInfoDtoSave(@Valid EmployeeDevelopTargetInfoDtoSaveReq employeeDevelopTargetInfoDtoSaveReq) {
        return employeeDevelopTargetInfoDtoSaveAction(employeeDevelopTargetInfoDtoSaveReq);
    }

    /**
     * 员工成长目标跟踪Info单记录保存
     * 
     * @param
     * @return
     */
    @Transactional
    public Map<String, String> employeeDevelopTargetInfoDtoSaveAction(EmployeeDevelopTargetInfoDto employeeDevelopTargetInfoDto) {
        LocalDateTime inputDate = LocalDateTime.now();
        // 获取业务管理对象
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (employeeDevelopTargetInfoDto != null) {
            // 根据主键获取成长目标跟踪详情
            EmployeeDevelopTarget employeeDevelopTarget = bomanager.keyLoadBusinessObject(EmployeeDevelopTarget.class,
                employeeDevelopTargetInfoDto.getSerialNo());
            // 获取当前登录用户id
            String userId = GlobalShareContextHolder.getUserId();
            // 判断新增还是修改
            if (StringUtils.isEmpty(employeeDevelopTarget)) {// 如果为空则新建对象
                employeeDevelopTarget = new EmployeeDevelopTarget();
                // 如果主键为空,则新增主键
                if (StringUtils.isEmpty(employeeDevelopTargetInfoDto.getSerialNo())) {
                    employeeDevelopTarget.generateKey();
                }
                else {
                    employeeDevelopTarget.setSerialNo(employeeDevelopTargetInfoDto.getSerialNo());
                }
                employeeDevelopTarget.setInputTime(inputDate);
                employeeDevelopTarget.setInputUserId(GlobalShareContextHolder.getUserId());
                employeeDevelopTarget.setInputOrgId(GlobalShareContextHolder.getOrgId());
            }
            // 主键不为空,判断是否有权限修改
            else {
                if (!userId.equals(employeeDevelopTarget.getInputUserId())) { // 当前用户和制定目标不一致则不能更新
                    throw new ALSException("EMS1004");
                }
            }
            // 将页面属性封装进实体类
            employeeDevelopTarget.setUpdateTime(inputDate);
            employeeDevelopTarget.setUpdateUserId(GlobalShareContextHolder.getUserId());
            employeeDevelopTarget.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
            employeeDevelopTarget.setEmployeeNo(employeeDevelopTargetInfoDto.getEmployeeNo());
            employeeDevelopTarget.setRankNo(employeeDevelopTargetInfoDto.getRankNo());
            // 日期格式化模板
            DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
            // String -> LocalDate
            LocalDate designTime = LocalDate.parse(employeeDevelopTargetInfoDto.getDesignTime(), sdf);
            employeeDevelopTarget.setDesignTime(designTime);
            // String -> LocalDate
            LocalDate describeTime = LocalDate.parse(employeeDevelopTargetInfoDto.getDescribeTime(), sdf);
            employeeDevelopTarget.setDescribeTime(describeTime);
            employeeDevelopTarget.setTargetDescribe(employeeDevelopTargetInfoDto.getTargetDescribe());
            employeeDevelopTarget.setDesignerId(employeeDevelopTargetInfoDto.getDesignerId());
            employeeDevelopTarget.setRecord(employeeDevelopTargetInfoDto.getRecord());
            employeeDevelopTarget.setImplStatus(employeeDevelopTargetInfoDto.getImplStatus());
            // 更新业务对象
            bomanager.updateBusinessObject(employeeDevelopTarget);

        }
        // 提交事务
        bomanager.updateDB();
        // 定义一个map封装返回信息 - 判断是否更新成功
        Map<String, String> map = new HashMap<String, String>();
        // 定义map,更新成功返回-Y
        map.put("status", "Y");
        return map;
    }
}
