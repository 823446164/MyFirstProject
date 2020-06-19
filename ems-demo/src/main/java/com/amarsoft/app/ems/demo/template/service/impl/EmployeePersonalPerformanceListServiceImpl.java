package com.amarsoft.app.ems.demo.template.service.impl;

import com.amarsoft.app.ems.demo.entity.EmployeeRankApply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeePersonalPerformanceListService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceList;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListSaveReq;
import com.amarsoft.app.ems.demo.entity.EmployeePersonalPerformance;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListDeleteReq;

/**
 * 员工项目经历个人表现ListService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeePersonalPerformanceListServiceImpl implements EmployeePersonalPerformanceListService{
    /**
                   * 查询结果集
     */
    public static class EmployeePersonalPerformanceListReqQuery implements RequestQuery<EmployeePersonalPerformanceListQueryReq> {
        @Override
        public Query apply(EmployeePersonalPerformanceListQueryReq employeePersonalPerformanceListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeePersonalPerformanceListQueryReq, EmployeePersonalPerformanceList.class);
            
            String sql = "select EPP.serialNo as serialNo,EPP.employeeNo as employeeNo,EPP.projectNo as projectNo,EPP.factors as factors,EPP.abilityDescribe as abilityDescribe,EPP.score as score,EPP.inputUserId as inputUserId,EPP.inputTime as inputTime,EPP.inputOrgId as inputOrgId,EPP.updateUserId as updateUserId,EPP.updateTime as updateTime,EPP.updateOrgId as updateOrgId"
                +" from EMPLOYEE_PERSONAL_PERFORMANCE_JFAN5 EPP"
                +" where 1=1 and EPP.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeePersonalPerformanceListQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeePersonalPerformanceListRspConvert implements Convert<EmployeePersonalPerformanceList> {
        @Override
        public EmployeePersonalPerformanceList apply(BusinessObject bo) {
            EmployeePersonalPerformanceList temp = new EmployeePersonalPerformanceList();
                
            //查询到的数据转换为响应实体
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
     */
    @Override
    @Transactional
    public EmployeePersonalPerformanceListQueryRsp employeePersonalPerformanceListQuery(@Valid EmployeePersonalPerformanceListQueryReq employeePersonalPerformanceListQueryReq) {
        EmployeePersonalPerformanceListQueryRsp employeePersonalPerformanceListQueryRsp = new EmployeePersonalPerformanceListQueryRsp();
        
        Query query = new EmployeePersonalPerformanceListReqQuery().apply(employeePersonalPerformanceListQueryReq);
        String fullsql = query.getSql();
        
        EmployeePersonalPerformanceListRspConvert convert = new EmployeePersonalPerformanceListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeePersonalPerformanceListQueryReq.getBegin(), employeePersonalPerformanceListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeePersonalPerformanceList> employeePersonalPerformanceLists = new ArrayList<EmployeePersonalPerformanceList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeePersonalPerformanceLists.add(convert.apply(bo));
            }
            employeePersonalPerformanceListQueryRsp.setEmployeePersonalPerformanceLists(employeePersonalPerformanceLists);
        }
        employeePersonalPerformanceListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeePersonalPerformanceListQueryRsp;
    }

    /**
     * 员工项目经历个人表现List多记录保存
     */
    @Override
    public void employeePersonalPerformanceListSave(@Valid EmployeePersonalPerformanceListSaveReq employeePersonalPerformanceListSaveReq) {
        employeePersonalPerformanceListSaveAction(employeePersonalPerformanceListSaveReq.getEmployeePersonalPerformanceLists());
    }
    /**
     * 员工项目经历个人表现List多记录保存
     */
    @Transactional
    public void employeePersonalPerformanceListSaveAction(List<EmployeePersonalPerformanceList> employeePersonalPerformanceLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeePersonalPerformanceLists!=null){
            for (EmployeePersonalPerformanceList employeePersonalPerformanceList : employeePersonalPerformanceLists) {
            	EmployeePersonalPerformance employeePersonalPerformance = bomanager.keyLoadBusinessObject(EmployeePersonalPerformance.class,employeePersonalPerformanceList.getEmployeeNo());
            	if(employeePersonalPerformance==null){
            		employeePersonalPerformance = new EmployeePersonalPerformance();
            		employeePersonalPerformance.generateKey();
                }
            	employeePersonalPerformance.setEmployeeNo(employeePersonalPerformanceList.getEmployeeNo());
            	employeePersonalPerformance.setFactors(employeePersonalPerformanceList.getFactors());
            	employeePersonalPerformance.setAbilityDescribe(employeePersonalPerformanceList.getAbilityDescribe());
            	employeePersonalPerformance.setProjectNo(employeePersonalPerformanceList.getProjectNo());
            	employeePersonalPerformance.setScore(employeePersonalPerformanceList.getScore());
            	
            	bomanager.updateBusinessObject(employeePersonalPerformance);
            	
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工项目经历个人表现List删除
     */
    @Override
    @Transactional
    public void employeePersonalPerformanceListDelete(@Valid EmployeePersonalPerformanceListDeleteReq employeePersonalPerformanceListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeePersonalPerformance employeePersonalPerformance=bomanager.keyLoadBusinessObject(EmployeePersonalPerformance.class, employeePersonalPerformanceListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeePersonalPerformance);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
