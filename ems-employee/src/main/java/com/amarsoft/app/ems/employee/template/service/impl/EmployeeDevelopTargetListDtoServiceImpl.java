/*文件名：EmployeeDevelopTargetListDtoServiceImpl 
 * 版权：Copyright by www.amarsoft.com
 * 描述： 
 * 修改人：dxiao 
 * 修改时间：2020/05/09 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.template.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.acsc.util.DTOHelper;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.entity.EmployeeDevelopTarget;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.service.EmployeeDevelopTargetListDtoService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EmployeeDevelopTargetListDtoServiceImpl implements EmployeeDevelopTargetListDtoService {
    /**
     * 查询成长目标跟踪
     */
    public static class EmployeeDevelopTargetListDtoReqQuery implements RequestQuery<EmployeeDevelopTargetListDtoQueryReq> {
        @Override
        public Query apply(EmployeeDevelopTargetListDtoQueryReq employeeDevelopTargetListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeDevelopTargetListDtoQueryReq,
                EmployeeDevelopTargetListDto.class);

            String sql = "select EDT.serialNo as serialNo,EDT.employeeNo as employeeNo,EDT.rankNo as rankNo,EDT.designTime as designTime,EDT.targetDescribe as targetDescribe,"
                        + "EDT.describeTime as describeTime,EDT.designerId as designerId,EDT.record as record,EDT.traceUserId as traceUserId,EDT.implStatus as implStatus,EDT.inputUserId as inputUserId,"
                        + "EDT.inputTime as inputTime,EDT.inputOrgId as inputOrgId,EDT.updateUserId as updateUserId,EDT.updateTime as updateTime,EDT.updateOrgId as updateOrgId"
                        + " from EMPLOYEE_DEVELOP_TARGET EDT" + " where 1=1 and EDT.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql, "employeeNo", employeeDevelopTargetListDtoQueryReq.getEmployeeNo());
        }
    }

    /**
     * 查询到的数据转换为响应实体
     */
    public static class EmployeeDevelopTargetListDtoRspConvert implements Convert<EmployeeDevelopTargetListDto> {
        @Override
        public EmployeeDevelopTargetListDto apply(BusinessObject bo) {
            EmployeeDevelopTargetListDto temp = new EmployeeDevelopTargetListDto();

            // 查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setRankNo(bo.getString("RankNo"));
            temp.setDesignTime(bo.getString("DesignTime"));
            temp.setTargetDescribe(bo.getString("TargetDescribe"));
            temp.setDescribeTime(bo.getString("DescribeTime"));
            temp.setDesignerId(bo.getString("DesignerId"));
            temp.setRecord(bo.getString("Record"));
            temp.setImplStatus(bo.getString("ImplStatus"));
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
     * 员工成长目标跟踪List多记录查询
     * 
     * @param begin
     * @param pagesSize
     * @param employeeNo
     * @return
     */
    @Override
    @Transactional
    public EmployeeDevelopTargetListDtoQueryRsp employeeDevelopTargetListDtoQuery(@Valid EmployeeDevelopTargetListDtoQueryReq employeeDevelopTargetListDtoQueryReq) {
        EmployeeDevelopTargetListDtoQueryRsp employeeDevelopTargetListDtoQueryRsp = new EmployeeDevelopTargetListDtoQueryRsp();

        Query query = new EmployeeDevelopTargetListDtoReqQuery().apply(employeeDevelopTargetListDtoQueryReq);
        String fullsql = query.getSql();

        EmployeeDevelopTargetListDtoRspConvert convert = new EmployeeDevelopTargetListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(
            employeeDevelopTargetListDtoQueryReq.getBegin(), employeeDevelopTargetListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();

        if (null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeDevelopTargetListDto> employeeDevelopTargetListDtos = new ArrayList<EmployeeDevelopTargetListDto>();
            for (BusinessObject bo : businessObjectList) {
                // 查询到的数据转换为响应实体
                employeeDevelopTargetListDtos.add(convert.apply(bo));
            }
            employeeDevelopTargetListDtoQueryRsp.setEmployeeDevelopTargetListDtos(employeeDevelopTargetListDtos);
        }
        employeeDevelopTargetListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));

        return employeeDevelopTargetListDtoQueryRsp;
    }

    /**
     * 员工成长目标跟踪List删除
     * 
     * @param EDT.serialNo
     * @return Y
     */
    @Override
    @Transactional
    public Map<String, String> employeeDevelopTargetListDtoDelete(@Valid EmployeeDevelopTargetListDtoDeleteReq employeeDevelopTargetListDtoDeleteReq) {
        // 获取业务对象
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 获取要删除的list对象
        EmployeeDevelopTarget employeeDevelopTarget = bomanager.keyLoadBusinessObject(EmployeeDevelopTarget.class,
            employeeDevelopTargetListDtoDeleteReq.getSerialNo());
  
        if (employeeDevelopTarget == null) {// 获取对象为空
            // 抛出异常信息
            throw new ALSException("EMS1005");
        }
        // 获取当前登录用户id
        String userId = GlobalShareContextHolder.getUserId();
        // 删除校验
        if (!userId.equals(employeeDevelopTarget.getInputUserId())) {
            // 当前登录人与目标制定人不一致,则不能删除
            throw new ALSException("EMS1003");
        }
        // 执行删除操作
        bomanager.deleteBusinessObject(employeeDevelopTarget);
        // 事务提交
        bomanager.updateDB();
        // 定义一个map封装返回信息 - 判断是否删除成功
        Map<String, String> map = new HashMap<String, String>();
        // 定义map,删除成功返回-Y
        map.put("status", "Y");
        return map;

    }
}
