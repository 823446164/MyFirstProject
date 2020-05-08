package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeTrainPerformListService;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformList;
import com.amarsoft.app.ems.train.entity.CodeLibrary;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListDeleteReq;

/**
 * 员工基础培训表现Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeTrainPerformListServiceImpl implements EmployeeTrainPerformListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeTrainPerformListReqQuery implements RequestQuery<EmployeeTrainPerformListQueryReq> {
        @Override
        public Query apply(EmployeeTrainPerformListQueryReq employeeTrainPerformListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeTrainPerformListQueryReq, EmployeeTrainPerformList.class);
            
            String sql = "select CL.codeNo as codeNo,CL.itemName as itemName,"
                +"TP.serialNo as serialNo,TP.itemNo as itemNo,TP.isComplete as isComplete,TP.completeness as completeness,TP.masterSitu as masterSitu"
                +" from CODE_LIBRARY CL,EMPLOYEE_TRAIN_PERFORM TP"
                +" where 1=1 and TP.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeTrainPerformListQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeTrainPerformListRspConvert implements Convert<EmployeeTrainPerformList> {
        @Override
        public EmployeeTrainPerformList apply(BusinessObject bo) {
            EmployeeTrainPerformList temp = new EmployeeTrainPerformList();
                
            //查询到的数据转换为响应实体
            temp.setCodeNo(bo.getString("CodeNo"));
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setItemNo(bo.getString("ItemNo"));
            temp.setItemName(bo.getString("ItemName"));
            temp.setIsComplete(bo.getString("IsComplete"));
            temp.setCompleteness(bo.getString("Completeness"));
            temp.setMasterSitu(bo.getString("MasterSitu"));
            
            return temp;
        }
    }

    /**
     * 员工基础培训表现多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeTrainPerformListQueryRsp employeeTrainPerformListQuery(@Valid EmployeeTrainPerformListQueryReq employeeTrainPerformListQueryReq) {
        EmployeeTrainPerformListQueryRsp employeeTrainPerformListQueryRsp = new EmployeeTrainPerformListQueryRsp();
        
        Query query = new EmployeeTrainPerformListReqQuery().apply(employeeTrainPerformListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeTrainPerformListRspConvert convert = new EmployeeTrainPerformListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeTrainPerformListQueryReq.getBegin(), employeeTrainPerformListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeTrainPerformList> employeeTrainPerformLists = new ArrayList<EmployeeTrainPerformList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeTrainPerformLists.add(convert.apply(bo));
            }
            employeeTrainPerformListQueryRsp.setEmployeeTrainPerformLists(employeeTrainPerformLists);
        }
        employeeTrainPerformListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeTrainPerformListQueryRsp;
    }

    /**
     * 员工基础培训表现删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeTrainPerformListDelete(@Valid EmployeeTrainPerformListDeleteReq employeeTrainPerformListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        CodeLibrary codeLibrary=bomanager.keyLoadBusinessObject(CodeLibrary.class, employeeTrainPerformListDeleteReq.getCodeNo());
        bomanager.deleteBusinessObject(codeLibrary);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
