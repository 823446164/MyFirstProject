package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;
import org.springframework.stereotype.Service;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeeInfoTestService;
import com.amarsoft.app.ems.demo.entity.EmployeeRankApply;
import com.amarsoft.app.ems.demo.entity.TestInfoJfan;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeRankApplySaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTest;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTest;
import com.amarsoft.amps.arpe.util.DateHelper;

/**
 * 员工信息InfoService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeInfoTestServiceImpl implements EmployeeInfoTestService{
    /**
     * 员工信息Info单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeInfoTestQueryRsp employeeInfoTestQuery(@Valid EmployeeInfoTestQueryReq employeeInfoTestQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        TestInfoJfan testInfoJfan = bomanager.loadBusinessObject(TestInfoJfan.class,"employeeNo",employeeInfoTestQueryReq.getEmployeeNo());
        if(testInfoJfan!=null){
            EmployeeInfoTestQueryRsp employeeInfoTest = new EmployeeInfoTestQueryRsp();
            employeeInfoTest.setEmployeeNo(testInfoJfan.getEmployeeNo());
            employeeInfoTest.setEmployeeName(testInfoJfan.getEmployeeName());
            employeeInfoTest.setEmployeeAcct(testInfoJfan.getEmployeeAcct());
            employeeInfoTest.setPhoneNum(testInfoJfan.getPhoneNum());
            employeeInfoTest.setNowRank(testInfoJfan.getNowRank());
            employeeInfoTest.setGoalRank(testInfoJfan.getGoalRank());
            employeeInfoTest.setRntryTime(testInfoJfan.getRntryTime());
            employeeInfoTest.setChangeTime(testInfoJfan.getChangeTime());
            employeeInfoTest.setEmployeeStatus(testInfoJfan.getEmployeeStatus());
            employeeInfoTest.setResignationReason(testInfoJfan.getResignationReason());
            employeeInfoTest.setEmployeeeDucation(testInfoJfan.getEmployeeeDucation());
            employeeInfoTest.setGraduationTime(testInfoJfan.getGraduationTime());
            employeeInfoTest.setGraduatedSchool(testInfoJfan.getGraduatedSchool());
            employeeInfoTest.setMajor(testInfoJfan.getMajor());
            employeeInfoTest.setHomeTown(testInfoJfan.getHomeTown());
            employeeInfoTest.setInputUserId(testInfoJfan.getInputUserId());
            employeeInfoTest.setInputTime(testInfoJfan.getInputTime());
            employeeInfoTest.setInputOrgId(testInfoJfan.getInputOrgId());
            employeeInfoTest.setUpdateUserId(testInfoJfan.getUpdateUserId());
            employeeInfoTest.setUpdateTime(testInfoJfan.getUpdateTime());
            employeeInfoTest.setUpdateOrgId(testInfoJfan.getUpdateOrgId());
            employeeInfoTest.setEmployeeWorkStatus(testInfoJfan.getEmployeeWorkStatus());
            employeeInfoTest.setEmployeePhoto(testInfoJfan.getEmployeePhoto());
            return employeeInfoTest;
        }

        return null;
    }

    /**
     * 员工信息Info单记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeInfoTestSave(@Valid EmployeeInfoTestSaveReq employeeInfoTestSaveReq) {
    	System.out.println(employeeInfoTestSaveReq);
        employeeInfoTestSaveAction(employeeInfoTestSaveReq);
    }
    /**
     * 员工信息Info单记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeInfoTestSaveAction(EmployeeInfoTest employeeInfoTest){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeInfoTest!=null){
            TestInfoJfan testInfoJfan = bomanager.keyLoadBusinessObject(TestInfoJfan.class,employeeInfoTest.getEmployeeNo());
            if(testInfoJfan==null){
                testInfoJfan = new TestInfoJfan();
                testInfoJfan.generateKey();
            }
            testInfoJfan.setEmployeeName(employeeInfoTest.getEmployeeName());
            testInfoJfan.setEmployeeAcct(employeeInfoTest.getEmployeeAcct());
            testInfoJfan.setPhoneNum(employeeInfoTest.getPhoneNum());
            testInfoJfan.setNowRank(employeeInfoTest.getNowRank());
            testInfoJfan.setGoalRank(employeeInfoTest.getGoalRank());
            testInfoJfan.setRntryTime(DateHelper.getDate(employeeInfoTest.getRntryTime()));
            testInfoJfan.setChangeTime(DateHelper.getDate(employeeInfoTest.getChangeTime()));
            testInfoJfan.setEmployeeStatus(employeeInfoTest.getEmployeeStatus());
            testInfoJfan.setResignationReason(employeeInfoTest.getResignationReason());
            testInfoJfan.setEmployeeeDucation(employeeInfoTest.getEmployeeeDucation());
            testInfoJfan.setGraduationTime(DateHelper.getDateTime(transferTime(employeeInfoTest.getGraduationTime())));
            testInfoJfan.setGraduatedSchool(employeeInfoTest.getGraduatedSchool());
            testInfoJfan.setMajor(employeeInfoTest.getMajor());
            testInfoJfan.setHomeTown(employeeInfoTest.getHomeTown());
            testInfoJfan.setInputUserId(employeeInfoTest.getInputUserId());
            testInfoJfan.setInputTime(DateHelper.getDateTime(transferTime(employeeInfoTest.getInputTime())));
            testInfoJfan.setInputOrgId(employeeInfoTest.getInputOrgId());
            testInfoJfan.setUpdateUserId(employeeInfoTest.getUpdateUserId());
            testInfoJfan.setUpdateTime(DateHelper.getDateTime(transferTime(employeeInfoTest.getUpdateTime())));
            testInfoJfan.setUpdateOrgId(employeeInfoTest.getUpdateOrgId());
            testInfoJfan.setEmployeeWorkStatus(employeeInfoTest.getEmployeeWorkStatus());
            testInfoJfan.setEmployeePhoto(employeeInfoTest.getEmployeePhoto());
            bomanager.updateBusinessObject(testInfoJfan);
        }
        bomanager.updateDB();
    }
    
    public String transferTime (String time){
    	
    	DateTimeFormatter df = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
    	LocalDateTime ldt = LocalDateTime.parse(time,df);
    	String formatTime = ldt.format(DateTimeFormatter.ofPattern(FormatType.DateTimeLongFormat.format));
    	return formatTime;
    }
    
    
    @Override
    public void employeeRankApply(@Valid EmployeeInfoTestSaveReq employeeInfoTestSaveReq) {
    	employeeRankApplySave(employeeInfoTestSaveReq);
    }
    
    @Transactional
	public void employeeRankApplySave(EmployeeInfoTest employeeInfoTest){
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeInfoTest!=null){
        	EmployeeRankApply employeeRankApply = bomanager.keyLoadBusinessObject(EmployeeRankApply.class,employeeInfoTest.getEmployeeNo());
            if(employeeRankApply==null){
            	employeeRankApply = new EmployeeRankApply();
            	employeeRankApply.generateKey();
            }
            employeeRankApply.setEmployeeNo(employeeInfoTest.getEmployeeNo());
            employeeRankApply.setEmployeeName(employeeInfoTest.getEmployeeName());
            employeeRankApply.setRankName(employeeInfoTest.getNowRank());
            employeeRankApply.setUpdateRankName(employeeInfoTest.getGoalRank());
            employeeRankApply.setRankNo(employeeInfoTest.getNowRank());
//            employeeRankApply.setEmployeeName(employeeInfoTest.getEmployeeName());
//            employeeRankApply.setEmployeeName(employeeInfoTest.getEmployeeName());
//            employeeRankApply.setEmployeeName(employeeInfoTest.getEmployeeName());
//            employeeRankApply.setEmployeeName(employeeInfoTest.getEmployeeName());
//            
            employeeRankApply.setApproveStatus("PreSubmit");
            bomanager.updateBusinessObject(employeeRankApply);
        }
        bomanager.updateDB();
	}
}
