package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeTraceRecordInfoService;
import com.amarsoft.app.ems.train.entity.EmployeeTraceRecord;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfo;

/**
 * 追踪记录详情Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeTraceRecordInfoServiceImpl implements EmployeeTraceRecordInfoService{
    /**
     * 追踪记录详情单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeTraceRecordInfoQueryRsp employeeTraceRecordInfoQuery(@Valid EmployeeTraceRecordInfoQueryReq employeeTraceRecordInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeTraceRecord employeeTraceRecord = bomanager.loadBusinessObject(EmployeeTraceRecord.class,"serialNo",employeeTraceRecordInfoQueryReq.getSerialNo());
        if(employeeTraceRecord!=null){
            EmployeeTraceRecordInfoQueryRsp employeeTraceRecordInfo = new EmployeeTraceRecordInfoQueryRsp();
            employeeTraceRecordInfo.setSerialNo(employeeTraceRecord.getSerialNo());
            employeeTraceRecordInfo.setEmployeeNo(employeeTraceRecord.getEmployeeNo());
            employeeTraceRecordInfo.setEmployeeName(employeeTraceRecord.getEmployeeName());
            employeeTraceRecordInfo.setSeriousLever(employeeTraceRecord.getSeriousLever());
            employeeTraceRecordInfo.setTraceStatus(employeeTraceRecord.getTraceStatus());
            employeeTraceRecordInfo.setInitiator(employeeTraceRecord.getInitiator());
            employeeTraceRecordInfo.setTeamLeader(employeeTraceRecord.getTeamLeader());
            return employeeTraceRecordInfo;
        }

        return null;
    }

    /**
     * 追踪记录详情单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeTraceRecordInfoSave(@Valid EmployeeTraceRecordInfoSaveReq employeeTraceRecordInfoSaveReq) {
        employeeTraceRecordInfoSaveAction(employeeTraceRecordInfoSaveReq);
    }
    /**
     * 追踪记录详情单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeTraceRecordInfoSaveAction(EmployeeTraceRecordInfo employeeTraceRecordInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeTraceRecordInfo!=null){
            EmployeeTraceRecord employeeTraceRecord = bomanager.keyLoadBusinessObject(EmployeeTraceRecord.class,employeeTraceRecordInfo.getSerialNo());
            if(employeeTraceRecord==null){
                employeeTraceRecord = new EmployeeTraceRecord();
                employeeTraceRecord.generateKey();
            }
            employeeTraceRecord.setEmployeeNo(employeeTraceRecordInfo.getEmployeeNo());
            employeeTraceRecord.setEmployeeName(employeeTraceRecordInfo.getEmployeeName());
            employeeTraceRecord.setSeriousLever(employeeTraceRecordInfo.getSeriousLever());
            employeeTraceRecord.setTraceStatus(employeeTraceRecordInfo.getTraceStatus());
            employeeTraceRecord.setInitiator(employeeTraceRecordInfo.getInitiator());
            employeeTraceRecord.setTeamLeader(employeeTraceRecordInfo.getTeamLeader());
            bomanager.updateBusinessObject(employeeTraceRecord);
        }
        bomanager.updateDB();
    }
}
