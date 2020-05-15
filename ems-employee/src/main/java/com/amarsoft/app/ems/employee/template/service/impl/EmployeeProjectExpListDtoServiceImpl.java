/* 文件名：EmployeeProjectExpListDtoServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：dxiao
 * 修改时间：2020/05/15
 * 跟踪单号：
 * 修改单号：
 * 修改内容：使用项目经历查询方法
 */
package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.service.EmployeeProjectExpListDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoSaveReq;
import com.amarsoft.app.ems.employee.entity.EmployeeProjectExperience;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoDeleteReq;

/**
 * 员工项目经历ListService实现类
 * @author lding
 */
@Slf4j
@Service
public class EmployeeProjectExpListDtoServiceImpl implements EmployeeProjectExpListDtoService{
    /**
                   * 查询员工对应的项目经历
     */
    public static class EmployeeProjectExpListDtoReqQuery implements RequestQuery<EmployeeProjectExpListDtoQueryReq> {
        @Override
        public Query apply(EmployeeProjectExpListDtoQueryReq employeeProjectExpListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeProjectExpListDtoQueryReq, EmployeeProjectExpListDto.class);
            
            String sql = "select EPE.serialNo as serialNo,EPE.employeeNo as employeeNo,EPE.projectName as projectName,EPE.projectMangerId as projectMangerId,EPE.employeeJob as employeeJob,EPE.begainTime as begainTime,EPE.endTime as endTime,EPE.workDescribe as workDescribe,EPE.inputUserid as inputUserid,EPE.inputTime as inputTime,EPE.inputOrgId as inputOrgId,EPE.updateuserid as updateuserid,EPE.updatetime as updatetime,EPE.updateorgid as updateorgid"
                +" from EMPLOYEE_PROJECT_EXPERIENCE EPE"
                +" where 1=1 and EPE.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeProjectExpListDtoQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeProjectExpListDtoRspConvert implements Convert<EmployeeProjectExpListDto> {
        @Override
        public EmployeeProjectExpListDto apply(BusinessObject bo) {
            EmployeeProjectExpListDto temp = new EmployeeProjectExpListDto();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setEmployeeNo(bo.getString("EmployeeNo"));
            temp.setProjectName(bo.getString("ProjectName"));
            temp.setProjectMangerId(bo.getString("ProjectMangerId"));
            temp.setEmployeeJob(bo.getString("EmployeeJob"));
            temp.setBegainTime(bo.getString("BegainTime"));
            temp.setEndTime(bo.getString("EndTime"));
            temp.setWorkDescribe(bo.getString("WorkDescribe"));
            temp.setInputUserid(bo.getString("InputUserid"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateuserid(bo.getString("Updateuserid"));
            temp.setUpdatetime(bo.getString("Updatetime"));
            temp.setUpdateorgid(bo.getString("Updateorgid"));
            
            return temp;
        }
    }

    /**
     * 员工项目经历List多记录查询
     * @param EPE.employeeNo
     * @return EmployeeProjectExpListDto
     */
    @Override
    @Transactional
    public EmployeeProjectExpListDtoQueryRsp employeeProjectExpListDtoQuery(@Valid EmployeeProjectExpListDtoQueryReq employeeProjectExpListDtoQueryReq) {
        EmployeeProjectExpListDtoQueryRsp employeeProjectExpListDtoQueryRsp = new EmployeeProjectExpListDtoQueryRsp();
        
        Query query = new EmployeeProjectExpListDtoReqQuery().apply(employeeProjectExpListDtoQueryReq);
        String fullsql = query.getSql();
        
        EmployeeProjectExpListDtoRspConvert convert = new EmployeeProjectExpListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeProjectExpListDtoQueryReq.getBegin(), employeeProjectExpListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeProjectExpListDto> employeeProjectExpListDtos = new ArrayList<EmployeeProjectExpListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeProjectExpListDtos.add(convert.apply(bo));
            }
            employeeProjectExpListDtoQueryRsp.setEmployeeProjectExpListDtos(employeeProjectExpListDtos);
        }
        employeeProjectExpListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeProjectExpListDtoQueryRsp;
    }

    /**
     * 员工项目经历List多记录保存
     * @param request
     * @return
     */
    @Override
    public void employeeProjectExpListDtoSave(@Valid EmployeeProjectExpListDtoSaveReq employeeProjectExpListDtoSaveReq) {
        employeeProjectExpListDtoSaveAction(employeeProjectExpListDtoSaveReq.getEmployeeProjectExpListDtos());
    }
    /**
     * 员工项目经历List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void employeeProjectExpListDtoSaveAction(List<EmployeeProjectExpListDto> employeeProjectExpListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeProjectExpListDtos!=null){
            for(EmployeeProjectExpListDto employeeProjectExpListDtoTmp :employeeProjectExpListDtos){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 员工项目经历List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeProjectExpListDtoDelete(@Valid EmployeeProjectExpListDtoDeleteReq employeeProjectExpListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeProjectExperience employeeProjectExperience=bomanager.keyLoadBusinessObject(EmployeeProjectExperience.class, employeeProjectExpListDtoDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeProjectExperience);
        bomanager.updateDB();

    }
}
