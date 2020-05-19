package com.amarsoft.app.ems.employee.template.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.aecd.system.constant.UserRoles;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.acsc.util.DTOHelper;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.entity.EmployeeInfo;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.EmployeeListByUserQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.EmployeeListByUserQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.Filter;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoReq;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoRsp;
import com.amarsoft.app.ems.employee.template.service.EmployeeInfoListDtoService;
import com.amarsoft.app.ems.system.cs.client.OrgClient;
import com.amarsoft.app.ems.system.cs.client.RoleClient;
import com.amarsoft.app.ems.system.cs.client.TeamClient;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.UserTeamOrgInfo;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserAndRole;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryRsp;

import lombok.extern.slf4j.Slf4j;

/**
 * 员工信息ListService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeInfoListDtoServiceImpl implements EmployeeInfoListDtoService{
	    @Autowired
	    RoleClient roleClient;
	    @Autowired
	    TeamClient teamClient;
	    @Autowired
	    OrgClient orgClient;
	    
	    /**
	     * Description:查询结果集<br>
	     * @see
	     */
    public static class EmployeeInfoListDtoReqQuery implements RequestQuery<EmployeeInfoListDtoQueryReq> {
        @Override
        public Query apply(EmployeeInfoListDtoQueryReq employeeInfoListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeInfoListDtoQueryReq, EmployeeInfoListDto.class);
            
            String sql = "select EI.employeeNo as employeeNo,EI.employeeName as employeeName,EI.employeeAcct as employeeAcct,EI.phoneNum as phoneNum,EI.nowRank as nowRank,EI.goalRank as goalRank,EI.rntryTime as rntryTime,EI.changeTime as changeTime,EI.employeeStatus as employeeStatus,EI.resignationReason as resignationReason,EI.employeeeDucation as employeeeDucation,EI.graduationTime as graduationTime,EI.graduatedSchool as graduatedSchool,EI.major as major,EI.homeTown as homeTown,EI.inputUserId as inputUserId,EI.inputTime as inputTime,EI.inputOrgId as inputOrgId,EI.updateUserId as updateUserId,EI.updateTime as updateTime,EI.updateOrgId as updateOrgId,EI.employeeWorkStatus as employeeWorkStatus,"
                +"TI.teamName as teamName,TI.teamId as teamId"
            	+" from EMPLOYEE_INFO EI,SYS_TEAM_USER TU,SYS_TEAM_INFO TI"
                +" where 1=1 AND EI.employeeNo=TU.userId AND TI.teamId=TU.teamId";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
     * Description:查询到的数据转换为响应实体<br>
     * @param request
     * @return 
     * @see
     */
    public static class EmployeeInfoListDtoRspConvert implements Convert<EmployeeInfoListDto> {
        @Override
        public EmployeeInfoListDto apply(BusinessObject bo) {
            EmployeeInfoListDto temp = new EmployeeInfoListDto();
                
            //查询到的数据转换为响应实体
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setEmployeeName(bo.getString("EmployeeName"));
            temp.setEmployeeAcct(bo.getString("EmployeeAcct"));
            temp.setPhoneNum(bo.getString("PhoneNum"));
            temp.setNowRank(bo.getString("NowRank"));
            temp.setGoalRank(bo.getString("GoalRank"));
            temp.setRntryTime(bo.getString("RntryTime"));
            temp.setChangeTime(bo.getString("ChangeTime"));
            temp.setEmployeeStatus(bo.getString("EmployeeStatus"));
            temp.setResignationReason(bo.getString("ResignationReason"));
            temp.setEmployeeeDucation(bo.getString("EmployeeeDucation"));
            temp.setGraduationTime(bo.getString("GraduationTime"));
            temp.setGraduatedSchool(bo.getString("GraduatedSchool"));
            temp.setMajor(bo.getString("Major"));
            temp.setHomeTown(bo.getString("HomeTown"));
            temp.setInputUserId(bo.getString("InputUserId"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateUserId(bo.getString("UpdateUserId"));
            temp.setUpdateTime(bo.getString("UpdateTime"));
            temp.setUpdateOrgId(bo.getString("UpdateOrgId"));
            temp.setEmployeeWorkStatus(bo.getString("EmployeeWorkStatus"));
            temp.setTeamId(bo.getString("TeamId"));
            temp.setTeamName(bo.getString("TeamName"));
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
    public EmployeeInfoListDtoQueryRsp employeeInfoListDtoQuery(@Valid EmployeeInfoListDtoQueryReq employeeInfoListDtoQueryReq) {
        EmployeeInfoListDtoQueryRsp employeeInfoListDtoQueryRsp = new EmployeeInfoListDtoQueryRsp();
        
        Query query = new EmployeeInfoListDtoReqQuery().apply(employeeInfoListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeInfoListDtoRspConvert convert = new EmployeeInfoListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeInfoListDtoQueryReq.getBegin(), employeeInfoListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeInfoListDto> employeeInfoListDtos = new ArrayList<EmployeeInfoListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeInfoListDtos.add(convert.apply(bo));
            }
            employeeInfoListDtoQueryRsp.setEmployeeInfoListDtos(employeeInfoListDtos);
        }
        employeeInfoListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeInfoListDtoQueryRsp;
    }


    /**
     * Description:员工信息List删除<br>
     * @param request
     * @return 
     * @see
     */
    @Override
    @Transactional
    public void employeeInfoListDtoDelete(@Valid EmployeeInfoListDtoDeleteReq employeeInfoListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeInfo employeeInfo=bomanager.keyLoadBusinessObject(EmployeeInfo.class, employeeInfoListDtoDeleteReq.getEmployeeNo());
        bomanager.deleteBusinessObject(employeeInfo);
        
        bomanager.updateDB();
    }
    
    /**
     * Description:根据用户权限的员工查询<br>
     * @param EmployeeListByUserQueryReq()
     * @return EmployeeListByUserQueryRsp(List<EmployeeInfoDto>)
     * @see
     */
    @Override
    public EmployeeListByUserQueryRsp employeeListByUserQuery(@Valid @RequestBody EmployeeListByUserQueryReq req) {
        //1.后端自动获取用户ＩＤ和部门ＩＤ
        String userId ="test22";// GlobalShareContextHolder.getUserId();
        String orgId ="0001";// GlobalShareContextHolder.getOrgId();
        List<Filter> filters = req.getFilters();
        
        EmployeeListByUserQueryRsp rsp = null;
        //2.查询用户角色的信息
        RequestMessage<UserRoleQueryReq> reqMsg = new RequestMessage<UserRoleQueryReq>();
        UserRoleQueryReq userRoleQueryReq = new UserRoleQueryReq();
        userRoleQueryReq.setUserId(userId);
        reqMsg.setMessage(userRoleQueryReq);
        //3.获取用户角色
        ResponseEntity<ResponseMessage<UserRoleQueryRsp>> userRoleQuery = roleClient.userRoleQuery(reqMsg);
        List<UserAndRole> userRoles = userRoleQuery.getBody().getMessage().getUserRoles();

        if (StringUtils.isEmpty(userRoles)) {//4.为空则说明没有权限
            throw new ALSException("EMS1008");
        }
        UserAndRole userAndRole = userRoles.get(0); //5.获取用户的最高权限（099、110、210）
        if (UserRoles.Admin.id.equals(userAndRole.getRoleId())) {//6.如果用户为系统管理员，展示所有员工
            String id = "";
            rsp = showList(userAndRole.getRoleId(), id,filters);
         }else if (UserRoles.DeptManager.id.equals(userAndRole.getRoleId()) || UserRoles.DeputyManager.id.equals(userAndRole.getRoleId())) {//7.如果用户为部门管理员或者部门副经理，展示所在部门所有员工
             rsp = showList(userAndRole.getRoleId(), orgId,filters);
         }else if(UserRoles.TeamLeader.id.equals(userAndRole.getRoleId())){//8.如果用户为团队负责人，展示所在团队所有员工
             rsp = showList(userAndRole.getRoleId(), userId,filters);
         }else {//９．如果不是这些角色，则提示权限不足
             throw new ALSException("EMS1008");
        }
        return rsp; 
    }
   
    
    /**
     * Description: 根据条件获取员工列表公共部分<br>
     * @param roleId,id
     * @return EmployeeListByUserQueryRsp(List<EmployeeInfoDto>)
     * @see
     */
    public EmployeeListByUserQueryRsp showList(String roleId,String id,List<Filter> filters) {
        EmployeeListByUserQueryRsp rsp = new EmployeeListByUserQueryRsp();
        RequestMessage<OrgUserQueryReq> reqMessage = new RequestMessage<OrgUserQueryReq>();
        OrgUserQueryReq orgUserQueryReq = new OrgUserQueryReq();
        orgUserQueryReq.setRoleId(roleId);
        orgUserQueryReq.setId(id);
        reqMessage.setMessage(orgUserQueryReq);
        //1.获取员工其他信息
        ResponseEntity<ResponseMessage<OrgUserQueryRsp>> orgUserQuery = orgClient.orgUserQuery(reqMessage);
        List<UserTeamOrgInfo> utois = orgUserQuery.getBody().getMessage().getUserTeamOrgInfos();
        if (CollectionUtils.isEmpty(utois)) {
            throw new ALSException("EMS1022");
        }else {
          //2.获取所有的员工ＩＤ
            List<String> userIdList = new ArrayList<String>();
            for (UserTeamOrgInfo userTeamOrgInfo : utois) {
               userIdList.add(userTeamOrgInfo.getUserId()); 
            }
            //3.调用employeeListByEmployeeNo方法
            EmployeeListByEmplNoReq emplNoReq = new EmployeeListByEmplNoReq();
            emplNoReq.setFilters(filters);
            emplNoReq.setEmployeeNoList(userIdList);
            EmployeeListByEmplNoRsp emplNoRsp = employeeListByEmployeeNo(emplNoReq);
            //4.将用户ＩＤ一致的团队部门信息添加到员工详情信息上
            for(EmployeeInfoDto eiDto:emplNoRsp.getEmployeeInfoList()) {
                for(UserTeamOrgInfo utoi:utois) {
                    if(eiDto.getEmployeeNo().equals(utoi.getUserId())){
                        eiDto.setOrgId(utoi.getOrgId());
                        eiDto.setOrgName(utoi.getOrgName());
                        eiDto.setTeamId(utoi.getTeamId());
                        eiDto.setTeamName(utoi.getTeamName());
                    }
                }
            } 
            rsp.setTotalCount(emplNoRsp.getTotalCount());
            rsp.setEmployeeList(emplNoRsp.getEmployeeInfoList());
        }
        
        return rsp;
    }
    
    /**
     * Description: 根据条件获取员工列表<br>
     * @param EmployeeListByEmplNoReq(List(String))
     * @return EmployeeListByEmplNoRsp(List<EmployeeInfoDto>)  
     * @see
     */
    @Override
    public EmployeeListByEmplNoRsp employeeListByEmployeeNo(@Valid EmployeeListByEmplNoReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeListByEmplNoRsp rsp = new EmployeeListByEmplNoRsp();
        List<String> employeeNoList = req.getEmployeeNoList();
        String eNo = "";
        String eName = "";
        //获取查询条件
        if (!CollectionUtils.isEmpty(req.getFilters())) {//判断查询条件是否为空，为空则不获取值
            for (Filter  filter : req.getFilters()) {
                if ("employeeNo".equals(filter.getName())) {
                    eNo = filter.getValue()[0];
                }else if ("employeeName".equals(filter.getName())) {
                    eName = filter.getValue()[0];
                }
            }
        }

        String employeeId = StringUtils.isEmpty(eNo)? "%":eNo+"%";
        String employeeName = StringUtils.isEmpty(eNo)?"%":eName+"%";
        
        List<EmployeeInfoDto> employeeDtoList = new ArrayList<EmployeeInfoDto>();
        String userList = "";
        //1.拼接员工查询参数
        for(String employeeNo:employeeNoList) {
            userList += "'"+employeeNo+"',";
        }
        if(!StringUtils.isEmpty(userList)) {
            userList = userList.substring(0, userList.length()-1);
        }
        //２．查询所需员工们的信息
        List<EmployeeInfo> employeeList = bomanager.loadBusinessObjects(EmployeeInfo.class , "employeeNo in (" + userList +") and employeeNo like :employeeNo and employeeName like :employeeName","employeeNo",employeeId,"employeeName",employeeName);

        for(EmployeeInfo ei:employeeList) {
            EmployeeInfoDto eIDto = new EmployeeInfoDto();
            //３.复制信息（当两者属性大致相同）
            BeanUtils.copyProperties(ei, eIDto);
            employeeDtoList.add(eIDto);
        }
        rsp.setTotalCount(employeeList.size());
        rsp.setEmployeeInfoList(employeeDtoList);
        return rsp;
    }
    

    @Override
    public void employeeInfoListDtoSave(@Valid EmployeeInfoListDtoSaveReq employeeInfoListDtoSaveReq) {
        
        
    }
}
