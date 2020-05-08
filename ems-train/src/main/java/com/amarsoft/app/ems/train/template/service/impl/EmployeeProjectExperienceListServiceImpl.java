package com.amarsoft.app.ems.train.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.train.template.service.EmployeeProjectExperienceListService;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceList;
import com.amarsoft.app.ems.employee.entity.EmployeeProjectExperience;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListDeleteReq;

/**
 * 员工参与培训项目列表Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class EmployeeProjectExperienceListServiceImpl implements EmployeeProjectExperienceListService{
    /**
                   * 查询结果集
     */
    public static class EmployeeProjectExperienceListReqQuery implements RequestQuery<EmployeeProjectExperienceListQueryReq> {
        @Override
        public Query apply(EmployeeProjectExperienceListQueryReq employeeProjectExperienceListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(employeeProjectExperienceListQueryReq, EmployeeProjectExperienceList.class);
            
            String sql = "select PE.serialNo as serialNo,PE.projectName as projectName"
                +" from EMPLOYEE_PROJECT_EXPERIENCE PE"
                +" where 1=1 and PE.employeeNo = :employeeNo";
            return queryProperties.assembleSql(sql,"employeeNo",employeeProjectExperienceListQueryReq.getEmployeeNo());
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class EmployeeProjectExperienceListRspConvert implements Convert<EmployeeProjectExperienceList> {
        @Override
        public EmployeeProjectExperienceList apply(BusinessObject bo) {
            EmployeeProjectExperienceList temp = new EmployeeProjectExperienceList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setProjectName(bo.getString("ProjectName"));
            
            return temp;
        }
    }

    /**
     * 员工参与培训项目列表多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeProjectExperienceListQueryRsp employeeProjectExperienceListQuery(@Valid EmployeeProjectExperienceListQueryReq employeeProjectExperienceListQueryReq) {
        EmployeeProjectExperienceListQueryRsp employeeProjectExperienceListQueryRsp = new EmployeeProjectExperienceListQueryRsp();
        
        Query query = new EmployeeProjectExperienceListReqQuery().apply(employeeProjectExperienceListQueryReq);
        String fullsql = query.getSql();
        
        EmployeeProjectExperienceListRspConvert convert = new EmployeeProjectExperienceListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(employeeProjectExperienceListQueryReq.getBegin(), employeeProjectExperienceListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<EmployeeProjectExperienceList> employeeProjectExperienceLists = new ArrayList<EmployeeProjectExperienceList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                employeeProjectExperienceLists.add(convert.apply(bo));
            }
            employeeProjectExperienceListQueryRsp.setEmployeeProjectExperienceLists(employeeProjectExperienceLists);
        }
        employeeProjectExperienceListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return employeeProjectExperienceListQueryRsp;
    }

    /**
     * 员工参与培训项目列表删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void employeeProjectExperienceListDelete(@Valid EmployeeProjectExperienceListDeleteReq employeeProjectExperienceListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeProjectExperience employeeProjectExperience=bomanager.keyLoadBusinessObject(EmployeeProjectExperience.class, employeeProjectExperienceListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(employeeProjectExperience);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
