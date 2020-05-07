package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeJobTrainInfoService;
import com.amarsoft.app.ems.train.entity.EmployeeJobTrain;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfo;
import com.amarsoft.amps.arpe.util.DateHelper;

/**
 * 在职培训详情Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeJobTrainInfoServiceImpl implements EmployeeJobTrainInfoService{
    /**
     * 在职培训详情单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeJobTrainInfoQueryRsp employeeJobTrainInfoQuery(@Valid EmployeeJobTrainInfoQueryReq employeeJobTrainInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeJobTrain employeeJobTrain = bomanager.loadBusinessObject(EmployeeJobTrain.class,"serialNo",employeeJobTrainInfoQueryReq.getSerialNo());
        if(employeeJobTrain!=null){
            EmployeeJobTrainInfoQueryRsp employeeJobTrainInfo = new EmployeeJobTrainInfoQueryRsp();
            employeeJobTrainInfo.setSerialNo(employeeJobTrain.getSerialNo());
            employeeJobTrainInfo.setTheme(employeeJobTrain.getTheme());
            employeeJobTrainInfo.setLecturer(employeeJobTrain.getLecturer());
            employeeJobTrainInfo.setTrainDate(employeeJobTrain.getTrainDate());
            employeeJobTrainInfo.setStartDate(employeeJobTrain.getStartDate());
            employeeJobTrainInfo.setEndDate(employeeJobTrain.getEndDate());
            employeeJobTrainInfo.setTrainStatus(employeeJobTrain.getTrainStatus());
            employeeJobTrainInfo.setRecorder(employeeJobTrain.getRecorder());
            employeeJobTrainInfo.setRecordDate(employeeJobTrain.getRecordDate());
            return employeeJobTrainInfo;
        }

        return null;
    }

    /**
     * 在职培训详情单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeJobTrainInfoSave(@Valid EmployeeJobTrainInfoSaveReq employeeJobTrainInfoSaveReq) {
        employeeJobTrainInfoSaveAction(employeeJobTrainInfoSaveReq);
    }
    /**
     * 在职培训详情单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeJobTrainInfoSaveAction(EmployeeJobTrainInfo employeeJobTrainInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeJobTrainInfo!=null){
            EmployeeJobTrain employeeJobTrain = bomanager.keyLoadBusinessObject(EmployeeJobTrain.class,employeeJobTrainInfo.getSerialNo());
            if(employeeJobTrain==null){
                employeeJobTrain = new EmployeeJobTrain();
                employeeJobTrain.generateKey();
            }
            employeeJobTrain.setTheme(employeeJobTrainInfo.getTheme());
            employeeJobTrain.setLecturer(employeeJobTrainInfo.getLecturer());
            employeeJobTrain.setTrainDate(DateHelper.getDate(employeeJobTrainInfo.getTrainDate()));
            employeeJobTrain.setStartDate(DateHelper.getDate(employeeJobTrainInfo.getStartDate()));
            employeeJobTrain.setEndDate(DateHelper.getDate(employeeJobTrainInfo.getEndDate()));
            employeeJobTrain.setTrainStatus(employeeJobTrainInfo.getTrainStatus());
            employeeJobTrain.setRecorder(employeeJobTrainInfo.getRecorder());
            employeeJobTrain.setRecordDate(DateHelper.getDate(employeeJobTrainInfo.getRecordDate()));
            bomanager.updateBusinessObject(employeeJobTrain);
        }
        bomanager.updateDB();
    }
}
