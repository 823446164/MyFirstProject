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
import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@ToString
public class TaskNumQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("类型和总数")
    @Valid
    private List<TaskNum> taskNums;
}