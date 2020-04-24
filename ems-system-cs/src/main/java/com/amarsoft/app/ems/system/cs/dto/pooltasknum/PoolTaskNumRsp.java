/**
 * 根据objectType 获取任务池总数
 * @Author dchen1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.pooltasknum;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;

@Getter
@Setter
@ToString
public class PoolTaskNumRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("总笔数")
    private Integer totalCount = 0;
}