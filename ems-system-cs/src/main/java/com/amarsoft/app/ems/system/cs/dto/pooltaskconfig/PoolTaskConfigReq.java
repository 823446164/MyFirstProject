/**
 * 根据objectType 获取任务池模式以及按钮方式
 * @Author dchen1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.pooltaskconfig;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class PoolTaskConfigReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("对象类型")
    @NotEmpty
    private List<String> objectType;
    @Description("业务系统号")
    @Length(max=40)
    @NotEmpty
    private String tenantId;
}