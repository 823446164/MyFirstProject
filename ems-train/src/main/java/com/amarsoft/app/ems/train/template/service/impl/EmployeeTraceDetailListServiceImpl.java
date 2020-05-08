package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeTraceDetailListService;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailList;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListSaveReq;
import com.amarsoft.app.ems.train.entity.EmployeeTraceDetail;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListDeleteReq;

/**
 * 追踪内容列表Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeTraceDetailListServiceImpl implements EmployeeTraceDetailListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeTraceDetailListReqQuery implements RequestQuery<EmployeeTraceDetailListQueryReq> {
        @Override
        public Query apply(EmployeeTraceDetailListQueryReq employeeTraceDetailListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeTraceDetailListQueryReq, EmployeeTraceDetailList.class);
            
            String sql = "select TD.serialNo as serialNo,TD.dataNo as dataNo,TD.traceDate as traceDate,TD.attention as attention,TD.feedbacker as feedbacker,TD.remark as remark"
                +" from EMPLOYEE_TRACE_DETAIL TD"
                +" where 1=1 and TD.traceNo = :traceNo";
            return queryProperties.assembleSql(sql,"traceNo",employeeTraceDetailListQueryReq.getTraceNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeTraceDetailListRspConvert implements Convert<EmployeeTraceDetailList> {
        @Override
        public EmployeeTraceDetailList apply(BusinessObject bo) {
            EmployeeTraceDetailList temp = new EmployeeTraceDetailList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setDataNo(bo.getString("DataNo"));
            temp.setTraceDate(bo.getString("TraceDate"));
            temp.setAttention(bo.getString("Attention"));
            temp.setFeedbacker(bo.getString("Feedbacker"));
            temp.setRemark(bo.getString("Remark"));
            
            return temp;
        }
    }

    /**
     * 追踪内容列表多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeTraceDetailListQueryRsp employeeTraceDetailListQuery(@Valid EmployeeTraceDetailListQueryReq employeeTraceDetailListQueryReq) {
        EmployeeTraceDetailListQueryRsp employeeTraceDetailListQueryRsp = new EmployeeTraceDetailListQueryRsp();
        
        Query query = new EmployeeTraceDetailListReqQuery().apply(employeeTraceDetailListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeTraceDetailListRspConvert convert = new EmployeeTraceDetailListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeTraceDetailListQueryReq.getBegin(), employeeTraceDetailListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeTraceDetailList> employeeTraceDetailLists = new ArrayList<EmployeeTraceDetailList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeTraceDetailLists.add(convert.apply(bo));
            }
            employeeTraceDetailListQueryRsp.setEmployeeTraceDetailLists(employeeTraceDetailLists);
        }
        employeeTraceDetailListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeTraceDetailListQueryRsp;
    }

    /**
     * 追踪内容列表多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeTraceDetailListSave(@Valid EmployeeTraceDetailListSaveReq employeeTraceDetailListSaveReq) {
        employeeTraceDetailListSaveAction(employeeTraceDetailListSaveReq.getEmployeeTraceDetailLists());
    }
    /**
     * 追踪内容列表多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeTraceDetailListSaveAction(List<EmployeeTraceDetailList> employeeTraceDetailLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeTraceDetailLists!=null){
            for(EmployeeTraceDetailList employeeTraceDetailListTmp :employeeTraceDetailLists){
                EmployeeTraceDetail employeeTraceDetail = bomanager.keyLoadBusinessObject(EmployeeTraceDetail.class,employeeTraceDetailListTmp.getSerialNo());
                if(employeeTraceDetail==null){
                    employeeTraceDetail = new EmployeeTraceDetail();
                    employeeTraceDetail.generateKey();
                }
                employeeTraceDetail.setDataNo(employeeTraceDetailListTmp.getDataNo());
                employeeTraceDetail.setTraceDate(DateHelper.getDate(employeeTraceDetailListTmp.getTraceDate()));
                employeeTraceDetail.setAttention(employeeTraceDetailListTmp.getAttention());
                employeeTraceDetail.setFeedbacker(employeeTraceDetailListTmp.getFeedbacker());
                employeeTraceDetail.setRemark(employeeTraceDetailListTmp.getRemark());
                bomanager.updateBusinessObject(employeeTraceDetail);
            }
        }
        bomanager.updateDB();
    }


    /**
     * 追踪内容列表删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeTraceDetailListDelete(@Valid EmployeeTraceDetailListDeleteReq employeeTraceDetailListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeTraceDetail employeeTraceDetail=bomanager.keyLoadBusinessObject(EmployeeTraceDetail.class, employeeTraceDetailListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeTraceDetail);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
