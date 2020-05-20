package com.amarsoft.app.ems.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.app.bpm.cs.client.NewProcessInstClient;
import com.amarsoft.app.bpm.cs.dto.createprocessinst.CreateProcessInstReq;

@Component
public class FlowUtil {

	private static NewProcessInstClient newProcessInstClient;
	
	
	/**
	 * 
	 * Description: <br>
	 * 1、为静态参数注入实例化对象<br>
	 * Implement: <br>
	 * 1、注入bpmcs中的NewProcessInstClient到当前静态类变量中<br>
	 *
	 * @param NewProcessInstClient 注入的对象
	 * @see
	 */
	@Autowired
	public void setRuntimeTaskClient(NewProcessInstClient newProcessInstClient) {
		FlowUtil.newProcessInstClient = newProcessInstClient;
	}
	
	/**
	 * 
	 * Description: 流程初始化<br>
	 * 必传参数:
	 * 	1、String processDefinitionKey  	流程配置编号
	 * 	2、String objectNo        		对象编号
	 *  3、String objectType				对象类型
	 *  3、String tenantId     			业务系统号
	 *  4、Integer startUser       		业务发起人
	 *  5、Integer startOrg  			发起人所属机构
	 *
	 * @param CreateProcessInstReq
	 * @return 
	 * @see
	 */
	public static void initFlow(CreateProcessInstReq createProcessInstReq) {
		try {
			RequestMessage<CreateProcessInstReq> paramRequestMessage = new RequestMessage<CreateProcessInstReq>();
			paramRequestMessage.setMessage(createProcessInstReq);
			newProcessInstClient.createProcessInst(paramRequestMessage);
		} catch (Exception e) {
			throw new ALSException("CM0133", e);
		}
	}
}
