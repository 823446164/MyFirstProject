package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.StringUtils;
import javax.validation.Valid;

import org.bouncycastle.jcajce.provider.asymmetric.ecgost12.ECGOST2012SignatureSpi256;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeInfoListDtoService;
import com.amarsoft.app.ems.system.cs.client.RoleClient;
import com.amarsoft.app.ems.system.cs.client.TeamClient;
import com.amarsoft.app.ems.system.cs.dto.addteamuser.UserTeam;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserAndRole;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import com.amarsoft.amps.arem.exception.ALSException;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.EmployeeListByUser;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.EmployeeListByUserQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.EmployeeListByUserQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoReq;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoRsp;
import com.amarsoft.app.ems.employee.entity.EmployeeInfo;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoDeleteReq;

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
	    
    /**
                   * 查询结果集
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
                  * 查询到的数据转换为响应实体
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
     * 员工信息List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeInfoListDtoSave(@Valid EmployeeInfoListDtoSaveReq employeeInfoListDtoSaveReq) {
        employeeInfoListDtoSaveAction(employeeInfoListDtoSaveReq.getEmployeeInfoListDtos());
    }
    /**
     * 员工信息List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeInfoListDtoSaveAction(List<EmployeeInfoListDto> employeeInfoListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeInfoListDtos!=null){
            for(EmployeeInfoListDto employeeInfoListDtoTmp :employeeInfoListDtos){
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
    public void employeeInfoListDtoDelete(@Valid EmployeeInfoListDtoDeleteReq employeeInfoListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeInfo employeeInfo=bomanager.keyLoadBusinessObject(EmployeeInfo.class, employeeInfoListDtoDeleteReq.getEmployeeNo());
        bomanager.deleteBusinessObject(employeeInfo);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();
    }
    
    /**
     * Description:根据用户权限的员工查询<br>
     * ${tags}
     * @see
     */
    @Override
    public EmployeeListByUserQueryRsp employeeListByUserQuery(@Valid @RequestBody EmployeeListByUserQueryReq req) {
        //后端自动获取用户ＩＤ和部门ＩＤ
        String userId = GlobalShareContextHolder.getUserId();
        String orgId = GlobalShareContextHolder.getOrgId();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeListByUserQueryRsp rsp = null;
        RequestMessage<UserRoleQueryReq> reqMsg = new RequestMessage<UserRoleQueryReq>();
        UserRoleQueryReq userRoleQueryReq = new UserRoleQueryReq();
        userRoleQueryReq.setUserId(userId);
        reqMsg.setMessage(userRoleQueryReq);
        ResponseEntity<ResponseMessage<UserRoleQueryRsp>> userRoleQuery = roleClient.userRoleQuery(reqMsg);
        List<UserAndRole> userRoles = userRoleQuery.getBody().getMessage().getUserRoles();

        if (StringUtils.isEmpty(userRoles)) {//为空则说明没有权限
            throw new ALSException("EMS1008");
        }
        UserAndRole userAndRole = userRoles.get(0); //获取用户的最高权限（099、110、210）
        String roleId = userAndRole.getRoleId(); 
        if ("099".equals(roleId)) {//如果用户为系统管理员，展示所有员工
            EmployeeInfoListDtoQueryReq employeeInfoListDtoQueryReq = new EmployeeInfoListDtoQueryReq();
            EmployeeInfoListDtoQueryRsp query = employeeInfoListDtoQuery(employeeInfoListDtoQueryReq);
            List<EmployeeInfoListDto> employeeInfoLists = query.getEmployeeInfoListDtos();
            rsp.setTotalCount(employeeInfoLists.size());
            rsp.setEmployeeList(employeeInfoLists);    
         }else if ("110".equals(roleId)) {//如果用户为部门管理员，展示所在部门所有员工
            //TODO 将角色ｉｄ和ｏｒｇＩＤ封装到部门服务的请求体中         
         }else if("210".equals(roleId)){//如果用户为团队负责人，展示所在团队所有员工
            //TODO 将角色ｉｄ和ｕｓｅｒＩＤ封装到部门服务的请求体中      
         } 
        
        return rsp; 
    }
    
    /**
     * Description: 根据权限列出员工的公共方法体<br>
     * ${tags}
     * @see
     */
    public EmployeeListByUserQueryRsp showList(List<BusinessObject> businessObjects) {
        
        EmployeeListByUserQueryRsp rsp = new EmployeeListByUserQueryRsp();
        List<EmployeeInfoListDto> employeeList = new ArrayList<EmployeeInfoListDto>();
        EmployeeInfoListDto employee = null;
        if (!StringUtils.isEmpty(businessObjects)) {
            for (BusinessObject bo : businessObjects) {
                employee = new EmployeeInfoListDto();
                employee.setEmployeeNo(bo.getString("EmployeeNo"));
                employee.setEmployeeName(bo.getString("EmployeeName"));
                employee.setEmployeeAcct(bo.getString("EmployeeAcct"));
                employee.setPhoneNum(bo.getString("PhoneNum"));
                employee.setNowRank(bo.getString("NowRank"));
                employee.setGoalRank(bo.getString("GoalRank"));
                employee.setRntryTime(bo.getString("RntryTime"));
                employee.setChangeTime(bo.getString("ChangeTime"));
                employee.setEmployeeStatus(bo.getString("EmployeeStatus"));
                employee.setResignationReason(bo.getString("ResignationReason"));
                employee.setEmployeeeDucation(bo.getString("EmployeeeDucation"));
                employee.setGraduationTime(bo.getString("GraduationTime"));
                employee.setGraduatedSchool(bo.getString("GraduatedSchool"));
                employee.setMajor(bo.getString("Major"));
                employee.setHomeTown(bo.getString("HomeTown"));
                employee.setInputUserId(bo.getString("InputUserId"));
                employee.setInputTime(bo.getString("InputTime"));
                employee.setInputOrgId(bo.getString("InputOrgId"));
                employee.setUpdateUserId(bo.getString("UpdateUserId"));
                employee.setUpdateTime(bo.getString("UpdateTime"));
                employee.setUpdateOrgId(bo.getString("UpdateOrgId"));
                employee.setEmployeeWorkStatus(bo.getString("EmployeeWorkStatus"));
                employee.setTeamId(bo.getString("TeamId"));
                employee.setTeamName(bo.getString("TeamName"));
                employeeList.add(employee);
            }
        }
        rsp.setTotalCount(employeeList.size());
        rsp.setEmployeeList(employeeList);
        return rsp;
    }

    /**
     * Description: 根据条件获取员工列表<br>
     * ${tags}
     * @see
     */
    @Override
    public EmployeeListByEmplNoRsp employeeListByEmployeeNo(@Valid EmployeeListByEmplNoReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeListByEmplNoRsp rsp = new EmployeeListByEmplNoRsp();
        List<String> employeeNoList = req.getEmployeeNoList();
        List<EmployeeInfoDto> employeeDtoList = new ArrayList<EmployeeInfoDto>();
        String userList = "";
        //拼接员工查询参数
        for(String employeeNo:employeeNoList) {
            userList += "'"+employeeNo+"',";
        }
        if(!StringUtils.isEmpty(userList)) {
            userList = userList.substring(0, userList.length()-1);
        }
        List<EmployeeInfo> employeeList = bomanager.loadBusinessObjects(EmployeeInfo.class , "employeeNo in (" + userList+")");
        for(EmployeeInfo ei:employeeList) {
            EmployeeInfoDto eIDto = new EmployeeInfoDto();
            //复制信息（当两者属性大致相同）
            BeanUtils.copyProperties(ei, eIDto);
            employeeDtoList.add(eIDto);
        }
        rsp.setTotalCount(employeeList.size());
        rsp.setEmployeeInfoList(employeeDtoList);
        return rsp;
    }
}
