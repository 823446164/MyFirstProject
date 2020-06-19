package com.amarsoft.app.ems.demo.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.demo.template.service.EmployeeListTestService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeRankApplySaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTest;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyList;
import com.amarsoft.app.ems.demo.entity.EmployeeRankApply;
import com.amarsoft.app.ems.demo.entity.TestInfoJfan;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestDeleteReq;

/**
 * 员工信息ListService实现类
 * @author jfan5
 */
@Slf4j
@Service
public class EmployeeListTestServiceImpl implements EmployeeListTestService{
    /**
                   * 查询结果集
     */
    public static class EmployeeListTestReqQuery implements RequestQuery<EmployeeListTestQueryReq> {
        @Override
        public Query apply(EmployeeListTestQueryReq employeeListTestQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeListTestQueryReq, EmployeeListTest.class);
            
            String sql = "select TIJ.employeeNo as employeeNo,TIJ.employeeName as employeeName,TIJ.employeeAcct as employeeAcct,TIJ.nowRank as nowRank,TIJ.employeeStatus as employeeStatus,"
            			+"TI.teamName as teamName,TI.teamId as teamId,OI.orgName as orgName "
            			+"from TEST_INFO_JFAN5 TIJ,SYS_TEAM_USER TU,SYS_TEAM_INFO TI,SYS_ORG_TEAM OT,SYS_ORG_INFO OI "
            			+"where 1=1 AND TIJ.employeeNo=TU.userId AND TI.teamId=TU.teamId AND OI.ORGID=OT.ORGID and OT.TEAMID=TI.TEAMID";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeListTestRspConvert implements Convert<EmployeeListTest> {
        @Override
        public EmployeeListTest apply(BusinessObject bo) {
            EmployeeListTest temp = new EmployeeListTest();
                
            //查询到的数据转换为响应实体
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setEmployeeAcct(bo.getString("EmployeeAcct"));
            temp.setNowRank(bo.getString("NowRank"));
            temp.setEmployeeStatus(bo.getString("EmployeeStatus"));
            temp.setTeamId(bo.getString("TeamId"));
            temp.setTeamName(bo.getString("TeamName"));
            temp.setOrgId(bo.getString("OrgId"));
            temp.setOrgName(bo.getString("OrgName"));
            
            return temp;
        }
    }

    /**
     * 员工信息List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeListTestQueryRsp employeeListTestQuery(@Valid EmployeeListTestQueryReq employeeListTestQueryReq) {
        EmployeeListTestQueryRsp employeeListTestQueryRsp = new EmployeeListTestQueryRsp();
        
        Query query = new EmployeeListTestReqQuery().apply(employeeListTestQueryReq);
        String fullsql = query.getSql();
        
        EmployeeListTestRspConvert convert = new EmployeeListTestRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeListTestQueryReq.getBegin(), employeeListTestQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeListTest> employeeListTests = new ArrayList<EmployeeListTest>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeListTests.add(convert.apply(bo));
            }
            employeeListTestQueryRsp.setEmployeeListTests(employeeListTests);
        }
        employeeListTestQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeListTestQueryRsp;
    }

    /**
     * 员工信息List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeListTestSave(@Valid EmployeeListTestSaveReq employeeListTestSaveReq) {
        employeeListTestSaveAction(employeeListTestSaveReq.getEmployeeListTests());
    }
    /**
     * 员工信息List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeListTestSaveAction(List<EmployeeListTest> employeeListTests){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeListTests!=null){
            for(EmployeeListTest employeeListTestTmp :employeeListTests){
                TestInfoJfan testInfoJfan = bomanager.keyLoadBusinessObject(TestInfoJfan.class,employeeListTestTmp.getEmployeeNo());
                if(testInfoJfan==null){
                    testInfoJfan = new TestInfoJfan();
                    testInfoJfan.generateKey();
                }
                testInfoJfan.setEmployeeName(employeeListTestTmp.getEmployeeName());
                testInfoJfan.setEmployeeAcct(employeeListTestTmp.getEmployeeAcct());
                testInfoJfan.setNowRank(employeeListTestTmp.getNowRank());
                testInfoJfan.setEmployeeStatus(employeeListTestTmp.getEmployeeStatus());
                testInfoJfan.setEmployeeStatus(employeeListTestTmp.getTeamName());
                testInfoJfan.setEmployeeStatus(employeeListTestTmp.getOrgName());
//                TeamInfo teamInfo = bomanager.keyLoadBusinessObject(TeamInfo.class,employeeListTestTmp.getTeamId());
//                if(teamInfo==null){
//                    teamInfo = new TeamInfo();
//                    teamInfo.generateKey();
//                }
//                teamInfo.setTeamName(employeeListTestTmp.getTeamName());
//                OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class,employeeListTestTmp.getOrgId());
//                if(orgInfo==null){
//                    orgInfo = new OrgInfo();
//                    orgInfo.generateKey();
//                }
//                orgInfo.setOrgName(employeeListTestTmp.getOrgName());
                bomanager.updateBusinessObject(testInfoJfan);
//                bomanager.updateBusinessObject(teamInfo);
//                bomanager.updateBusinessObject(orgInfo);
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工信息List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeListTestDelete(@Valid EmployeeListTestDeleteReq employeeListTestDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TestInfoJfan testInfoJfan=bomanager.keyLoadBusinessObject(TestInfoJfan.class, employeeListTestDeleteReq.getEmployeeNo());
        bomanager.deleteBusinessObject(testInfoJfan);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }

}
