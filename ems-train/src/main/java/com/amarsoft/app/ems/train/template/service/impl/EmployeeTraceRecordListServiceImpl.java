package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeTraceRecordListService;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordList;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListSaveReq;
import com.amarsoft.app.ems.train.entity.EmployeeTraceRecord;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListDeleteReq;

/**
 * 追踪记录列表Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeTraceRecordListServiceImpl implements EmployeeTraceRecordListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeTraceRecordListReqQuery implements RequestQuery<EmployeeTraceRecordListQueryReq> {
        @Override
        public Query apply(EmployeeTraceRecordListQueryReq employeeTraceRecordListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeTraceRecordListQueryReq, EmployeeTraceRecordList.class);
            
            String sql = "select TR.serialNo as serialNo,TR.employeeNo as employeeNo,TR.employeeName as employeeName,TR.seriousLever as seriousLever,TR.traceStatus as traceStatus,TR.initiator as initiator,TR.teamLeader as teamLeader"
                +" from EMPLOYEE_TRACE_RECORD TR"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeTraceRecordListRspConvert implements Convert<EmployeeTraceRecordList> {
        @Override
        public EmployeeTraceRecordList apply(BusinessObject bo) {
            EmployeeTraceRecordList temp = new EmployeeTraceRecordList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setSeriousLever(bo.getString("SeriousLever"));
            temp.setTraceStatus(bo.getString("TraceStatus"));
            temp.setInitiator(bo.getString("Initiator"));
            temp.setTeamLeader(bo.getString("TeamLeader"));
            
            return temp;
        }
    }

    /**
     * 追踪记录列表多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeTraceRecordListQueryRsp employeeTraceRecordListQuery(@Valid EmployeeTraceRecordListQueryReq employeeTraceRecordListQueryReq) {
        EmployeeTraceRecordListQueryRsp employeeTraceRecordListQueryRsp = new EmployeeTraceRecordListQueryRsp();
        
        Query query = new EmployeeTraceRecordListReqQuery().apply(employeeTraceRecordListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeTraceRecordListRspConvert convert = new EmployeeTraceRecordListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeTraceRecordListQueryReq.getBegin(), employeeTraceRecordListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeTraceRecordList> employeeTraceRecordLists = new ArrayList<EmployeeTraceRecordList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeTraceRecordLists.add(convert.apply(bo));
            }
            employeeTraceRecordListQueryRsp.setEmployeeTraceRecordLists(employeeTraceRecordLists);
        }
        employeeTraceRecordListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeTraceRecordListQueryRsp;
    }

    /**
     * 追踪记录列表多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeTraceRecordListSave(@Valid EmployeeTraceRecordListSaveReq employeeTraceRecordListSaveReq) {
        employeeTraceRecordListSaveAction(employeeTraceRecordListSaveReq.getEmployeeTraceRecordLists());
    }
    /**
     * 追踪记录列表多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeTraceRecordListSaveAction(List<EmployeeTraceRecordList> employeeTraceRecordLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeTraceRecordLists!=null){
            for(EmployeeTraceRecordList employeeTraceRecordListTmp :employeeTraceRecordLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 追踪记录列表删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeTraceRecordListDelete(@Valid EmployeeTraceRecordListDeleteReq employeeTraceRecordListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeTraceRecord employeeTraceRecord=bomanager.keyLoadBusinessObject(EmployeeTraceRecord.class, employeeTraceRecordListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeTraceRecord);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
