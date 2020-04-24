/**
 * 工作台模板获取
 * @Author dchen1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.tasktemplate;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class TaskTemplateReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("代办任务模板编号")
    @Length(max=40)
    @NotEmpty
    private String templateId;
}