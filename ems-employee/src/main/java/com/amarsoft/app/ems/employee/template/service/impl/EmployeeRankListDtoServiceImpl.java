/*
 * 文件名：EmployeeRankListDtoServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工职级ListService实现类
 * 修改人：dxiao
 * 修改时间：2020/05/14
 * 跟踪单号：
 * 修改单号：
 * 修改内容：修改注释
 */
package com.amarsoft.app.ems.employee.template.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.acsc.util.DTOHelper;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.entity.EmployeeRank;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankListDtoService;

import lombok.extern.slf4j.Slf4j;


/**
 * 员工职级ListService实现类
 * 
 * @author lding
 */
@Slf4j
@Service
public class EmployeeRankListDtoServiceImpl implements EmployeeRankListDtoService {
    /**
     * 查询结果集-展示员工职级情况左侧列表,历史职级只展示最新日期
     */
    public static class EmployeeRankListDtoReqQuery implements RequestQuery<EmployeeRankListDtoQueryReq> {
        @Override
        public Query apply(EmployeeRankListDtoQueryReq employeeRankListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeRankListDtoQueryReq, EmployeeRankListDto.class);

            String sql = "select ER.serialNo as serialNo,ER.employeeNo as employeeNo,ER.classify as classify,ER.goalDate as goalDate,ER.rank as rank,"
                        + "ER.direction as direction,ER.rankVersion as rankVersion,ER.inputUserId as inputUserId,ER.inputTime as inputTime,ER.inputOrgId as inputOrgId,"
                        + "ER.updateUserId as updateUserId,ER.updateTime as updateTime,ER.updateOrgId as updateOrgId,ER.rankIsFormal as rankIsFormal,ER.changeDate as changeDate"
                        + " from Employee_Rank ER" + " where 1=1 and ER.employeeNo = :employeeNo and ER.goalDate in (select max(ER.goalDate) from Employee_Rank ER group by rankVersion)";
         

            return queryProperties.assembleSql(sql, "employeeNo", employeeRankListDtoQueryReq.getEmployeeNo());
        }
    }

    /**
     * 查询到的数据转换为响应实体
     */
    public static class EmployeeRankListDtoRspConvert implements Convert<EmployeeRankListDto> {
        @Override
        public EmployeeRankListDto apply(BusinessObject bo) {
            EmployeeRankListDto temp = new EmployeeRankListDto();

            // 查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setClassify(bo.getString("Classify"));
            temp.setGoalDate(bo.getString("GoalDate"));
            temp.setRank(bo.getString("Rank"));
            temp.setDirection(bo.getString("Direction"));
            temp.setRankVersion(bo.getString("RankVersion"));
            temp.setInputUserId(bo.getString("InputUserId"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateUserId(bo.getString("UpdateUserId"));
            temp.setUpdateTime(bo.getString("UpdateTime"));
            temp.setUpdateOrgId(bo.getString("UpdateOrgId"));
            temp.setRankIsFormal(bo.getString("RankIsFormal"));
            temp.setChangeDate(bo.getString("ChangeDate"));

            return temp;
        }
    }

    /**
     * 
     * Description:员工职级List多记录查询<br>
     *
     * @param EmployeeRankListDtoQueryReq
     * @return EmployeeRankListDtoQueryRsp
     * @see
     */
    @Override
    @Transactional
    public EmployeeRankListDtoQueryRsp employeeRankListDtoQuery(@Valid EmployeeRankListDtoQueryReq employeeRankListDtoQueryReq) {
        EmployeeRankListDtoQueryRsp employeeRankListDtoQueryRsp = new EmployeeRankListDtoQueryRsp();

        Query query = new EmployeeRankListDtoReqQuery().apply(employeeRankListDtoQueryReq);
        String fullsql = query.getSql();

        EmployeeRankListDtoRspConvert convert = new EmployeeRankListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        //发送SQL
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeRankListDtoQueryReq.getBegin(),
            employeeRankListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();//获取list

        if (null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeRankListDto> employeeRankListDtos = new ArrayList<EmployeeRankListDto>();
            for (BusinessObject bo : businessObjectList) {
                // 查询到的数据转换为响应实体
                employeeRankListDtos.add(convert.apply(bo));
            }
            employeeRankListDtoQueryRsp.setEmployeeRankListDtos(employeeRankListDtos);
        }
        employeeRankListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));

        return employeeRankListDtoQueryRsp;
    }

    /**
     * 
     * Description:员工职级List单记录保存<br>
     *
     * @param EmployeeRankListDtoSaveReq
     * @return map
     * @see
     */
    @Override
    public Map<String, String> employeeRankListDtoSave(@Valid EmployeeRankListDtoSaveReq employeeRankListDtoSaveReq) {
        return employeeRankListDtoSaveAction(employeeRankListDtoSaveReq);
    }

    /**
     * 
     * Description:员工职级List单记录保存<br>
     *1-因需求变更,职级情况保存还未完成
     * @param EmployeeRankListDto
     * @return map
     * @see
     */
    @Transactional
    public Map<String, String> employeeRankListDtoSaveAction(EmployeeRankListDto employeeRankListDto) {
        //TODO dxiao
        // 获取业务管理器
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 校验保存
        if (employeeRankListDto != null) {
            // 新增对象
            EmployeeRank employeeRank = new EmployeeRank();
            // 如果主键为空,则新增主键
            if (StringUtils.isEmpty(employeeRankListDto.getSerialNo())) employeeRank.generateKey();
            // 将页面属性封装进实体类
            BeanUtils.copyProperties(employeeRankListDto, employeeRank);
            // 更新业务对象
            bomanager.updateBusinessObject(employeeRank);
        }
        else {
            throw new ALSException("EMS1007");
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
