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
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class TaskTab implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("代办卡片标题")
    @Length(max=80)
    private String title;
    @Description("代办卡片图标")
    @Length(max=80)
    private String icon;
    @Description("背景颜色")
    @Length(max=80)
    private String bgColor;
    @Description("排序号")
    @Digits(length=80,scale=0)
    private Integer sortNo = 0;
    @Description("数值显示后缀")
    @Length(max=80)
    private String suffix;
    @Description("对象类型")
    @Length(max=80)
    private String objectType;
    @Description("代办任务对应的下方显示卡片模板编号")
    @Length(max=80)
    private String templateId;
    @Description("代办总数")
    @Digits(length=80,scale=0)
    private Integer tabNum = 0;
    @Description("服务名")
    @Length(max=80)
    private String serviceName;
    @Description("处理链接")
    @Length(max=200)
    private String dealUrl;
    @Description("处理链接需要的参数名")
    @Length(max=200)
    private String dealParam;
}