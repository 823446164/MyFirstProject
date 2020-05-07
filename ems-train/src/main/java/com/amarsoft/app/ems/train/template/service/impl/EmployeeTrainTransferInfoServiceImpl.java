package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeTrainTransferInfoService;
import com.amarsoft.app.ems.train.entity.EmployeeTrainTransfer;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfo;
import com.amarsoft.amps.arpe.util.DateHelper;

/**
 * 培训转移记录Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeTrainTransferInfoServiceImpl implements EmployeeTrainTransferInfoService{
    /**
     * 培训转移记录单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeTrainTransferInfoQueryRsp employeeTrainTransferInfoQuery(@Valid EmployeeTrainTransferInfoQueryReq employeeTrainTransferInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeTrainTransfer employeeTrainTransfer = bomanager.loadBusinessObject(EmployeeTrainTransfer.class);
        if(employeeTrainTransfer!=null){
            EmployeeTrainTransferInfoQueryRsp employeeTrainTransferInfo = new EmployeeTrainTransferInfoQueryRsp();
            employeeTrainTransferInfo.setSerialNo(employeeTrainTransfer.getSerialNo());
            employeeTrainTransferInfo.setEmployeeName(employeeTrainTransfer.getEmployeeName());
            employeeTrainTransferInfo.setTransferType(employeeTrainTransfer.getTransferType());
            employeeTrainTransferInfo.setProjectNo(employeeTrainTransfer.getProjectNo());
            employeeTrainTransferInfo.setTeamNo(employeeTrainTransfer.getTeamNo());
            employeeTrainTransferInfo.setTransferReason(employeeTrainTransfer.getTransferReason());
            employeeTrainTransferInfo.setTransfer(employeeTrainTransfer.getTransfer());
            employeeTrainTransferInfo.setTransferDate(employeeTrainTransfer.getTransferDate());
            return employeeTrainTransferInfo;
        }

        return null;
    }

    /**
     * 培训转移记录单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeTrainTransferInfoSave(@Valid EmployeeTrainTransferInfoSaveReq employeeTrainTransferInfoSaveReq) {
        employeeTrainTransferInfoSaveAction(employeeTrainTransferInfoSaveReq);
    }
    /**
     * 培训转移记录单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeTrainTransferInfoSaveAction(EmployeeTrainTransferInfo employeeTrainTransferInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeTrainTransferInfo!=null){
            EmployeeTrainTransfer employeeTrainTransfer = bomanager.keyLoadBusinessObject(EmployeeTrainTransfer.class,employeeTrainTransferInfo.getSerialNo());
            if(employeeTrainTransfer==null){
                employeeTrainTransfer = new EmployeeTrainTransfer();
                employeeTrainTransfer.generateKey();
            }
            employeeTrainTransfer.setEmployeeName(employeeTrainTransferInfo.getEmployeeName());
            employeeTrainTransfer.setTransferType(employeeTrainTransferInfo.getTransferType());
            employeeTrainTransfer.setProjectNo(employeeTrainTransferInfo.getProjectNo());
            employeeTrainTransfer.setTeamNo(employeeTrainTransferInfo.getTeamNo());
            employeeTrainTransfer.setTransferReason(employeeTrainTransferInfo.getTransferReason());
            employeeTrainTransfer.setTransfer(employeeTrainTransferInfo.getTransfer());
            employeeTrainTransfer.setTransferDate(DateHelper.getDate(employeeTrainTransferInfo.getTransferDate()));
            bomanager.updateBusinessObject(employeeTrainTransfer);
        }
        bomanager.updateDB();
    }
}
