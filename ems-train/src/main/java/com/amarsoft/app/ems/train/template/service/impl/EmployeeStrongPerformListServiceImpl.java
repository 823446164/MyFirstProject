package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeStrongPerformListService;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformList;
import com.amarsoft.app.ems.train.entity.CodeLibrary;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListDeleteReq;

/**
 * 员工强化培训表现Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeStrongPerformListServiceImpl implements EmployeeStrongPerformListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeStrongPerformListReqQuery implements RequestQuery<EmployeeStrongPerformListQueryReq> {
        @Override
        public Query apply(EmployeeStrongPerformListQueryReq employeeStrongPerformListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeStrongPerformListQueryReq, EmployeeStrongPerformList.class);
            
            String sql = "select CL.codeNo as codeNo,CL.itemName as itemName,"
                +"TP.serialNo as serialNo,TP.itemNo as itemNo,TP.capabilityDes as capabilityDes,TP.modelScore as modelScore"
                +" from CODE_LIBRARY CL,EMPLOYEE_TRAIN_PERFORM TP"
                +" where 1=1 and TP.employeeNo = :employeeNo and TP.projectNo = :projectNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeStrongPerformListQueryReq.getEmployeeNo(),"projectNo",employeeStrongPerformListQueryReq.getProjectNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeStrongPerformListRspConvert implements Convert<EmployeeStrongPerformList> {
        @Override
        public EmployeeStrongPerformList apply(BusinessObject bo) {
            EmployeeStrongPerformList temp = new EmployeeStrongPerformList();
                
            //查询到的数据转换为响应实体
            temp.setCodeNo(bo.getString("CodeNo"));
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setItemNo(bo.getString("ItemNo"));
            temp.setItemName(bo.getString("ItemName"));
            temp.setCapabilityDes(bo.getString("CapabilityDes"));
            temp.setModelScore(bo.getString("ModelScore"));
            
            return temp;
        }
    }

    /**
     * 员工强化培训表现多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeStrongPerformListQueryRsp employeeStrongPerformListQuery(@Valid EmployeeStrongPerformListQueryReq employeeStrongPerformListQueryReq) {
        EmployeeStrongPerformListQueryRsp employeeStrongPerformListQueryRsp = new EmployeeStrongPerformListQueryRsp();
        
        Query query = new EmployeeStrongPerformListReqQuery().apply(employeeStrongPerformListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeStrongPerformListRspConvert convert = new EmployeeStrongPerformListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeStrongPerformListQueryReq.getBegin(), employeeStrongPerformListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeStrongPerformList> employeeStrongPerformLists = new ArrayList<EmployeeStrongPerformList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeStrongPerformLists.add(convert.apply(bo));
            }
            employeeStrongPerformListQueryRsp.setEmployeeStrongPerformLists(employeeStrongPerformLists);
        }
        employeeStrongPerformListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeStrongPerformListQueryRsp;
    }

    /**
     * 员工强化培训表现删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeStrongPerformListDelete(@Valid EmployeeStrongPerformListDeleteReq employeeStrongPerformListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        CodeLibrary codeLibrary=bomanager.keyLoadBusinessObject(CodeLibrary.class, employeeStrongPerformListDeleteReq.getCodeNo());
        bomanager.deleteBusinessObject(codeLibrary);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
