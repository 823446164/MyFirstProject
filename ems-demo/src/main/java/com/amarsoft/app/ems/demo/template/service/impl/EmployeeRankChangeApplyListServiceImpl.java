package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeeRankChangeApplyListService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListQueryRsp;
import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyList;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListSaveReq;
import com.amarsoft.app.ems.demo.entity.EmployeeRankApply;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.demo.entity.TestInfoJfan;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListDeleteReq;

/**
 * 员工职级调整申请ListService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeRankChangeApplyListServiceImpl implements EmployeeRankChangeApplyListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeRankChangeApplyListReqQuery implements RequestQuery<EmployeeRankChangeApplyListQueryReq> {
        @Override
        public Query apply(EmployeeRankChangeApplyListQueryReq employeeRankChangeApplyListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeRankChangeApplyListQueryReq, EmployeeRankChangeApplyList.class);
            
            String sql = "select TIJ.employeeNo as employeeNo,TIJ.employeeName as employeeName,TIJ.employeeAcct as employeeAcct,"
                +"ERA.serialNo as serialNo,ERA.rankName as rankName,ERA.updateRankName as updateRankName,ERA.rankTime as rankTime,"
                + "ERA.inputUserId as inputUserId,ERA.inputTime as inputTime,ERA.inputOrgId as inputOrgId,ERA.updateUserId as updateUserId,"
                + "ERA.updateTime as updateTime,ERA.updateOrgId as updateOrgId"
                +" from TEST_INFO_JFAN5 TIJ,EMPLOYEE_RANK_APPLY_JFAN5 ERA"
                +" where 1=1 and ERA.approveStatus='PreSubmit' and TIJ.employeeNo=ERA.employeeNo";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeRankChangeApplyListRspConvert implements Convert<EmployeeRankChangeApplyList> {
        @Override
        public EmployeeRankChangeApplyList apply(BusinessObject bo) {
            EmployeeRankChangeApplyList temp = new EmployeeRankChangeApplyList();
                
            //查询到的数据转换为响应实体
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setEmployeeAcct(bo.getString("EmployeeAcct"));
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setRankName(bo.getString("RankName"));
            temp.setUpdateRankName(bo.getString("UpdateRankName"));
            String string = bo.getString("RankTime");
            temp.setRankTime(transferTime(string));
            temp.setInputUserId(bo.getString("InputUserId"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateUserId(bo.getString("UpdateUserId"));
            temp.setUpdateTime(bo.getString("UpdateTime"));
            temp.setUpdateOrgId(bo.getString("UpdateOrgId"));
            
            return temp;
        }
    }
    
    public static String transferTime (String time){
    	
    	DateTimeFormatter df = DateTimeFormatter.ofPattern(FormatType.DateTimeLongFormat.format);
    	LocalDateTime ldt = LocalDateTime.parse(time,df);
    	String formatTime = ldt.format(DateTimeFormatter.ofPattern(FormatType.DateFormat.format));
    	return formatTime;
    }

    /**
     * 员工职级调整申请List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankChangeApplyListQueryRsp employeeRankChangeApplyListQuery(@Valid EmployeeRankChangeApplyListQueryReq employeeRankChangeApplyListQueryReq) {
        EmployeeRankChangeApplyListQueryRsp employeeRankChangeApplyListQueryRsp = new EmployeeRankChangeApplyListQueryRsp();
        
        Query query = new EmployeeRankChangeApplyListReqQuery().apply(employeeRankChangeApplyListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeRankChangeApplyListRspConvert convert = new EmployeeRankChangeApplyListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeRankChangeApplyListQueryReq.getBegin(), employeeRankChangeApplyListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeRankChangeApplyList> employeeRankChangeApplyLists = new ArrayList<EmployeeRankChangeApplyList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeRankChangeApplyLists.add(convert.apply(bo));
            }
            employeeRankChangeApplyListQueryRsp.setEmployeeRankChangeApplyLists(employeeRankChangeApplyLists);
        }
        employeeRankChangeApplyListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeRankChangeApplyListQueryRsp;
    }

    /**
     * 员工职级调整申请List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeRankChangeApplyListSave(@Valid EmployeeRankChangeApplyListSaveReq employeeRankChangeApplyListSaveReq) {
        employeeRankChangeApplyListSaveAction(employeeRankChangeApplyListSaveReq.getEmployeeRankChangeApplyLists());
    }
    /**
     * 员工职级调整申请List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeRankChangeApplyListSaveAction(List<EmployeeRankChangeApplyList> employeeRankChangeApplyLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeRankChangeApplyLists!=null){
            for(EmployeeRankChangeApplyList employeeRankChangeApplyListTmp :employeeRankChangeApplyLists){
                EmployeeRankApply employeeRankApply = bomanager.keyLoadBusinessObject(EmployeeRankApply.class,employeeRankChangeApplyListTmp.getSerialNo());
                if(employeeRankApply==null){
                    employeeRankApply = new EmployeeRankApply();
                    employeeRankApply.generateKey();
                }
                employeeRankApply.setRankName(employeeRankChangeApplyListTmp.getRankName());
                employeeRankApply.setUpdateRankName(employeeRankChangeApplyListTmp.getUpdateRankName());
                employeeRankApply.setRankTime(DateHelper.getDateTime(employeeRankChangeApplyListTmp.getRankTime()));
                employeeRankApply.setInputUserId(employeeRankChangeApplyListTmp.getInputUserId());
                employeeRankApply.setInputTime(DateHelper.getDateTime(employeeRankChangeApplyListTmp.getInputTime()));
                employeeRankApply.setInputOrgId(employeeRankChangeApplyListTmp.getInputOrgId());
                employeeRankApply.setUpdateUserId(employeeRankChangeApplyListTmp.getUpdateUserId());
                employeeRankApply.setUpdateTime(DateHelper.getDateTime(employeeRankChangeApplyListTmp.getUpdateTime()));
                employeeRankApply.setUpdateOrgId(employeeRankChangeApplyListTmp.getUpdateOrgId());
                bomanager.updateBusinessObject(employeeRankApply);
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工职级调整申请List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeRankChangeApplyListDelete(@Valid EmployeeRankChangeApplyListDeleteReq employeeRankChangeApplyListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TestInfoJfan testInfoJfan=bomanager.keyLoadBusinessObject(TestInfoJfan.class, employeeRankChangeApplyListDeleteReq.getEmployeeNo());
        bomanager.deleteBusinessObject(testInfoJfan);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
