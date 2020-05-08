package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeTraceDetailInfoService;
import com.amarsoft.app.ems.train.entity.EmployeeTraceDetail;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfo;
import com.amarsoft.amps.arpe.util.DateHelper;

/**
 * 追踪内容详情Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeTraceDetailInfoServiceImpl implements EmployeeTraceDetailInfoService{
    /**
     * 追踪内容详情单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeTraceDetailInfoQueryRsp employeeTraceDetailInfoQuery(@Valid EmployeeTraceDetailInfoQueryReq employeeTraceDetailInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeTraceDetail employeeTraceDetail = bomanager.loadBusinessObject(EmployeeTraceDetail.class,"serialNo",employeeTraceDetailInfoQueryReq.getSerialNo());
        if(employeeTraceDetail!=null){
            EmployeeTraceDetailInfoQueryRsp employeeTraceDetailInfo = new EmployeeTraceDetailInfoQueryRsp();
            employeeTraceDetailInfo.setSerialNo(employeeTraceDetail.getSerialNo());
            employeeTraceDetailInfo.setEmployeeNo(employeeTraceDetail.getEmployeeNo());
            employeeTraceDetailInfo.setTraceNo(employeeTraceDetail.getTraceNo());
            employeeTraceDetailInfo.setDataNo(employeeTraceDetail.getDataNo());
            employeeTraceDetailInfo.setTraceDate(employeeTraceDetail.getTraceDate());
            employeeTraceDetailInfo.setAttention(employeeTraceDetail.getAttention());
            employeeTraceDetailInfo.setIsContinue(employeeTraceDetail.getIsContinue());
            employeeTraceDetailInfo.setTracker(employeeTraceDetail.getTracker());
            employeeTraceDetailInfo.setFeedbacker(employeeTraceDetail.getFeedbacker());
            employeeTraceDetailInfo.setRemark(employeeTraceDetail.getRemark());
            employeeTraceDetailInfo.setCodeQuality(employeeTraceDetail.getCodeQuality());
            employeeTraceDetailInfo.setTaskProgress(employeeTraceDetail.getTaskProgress());
            employeeTraceDetailInfo.setInitiative(employeeTraceDetail.getInitiative());
            employeeTraceDetailInfo.setResponsibility(employeeTraceDetail.getResponsibility());
            employeeTraceDetailInfo.setAttendance(employeeTraceDetail.getAttendance());
            employeeTraceDetailInfo.setBusinessKnow(employeeTraceDetail.getBusinessKnow());
            employeeTraceDetailInfo.setPsychology(employeeTraceDetail.getPsychology());
            employeeTraceDetailInfo.setWorkHelp(employeeTraceDetail.getWorkHelp());
            employeeTraceDetailInfo.setCommunicate(employeeTraceDetail.getCommunicate());
            employeeTraceDetailInfo.setKnowShare(employeeTraceDetail.getKnowShare());
            employeeTraceDetailInfo.setParticipation(employeeTraceDetail.getParticipation());
            return employeeTraceDetailInfo;
        }

        return null;
    }

    /**
     * 追踪内容详情单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeTraceDetailInfoSave(@Valid EmployeeTraceDetailInfoSaveReq employeeTraceDetailInfoSaveReq) {
        employeeTraceDetailInfoSaveAction(employeeTraceDetailInfoSaveReq);
    }
    /**
     * 追踪内容详情单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeTraceDetailInfoSaveAction(EmployeeTraceDetailInfo employeeTraceDetailInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeTraceDetailInfo!=null){
            EmployeeTraceDetail employeeTraceDetail = bomanager.keyLoadBusinessObject(EmployeeTraceDetail.class,employeeTraceDetailInfo.getSerialNo());
            if(employeeTraceDetail==null){
                employeeTraceDetail = new EmployeeTraceDetail();
                employeeTraceDetail.generateKey();
            }
            employeeTraceDetail.setEmployeeNo(employeeTraceDetailInfo.getEmployeeNo());
            employeeTraceDetail.setTraceNo(employeeTraceDetailInfo.getTraceNo());
            employeeTraceDetail.setDataNo(employeeTraceDetailInfo.getDataNo());
            employeeTraceDetail.setTraceDate(DateHelper.getDate(employeeTraceDetailInfo.getTraceDate()));
            employeeTraceDetail.setAttention(employeeTraceDetailInfo.getAttention());
            employeeTraceDetail.setIsContinue(employeeTraceDetailInfo.getIsContinue());
            employeeTraceDetail.setTracker(employeeTraceDetailInfo.getTracker());
            employeeTraceDetail.setFeedbacker(employeeTraceDetailInfo.getFeedbacker());
            employeeTraceDetail.setRemark(employeeTraceDetailInfo.getRemark());
            employeeTraceDetail.setCodeQuality(employeeTraceDetailInfo.getCodeQuality());
            employeeTraceDetail.setTaskProgress(employeeTraceDetailInfo.getTaskProgress());
            employeeTraceDetail.setInitiative(employeeTraceDetailInfo.getInitiative());
            employeeTraceDetail.setResponsibility(employeeTraceDetailInfo.getResponsibility());
            employeeTraceDetail.setAttendance(employeeTraceDetailInfo.getAttendance());
            employeeTraceDetail.setBusinessKnow(employeeTraceDetailInfo.getBusinessKnow());
            employeeTraceDetail.setPsychology(employeeTraceDetailInfo.getPsychology());
            employeeTraceDetail.setWorkHelp(employeeTraceDetailInfo.getWorkHelp());
            employeeTraceDetail.setCommunicate(employeeTraceDetailInfo.getCommunicate());
            employeeTraceDetail.setKnowShare(employeeTraceDetailInfo.getKnowShare());
            employeeTraceDetail.setParticipation(employeeTraceDetailInfo.getParticipation());
            bomanager.updateBusinessObject(employeeTraceDetail);
        }
        bomanager.updateDB();
    }
}
