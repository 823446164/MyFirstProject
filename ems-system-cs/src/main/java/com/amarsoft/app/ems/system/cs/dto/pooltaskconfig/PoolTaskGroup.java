/**
 * 根据objectType 获取任务池模式以及按钮方式
 * @Author dchen1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.pooltaskconfig;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class PoolTaskGroup implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("流程定义号")
    @Length(max=100)
    private String processDefinitionId;
    @Description("流程定义名称")
    @Length(max=200)
    private String processDefinitionName;
    @Description("节点定义号")
    @Length(max=50)
    private String taskDefinitionKey;
    @Description("节点定义名称")
    @Length(max=200)
    private String taskDefinitionName;
    @Description("任务数")
    private Integer taskCount = 0;
    @Description("任务池类型(自动,手动)")
    @Length(max=100)
    private String poolType;
}