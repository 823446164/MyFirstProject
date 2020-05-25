package com.amarsoft.app.ems.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.app.bpm.cs.dto.CreateProcessParams;
import com.amarsoft.app.bpm.cs.dto.createprocessinst.CreateProcessInstRsp;
import com.amarsoft.app.bpm.cs.dto.taskcommit.TaskCommitReq;
import com.amarsoft.app.bpm.cs.service.impl.BpmServiceImpl;

@Component
public class FlowUtil {

    private static BpmServiceImpl bpmServiceImpl;
    
    /**
     * 
     * Description: <br>
     * 1、为静态参数注入实例化对象<br>
     * Implement: <br>
     * 1、注入bpmcs中的BpmServiceClient到当前静态类变量中<br>
     *
     * @param BpmServiceClient 注入的对象
     * @see
     */
    @Autowired
    public void setBpmServiceClient(BpmServiceImpl bpmServiceImpl) {
        FlowUtil.bpmServiceImpl = bpmServiceImpl;
    }
    
    /**
     * 
     * Description: 流程初始化<br>
     * 必传参数:
     *  1、String processDefinitionKey   流程配置编号
     *  2、String objectNo               对象编号
     *  3、String objectType             对象类型
     *  3、String tenantId               业务系统号
     *  4、Integer startUser             业务发起人
     *  5、Integer startOrg              发起人所属机构
     *
     * @param CreateProcessInstReq
     * @return 
     * @see
     */
    public static CreateProcessInstRsp  initFlow (CreateProcessParams createProcParams) {
        try {
            CreateProcessInstRsp createProcessInst = bpmServiceImpl.createProcessInst(createProcParams);
            return createProcessInst;
        } catch (Exception e) {
            throw new ALSException("CM0133", e);
        }
    }
    
    
    /**
     * 
     * Description: 流程任务提交<br>
     * 必传参数:
     *  1、String partitionField         分区字段
     *  2、String processInstanceId      流程实例号
     *  3、String taskId                 流程任务号
     *  4、String tenantId               业务系统号
     *  5、String processDefinitionId    流程定义号
     *  6、String taskDefinitionKey      节点定义号
     *  7、String objectType             对象类型
     *  8、String objectNo               对象编号
     *
     * @param CreateProcessInstReq
     * @return 
     * @see
     */
    public static void Commit (TaskCommitReq commitReq) {
        try {
            bpmServiceImpl.commit(commitReq);
        } catch (Exception e) {
            throw new ALSException("CM0133", e);
        }
    }
}
