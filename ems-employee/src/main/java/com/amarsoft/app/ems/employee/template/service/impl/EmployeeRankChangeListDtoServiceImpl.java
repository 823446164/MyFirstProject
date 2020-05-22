/*
 * 文件名：EmployeeRankChangeListDtoServiceImpl 版权：Copyright by www.amarsoft.com
 * 描述：员工职级调整情况ListService实现类 修改人：xucheng 修改时间：2020/5/22 跟踪单号： 修改单号： 修改内容：
 */
package com.amarsoft.app.ems.employee.template.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.amarsoft.aecd.employee.constant.RankVersion;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.employee.entity.EmployeeInfo;
import com.amarsoft.app.ems.employee.entity.EmployeeRank;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankChangeListDtoService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EmployeeRankChangeListDtoServiceImpl implements EmployeeRankChangeListDtoService {
   
    /**
     * 员工职级调整情况List多记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankChangeListDtoQueryRsp employeeRankChangeListDtoQuery(@Valid EmployeeRankChangeListDtoQueryReq employeeRankChangeListDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 查询员工当前职级信息
        List<BusinessObject> employeeRank = bomanager.selectBusinessObjectsBySql(
            "select ER.serialNo as serialNo,ER.employeeNo as employeeNo,ER.changeDate as changeDate,ER.evaluateRankLevel as evaluateRankLevel,ER.rank as rank,ER.currentAdjustPay as currentAdjustPay,"
                                                                                 + "EI.employeeNo as employeeNo,EI.employeePay as employeePay"
                                                                                 + " from EmployeeRank ER,EmployeeInfo EI"
                                                                                 + " where EI.employeeNo=ER.employeeNo and EI.employeeNo= :employeeNo and ER.rankVersion= :rankVersion",
            "employeeNo", employeeRankChangeListDtoQueryReq.getEmployeeNo(),
            "rankVersion", RankVersion.Current.id).getBusinessObjects();
        // 查询员工目标职级信息
        List<BusinessObject> employeeFutureRank = bomanager.selectBusinessObjectsBySql(
            "select ER.serialNo as serialNo,ER.changeDate as changeDate,ER.evaluateRankLevel as evaluateRankLevel,ER.rank as rank,ER.currentAdjustPay as currentAdjustPay"
                                                                                       + " from EmployeeRank ER"
                                                                                       + " where ER.rankVersion= :rankVersion",
             "rankVersion", RankVersion.Target.id).getBusinessObjects();

        // 员工职级信息响应体对象
        EmployeeRankChangeListDtoQueryRsp response = new EmployeeRankChangeListDtoQueryRsp();

        List<EmployeeRankChangeListDto> employeeRankChangeListDto = new ArrayList<EmployeeRankChangeListDto>();

        if (!CollectionUtils.isEmpty(employeeRank) && !CollectionUtils.isEmpty(employeeFutureRank)) {// 集合判空
            EmployeeRankChangeListDto temp = new EmployeeRankChangeListDto();
            for (BusinessObject bo : employeeRank) {// 遍历当前职级信息
                temp.setEmployeeNo(bo.getString("EmployeeNo"));
                temp.setChangeDate(bo.getString("ChangeDate"));
                temp.setEvaluateRankLevel(bo.getString("EvaluateRankLevel"));
                temp.setRank(bo.getString("Rank"));
                temp.setEmployeeNo(bo.getString("EmployeeNo"));
                temp.setEmployeePay(bo.getDouble("EmployeePay"));
                temp.setCurrentAdjustPay(bo.getDouble("CurrentAdjustPay"));
            }
            for (BusinessObject bos : employeeFutureRank) {// 遍历未来职级信息
                temp.setSerialNo(bos.getString("SerialNo"));
                temp.setFutureChangeDate(bos.getString("ChangeDate"));
                temp.setFutureRankLevel(bos.getString("EvaluateRankLevel"));
                temp.setFutureAdjustPay(bos.getDouble("CurrentAdjustPay"));
                temp.setFutureRank(bos.getString("Rank"));
            }
            temp.setAdjustPay(temp.getEmployeePay()+temp.getCurrentAdjustPay());//计算调整后工资
            employeeRankChangeListDto.add(temp);
        }
        response.setEmployeeRankChangeListDtos(employeeRankChangeListDto);

        return response;

    }

    /**
     * 员工职级调整情况List多记录保存
     * 
     * @param request
     * @return
     */
    @Override
    public void employeeRankChangeListDtoSave(@Valid EmployeeRankChangeListDtoSaveReq employeeRankChangeListDtoSaveReq) {
        employeeRankChangeListDtoSaveAction(employeeRankChangeListDtoSaveReq.getEmployeeRankChangeListDtos());
    }

    /**
     * 员工职级调整情况List多记录保存
     * 
     * @param
     * @return
     */
    @Transactional
    public void employeeRankChangeListDtoSaveAction(List<EmployeeRankChangeListDto> employeeRankChangeListDtos) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (employeeRankChangeListDtos != null) {
            for (EmployeeRankChangeListDto employeeRankChangeListDtoTmp : employeeRankChangeListDtos) {
                EmployeeRank employeeRank = bomanager.keyLoadBusinessObject(EmployeeRank.class, employeeRankChangeListDtoTmp.getSerialNo());
                employeeRank.setCurrentAdjustPay(employeeRankChangeListDtoTmp.getFutureAdjustPay());
                employeeRank.setUpdateUserId(employeeRankChangeListDtoTmp.getUpdateUserId());
                employeeRank.setUpdateTime(DateHelper.getDateTime(employeeRankChangeListDtoTmp.getUpdateTime()));
                employeeRank.setUpdateOrgId(employeeRankChangeListDtoTmp.getUpdateOrgId());
                bomanager.updateBusinessObject(employeeRank);
            }
        }
        bomanager.updateDB();
    }

    /**
     * 员工职级调整情况List删除
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeRankChangeListDtoDelete(@Valid EmployeeRankChangeListDtoDeleteReq employeeRankChangeListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeRank employeeRank = bomanager.keyLoadBusinessObject(EmployeeRank.class, employeeRankChangeListDtoDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeRank);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
