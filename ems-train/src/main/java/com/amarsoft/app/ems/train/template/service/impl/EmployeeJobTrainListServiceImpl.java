package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeJobTrainListService;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainList;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListSaveReq;
import com.amarsoft.app.ems.train.entity.EmployeeJobTrain;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListDeleteReq;

/**
 * 在职培训列表Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeJobTrainListServiceImpl implements EmployeeJobTrainListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeJobTrainListReqQuery implements RequestQuery<EmployeeJobTrainListQueryReq> {
        @Override
        public Query apply(EmployeeJobTrainListQueryReq employeeJobTrainListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeJobTrainListQueryReq, EmployeeJobTrainList.class);
            
            String sql = "select TC.serialNo as serialNo,TC.theme as theme,TC.lecturer as lecturer,TC.trainDate as trainDate,TC.trainStatus as trainStatus"
                +" from EMPLOYEE_INJOB_TRAIN TC"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeJobTrainListRspConvert implements Convert<EmployeeJobTrainList> {
        @Override
        public EmployeeJobTrainList apply(BusinessObject bo) {
            EmployeeJobTrainList temp = new EmployeeJobTrainList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setTheme(bo.getString("Theme"));
            temp.setLecturer(bo.getString("Lecturer"));
            temp.setTrainDate(bo.getString("TrainDate"));
            temp.setTrainStatus(bo.getString("TrainStatus"));
            
            return temp;
        }
    }

    /**
     * 在职培训列表多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeJobTrainListQueryRsp employeeJobTrainListQuery(@Valid EmployeeJobTrainListQueryReq employeeJobTrainListQueryReq) {
        EmployeeJobTrainListQueryRsp employeeJobTrainListQueryRsp = new EmployeeJobTrainListQueryRsp();
        
        Query query = new EmployeeJobTrainListReqQuery().apply(employeeJobTrainListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeJobTrainListRspConvert convert = new EmployeeJobTrainListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeJobTrainListQueryReq.getBegin(), employeeJobTrainListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeJobTrainList> employeeJobTrainLists = new ArrayList<EmployeeJobTrainList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeJobTrainLists.add(convert.apply(bo));
            }
            employeeJobTrainListQueryRsp.setEmployeeJobTrainLists(employeeJobTrainLists);
        }
        employeeJobTrainListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeJobTrainListQueryRsp;
    }

    /**
     * 在职培训列表多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeJobTrainListSave(@Valid EmployeeJobTrainListSaveReq employeeJobTrainListSaveReq) {
        employeeJobTrainListSaveAction(employeeJobTrainListSaveReq.getEmployeeJobTrainLists());
    }
    /**
     * 在职培训列表多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeJobTrainListSaveAction(List<EmployeeJobTrainList> employeeJobTrainLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeJobTrainLists!=null){
            for(EmployeeJobTrainList employeeJobTrainListTmp :employeeJobTrainLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 在职培训列表删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeJobTrainListDelete(@Valid EmployeeJobTrainListDeleteReq employeeJobTrainListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeJobTrain employeeJobTrain=bomanager.keyLoadBusinessObject(EmployeeJobTrain.class, employeeJobTrainListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeJobTrain);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
