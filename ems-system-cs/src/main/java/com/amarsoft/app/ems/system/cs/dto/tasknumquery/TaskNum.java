/**
 * 获取代办总数 根据obejctType分类 数量降序
 * @Author dchen1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.tasknumquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class TaskNum implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("对象类型")
    @Length(max=80)
    private String objectType;
    @Description("总数")
    @Digits(length=80,scale=0)
    private Integer totalCount = 0;
}