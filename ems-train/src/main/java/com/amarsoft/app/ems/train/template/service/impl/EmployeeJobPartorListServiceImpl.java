package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeJobPartorListService;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorList;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListSaveReq;
import com.amarsoft.app.ems.train.entity.EmployeeJobPartor;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListDeleteReq;

/**
 * 在职培训参与人员列表Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeJobPartorListServiceImpl implements EmployeeJobPartorListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeJobPartorListReqQuery implements RequestQuery<EmployeeJobPartorListQueryReq> {
        @Override
        public Query apply(EmployeeJobPartorListQueryReq employeeJobPartorListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeJobPartorListQueryReq, EmployeeJobPartorList.class);
            
            String sql = "select TR.serialNo as serialNo,TR.trainNo as trainNo,TR.employeeNo as employeeNo,TR.attachmentNo as attachmentNo"
                +" from EMPLOYEE_INJOB_PARTOR TR"
                +" where 1=1 and TR.trainNo = :trainNo";
            return queryProperties.assembleSql(sql,"trainNo",employeeJobPartorListQueryReq.getTrainNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeJobPartorListRspConvert implements Convert<EmployeeJobPartorList> {
        @Override
        public EmployeeJobPartorList apply(BusinessObject bo) {
            EmployeeJobPartorList temp = new EmployeeJobPartorList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setTrainNo(bo.getString("TrainNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setAttachmentNo(bo.getString("AttachmentNo"));
            
            return temp;
        }
    }

    /**
     * 在职培训参与人员列表多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeJobPartorListQueryRsp employeeJobPartorListQuery(@Valid EmployeeJobPartorListQueryReq employeeJobPartorListQueryReq) {
        EmployeeJobPartorListQueryRsp employeeJobPartorListQueryRsp = new EmployeeJobPartorListQueryRsp();
        
        Query query = new EmployeeJobPartorListReqQuery().apply(employeeJobPartorListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeJobPartorListRspConvert convert = new EmployeeJobPartorListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeJobPartorListQueryReq.getBegin(), employeeJobPartorListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeJobPartorList> employeeJobPartorLists = new ArrayList<EmployeeJobPartorList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeJobPartorLists.add(convert.apply(bo));
            }
            employeeJobPartorListQueryRsp.setEmployeeJobPartorLists(employeeJobPartorLists);
        }
        employeeJobPartorListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeJobPartorListQueryRsp;
    }

    /**
     * 在职培训参与人员列表多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeJobPartorListSave(@Valid EmployeeJobPartorListSaveReq employeeJobPartorListSaveReq) {
        employeeJobPartorListSaveAction(employeeJobPartorListSaveReq.getEmployeeJobPartorLists());
    }
    /**
     * 在职培训参与人员列表多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeJobPartorListSaveAction(List<EmployeeJobPartorList> employeeJobPartorLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeJobPartorLists!=null){
            for(EmployeeJobPartorList employeeJobPartorListTmp :employeeJobPartorLists){
                EmployeeJobPartor employeeJobPartor = bomanager.keyLoadBusinessObject(EmployeeJobPartor.class,employeeJobPartorListTmp.getSerialNo());
                if(employeeJobPartor==null){
                    employeeJobPartor = new EmployeeJobPartor();
                    employeeJobPartor.generateKey();
                }
                employeeJobPartor.setTrainNo(employeeJobPartorListTmp.getTrainNo());
                employeeJobPartor.setEmployeeNo(employeeJobPartorListTmp.getEmployeeNo());
                employeeJobPartor.setAttachmentNo(employeeJobPartorListTmp.getAttachmentNo());
                bomanager.updateBusinessObject(employeeJobPartor);
            }
        }
        bomanager.updateDB();
    }


    /**
     * 在职培训参与人员列表删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeJobPartorListDelete(@Valid EmployeeJobPartorListDeleteReq employeeJobPartorListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeJobPartor employeeJobPartor=bomanager.keyLoadBusinessObject(EmployeeJobPartor.class, employeeJobPartorListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeJobPartor);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
