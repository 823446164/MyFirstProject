package com.amarsoft.app.ems.system.template.cs.dto.task;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.annotation.TaskBody;
import com.amarsoft.app.ems.system.annotation.TaskHeader;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author dchen1
 * @Date 2019/9/29 下午4:30
 * @Version
 */
@Getter
@Setter
@TaskHeader(id = "CfsTask", name = "网贷代办工作模板")
public class CfsTask {
    @Description("待处理申请")
    @TaskBody(icon = "icon-shenqing", suffix = "件", templateId = "ApplyFlowToDoList", bgColor = "#1890ff",serviceName = "abams",dealUrl="/detailwork/creditwork/credit/applywork/apply",dealParam = "objectNo,objectType=BusinessApply,customerId,productId,processInstanceId,taskId,processDefinitionId,taskDefinitionKey")
    private String businessApply;

    @Description("待登记合同")
    @TaskBody(icon = "icon-hetong", suffix = "件", templateId = "ContractRegistToDoList", bgColor = "#52c41a",serviceName = "abcms",dealUrl = "/detailwork/contractwork/contract/regist",dealParam = "objectNo,customerId,productId,processInstanceId,taskId,taskDefinitionKey,processDefinitionId,objectType=BusinessContract,applySerialNo,taskType=01")
    private String businessContract;

    @Description("待处理放款")
    @TaskBody(icon = "icon-fangkuan", suffix = "件", templateId = "MakeLoansToDoList", bgColor = "#fa8c16",serviceName = "abpms",dealUrl = "/detailwork/putoutwork/putout/makeloansdetail",dealParam = "serialNo,customerId,objectType=BusinessPutOut,objectNo,processInstanceId,taskId,taskDefinitionKey,processDefinitionId,flowIndex,productId,contractNo")
    private String businessPutOut;

    @Description("待处理合作项目")
    @TaskBody(icon = "icon-fengxian", suffix = "件", templateId = "ProjectFlowToDoList", bgColor = "#eb2f96",serviceName = "acoms",dealUrl = "/detailwork/partnerwork/partner/projectdetail/detail",dealParam = "objectNo,customerId,partnerNo,processInstanceId,taskId,taskDefinitionKey,processDefinitionId,objectType=ProjectDetailEntity")
    private String projectDetailEntity;
}
