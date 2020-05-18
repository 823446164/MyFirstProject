/*
 * 文件名：EmployeeLabelServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工历史职级对应的标签查询
 * 修改人：xszhou
 * 修改时间：2020/5/9
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels.EmployeeAbilityLabelsReq;
import com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels.EmployeeAbilityLabelsRsp;
import com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels.LabelCatalogInfo;
import com.amarsoft.app.ems.employee.entity.EmployeeRank;
import com.amarsoft.app.ems.employee.entity.EmployeeRankRelabel;
import com.amarsoft.app.ems.employee.service.EmployeeLabelService;



@Service
public class EmployeeLabelServiceImpl implements EmployeeLabelService{
	
    /**
     * Description:员工对应历史职级标签查询<br>
     * @param EmployeeAbilityLabelsReq(employeeNo)
     * @return EmployeeAbilityLabelsRsp(List<LabelCatalog>)
     * @see
     */
	@Override
	public EmployeeAbilityLabelsRsp employeeLabelQuery(@Valid EmployeeAbilityLabelsReq req) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		EmployeeAbilityLabelsRsp rsp = new EmployeeAbilityLabelsRsp();
		List<LabelCatalogInfo> labelList = new ArrayList<LabelCatalogInfo>();
		LabelCatalogInfo label = new LabelCatalogInfo();
		List<EmployeeRank> rankList = bomanager.loadBusinessObjects(EmployeeRank.class,
				"employeeNo=:employeeNo and rankVersion='1' order by changeDate", "employeeNo",
				req.getEmployeeNo());
		if (!StringUtils.isEmpty(rankList)) {//如果list不为空则是有历史职级，执行下一步
			EmployeeRank employeeRank = rankList.get(0);//获取最新的历史职级
			if (!StringUtils.isEmpty(employeeRank)) {//若为新员工则没有历史职级和对应标签，直接返回空
				String rankNo = employeeRank.getSerialNo();
		 		if (!StringUtils.isEmpty(rankNo)) {//获取员工历史最新职级，若不为空则查找对应的标签
		 		    //获取标签掌握程度在了解或者一般以上的
					List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
							"select labelNo from EmployeeRankRelabel where rankNo=:rankNo and level>'1'",
							"rankNo", rankNo).getBusinessObjects();
					List<String> serialNoList = new ArrayList<String>();
					for (BusinessObject businessObject : businessObjects) {
                        String serialNo = businessObject.getString("labelNo");
                        serialNoList.add(serialNo);
                    }
					//TODO 调用参数服务的接口获取对应的指标名称						
					
				}
			}
		}
		rsp.setLabelList(labelList);
		return rsp;
	}
}
