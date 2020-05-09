package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
 * @author lding
 */
@Slf4j
@Service
public class EmployeeDevelopTargetInfoDtoServiceImpl implements EmployeeDevelopTargetInfoDtoService{
    /**
     * 员工成长目标跟踪Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeDevelopTargetInfoDtoQueryRsp employeeDevelopTargetInfoDtoQuery(@Valid EmployeeDevelopTargetInfoDtoQueryReq employeeDevelopTargetInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeDevelopTarget employeeDevelopTarget = bomanager.loadBusinessObject(EmployeeDevelopTarget.class,"serialNo",employeeDevelopTargetInfoDtoQueryReq.getSerialNo());
        if(employeeDevelopTarget!=null){
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
     * @param request
     * @return
     */
    @Override
    public Map<String,String> employeeDevelopTargetInfoDtoSave(@Valid EmployeeDevelopTargetInfoDtoSaveReq employeeDevelopTargetInfoDtoSaveReq) {
        return employeeDevelopTargetInfoDtoSaveAction(employeeDevelopTargetInfoDtoSaveReq);
    }
    /**
     * 员工成长目标跟踪Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public Map<String, String> employeeDevelopTargetInfoDtoSaveAction(EmployeeDevelopTargetInfoDto employeeDevelopTargetInfoDto){
        //获取业务管理对象
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeDevelopTargetInfoDto!=null){
            //根据主键获取成长目标跟踪详情
            EmployeeDevelopTarget employeeDevelopTarget = bomanager.keyLoadBusinessObject(EmployeeDevelopTarget.class,employeeDevelopTargetInfoDto.getSerialNo());
            //判断新增还是修改
            if(employeeDevelopTarget == null) {//如果为空则新建对象
                employeeDevelopTarget = new EmployeeDevelopTarget();
                //如果主键为空,则新增主键
                if(employeeDevelopTargetInfoDto.getSerialNo() == null)
                    employeeDevelopTarget.generateKey();
            }
            //将页面属性封装进实体类
            BeanUtils.copyProperties(employeeDevelopTargetInfoDto, employeeDevelopTarget);
            //更新业务对象
            bomanager.updateBusinessObject(employeeDevelopTarget);
     
        }
        //提交事务
        bomanager.updateDB();
        //定义一个map封装返回信息 - 判断是否更新成功
        Map<String,String> map = new HashMap<String,String>();
        //定义map,更新成功返回-Y
        map.put("status", "Y");
        return map;
    }
}
