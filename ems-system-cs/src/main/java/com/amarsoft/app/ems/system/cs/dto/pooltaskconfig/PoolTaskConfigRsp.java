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
import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@ToString
public class PoolTaskConfigRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("分组数据")
    @Valid
    private List<PoolTaskGroup> groups;
}