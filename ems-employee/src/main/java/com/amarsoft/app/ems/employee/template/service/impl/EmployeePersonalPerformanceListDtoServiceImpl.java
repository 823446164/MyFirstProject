/* 文件名：EmployeePersonalPerformanceListDtoServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：dxiao
 * 修改时间：2020/05/14
 * 跟踪单号：
 * 修改单号：
 * 修改内容：修改注释
 */
package com.amarsoft.app.ems.employee.template.service.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.acsc.util.DTOHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.entity.EmployeePersonalPerformance;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeePersonalPerformanceListDtoService;

import lombok.extern.slf4j.Slf4j;


/**
 * 员工项目经历个人表现ListService实现类
 * 
 * @author lding
 */
@Slf4j
@Service
public class EmployeePersonalPerformanceListDtoServiceImpl implements EmployeePersonalPerformanceListDtoService {
    /**
     * 查询结果集
     */
    public static class EmployeePersonalPerformanceListDtoReqQuery implements RequestQuery<EmployeePersonalPerformanceListDtoQueryReq> {
        @Override
        public Query apply(EmployeePersonalPerformanceListDtoQueryReq employeePersonalPerformanceListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeePersonalPerformanceListDtoQueryReq,
                EmployeePersonalPerformanceListDto.class);
            //根据projectNo项目编号查询个人表现
            String sql = "select EPP.serialNo as serialNo,EPP.employeeNo as employeeNo,EPP.projectNo as projectNo,EPP.factors as factors,"
                + "EPP.abilityDescribe as abilityDescribe,EPP.score as score,EPP.inputUserId as inputUserId,EPP.inputTime as inputTime,EPP.inputOrgId as inputOrgId,EPP.updateUserId as updateUserId,"
                + "EPP.updateTime as updateTime,EPP.updateOrgId as updateOrgId"
                + " from EMPLOYEE_PERSONAL_PERFORMANCE EPP" + " where 1=1 and EPP.projectNo = :projectNo";
            return queryProperties.assembleSql(sql, "projectNo", employeePersonalPerformanceListDtoQueryReq.getProjectNo());
        }
    }

    /**
     * 查询到的数据转换为响应实体
     */
    public static class EmployeePersonalPerformanceListDtoRspConvert implements Convert<EmployeePersonalPerformanceListDto> {
        @Override
        public EmployeePersonalPerformanceListDto apply(BusinessObject bo) {
            EmployeePersonalPerformanceListDto temp = new EmployeePersonalPerformanceListDto();

            // 查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setProjectNo(bo.getString("ProjectNo"));
            temp.setFactors(bo.getString("Factors"));
            temp.setAbilityDescribe(bo.getString("AbilityDescribe"));
            temp.setScore(bo.getString("Score"));
            temp.setInputUserId(bo.getString("InputUserId"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateUserId(bo.getString("UpdateUserId"));
            temp.setUpdateTime(bo.getString("UpdateTime"));
            temp.setUpdateOrgId(bo.getString("UpdateOrgId"));

            return temp;
        }
    }

    /**
     * 员工项目经历个人表现List多记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeePersonalPerformanceListDtoQueryRsp employeePersonalPerformanceListDtoQuery(@Valid EmployeePersonalPerformanceListDtoQueryReq employeePersonalPerformanceListDtoQueryReq) {
        EmployeePersonalPerformanceListDtoQueryRsp employeePersonalPerformanceListDtoQueryRsp = new EmployeePersonalPerformanceListDtoQueryRsp();

        Query query = new EmployeePersonalPerformanceListDtoReqQuery().apply(employeePersonalPerformanceListDtoQueryReq);
        //获取SQL
        String fullsql = query.getSql();

        EmployeePersonalPerformanceListDtoRspConvert convert = new EmployeePersonalPerformanceListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        //发送SQL
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(
            employeePersonalPerformanceListDtoQueryReq.getBegin(), employeePersonalPerformanceListDtoQueryReq.getPageSize(), fullsql,
            query.getParam());
        //处理结果集合
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();

        if (null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeePersonalPerformanceListDto> employeePersonalPerformanceListDtos = new ArrayList<EmployeePersonalPerformanceListDto>();
            for (BusinessObject bo : businessObjectList) {
                // 查询到的数据转换为响应实体
                employeePersonalPerformanceListDtos.add(convert.apply(bo));
            }
            employeePersonalPerformanceListDtoQueryRsp.setEmployeePersonalPerformanceListDtos(employeePersonalPerformanceListDtos);
        }
        employeePersonalPerformanceListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));

        return employeePersonalPerformanceListDtoQueryRsp;
    }

    /**
     * 
     * Description:员工项目经历个人表现-多记录保存 <br>
     *
     * @param EmployeePersonalPerformanceListDtoSaveReq
     * @return map
     * @see
     */
    @Override
    public Map<String, String> employeePersonalPerformanceListDtoSave(@Valid EmployeePersonalPerformanceListDtoSaveReq employeePersonalPerformanceListDtoSaveReq) {
        return employeePersonalPerformanceListDtoSaveAction(
            employeePersonalPerformanceListDtoSaveReq.getEmployeePersonalPerformanceListDtos());
    }

    /**
     * 
     * Description:员工项目经历个人表现-多记录保存 <br>
     *
     * @param employeePersonalPerformanceListDtos
     * @return map
     * @see
     */
    @Transactional
    public Map<String, String> employeePersonalPerformanceListDtoSaveAction(List<EmployeePersonalPerformanceListDto> employeePersonalPerformanceListDtos) {
        // 获取业务管理器
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 判断传入集合是否为空
        if (null != employeePersonalPerformanceListDtos && !employeePersonalPerformanceListDtos.isEmpty()) {
            // 新建集合
            List<EmployeePersonalPerformance> employeePersonalPerformances = new ArrayList<>();
            // 集合遍历
            for (EmployeePersonalPerformanceListDto employeePersonalPerformanceListDtoTmp : employeePersonalPerformanceListDtos) {
                // 根据主键获取查询信息
                EmployeePersonalPerformance employeePersonalPerformance = bomanager.keyLoadBusinessObject(EmployeePersonalPerformance.class,
                    employeePersonalPerformanceListDtoTmp.getSerialNo());
                // 判断更新或者插入
                if (null == employeePersonalPerformance) {// 对象为空,插入
                    // 新建对象
                    employeePersonalPerformance = new EmployeePersonalPerformance();
                    if (StringUtils.isEmpty(employeePersonalPerformance.getSerialNo())) {// 主键判空
                        employeePersonalPerformance.generateKey();
                    }
                    employeePersonalPerformance.setInputTime(LocalDateTime.now());
                    employeePersonalPerformance.setInputUserId(GlobalShareContextHolder.getUserId());
                    employeePersonalPerformance.setInputOrgId(GlobalShareContextHolder.getOrgId());
                }else {     
                    employeePersonalPerformance.setUpdateTime(LocalDateTime.now());
                    employeePersonalPerformance.setUpdateUserId(GlobalShareContextHolder.getUserId());
                    employeePersonalPerformance.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                }

                employeePersonalPerformance.setSerialNo(employeePersonalPerformanceListDtoTmp.getSerialNo());
                employeePersonalPerformance.setEmployeeNo(employeePersonalPerformanceListDtoTmp.getEmployeeNo());
                employeePersonalPerformance.setProjectNo(employeePersonalPerformanceListDtoTmp.getProjectNo());
                employeePersonalPerformance.setFactors(employeePersonalPerformanceListDtoTmp.getFactors());
                employeePersonalPerformance.setAbilityDescribe(employeePersonalPerformanceListDtoTmp.getAbilityDescribe());
                employeePersonalPerformance.setScore(employeePersonalPerformanceListDtoTmp.getScore());
    
                employeePersonalPerformances.add(employeePersonalPerformance);
            }
            bomanager.updateBusinessObjects(employeePersonalPerformances);
        }
        bomanager.updateDB();
        // 定义一个map封装返回信息 - 判断是否更新成功
        Map<String, String> map = new HashMap<String, String>();
        // 定义map,更新成功返回-Y
        map.put("status", "Y");
        return map;
    }
    
}
