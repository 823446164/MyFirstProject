/**
 * 工作台模板获取
 * @Author dchen1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.tasktemplate;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@ToString
public class TaskTemplateRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("代办任务模板编号")
    @Length(max=40)
    private String id;
    @Description("代办任务模板名称")
    @Length(max=80)
    private String name;
    @Description("模板卡片配置")
    @Valid
    private List<TaskTab> taskTabs;
}