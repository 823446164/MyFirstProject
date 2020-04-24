/**
 * 获取用户布局
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userlayoutquery;

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
public class UserLayout implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @Length(max=40)
    private String userId;
    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("横坐标")
    @Digits(length=10,scale=0)
    private Integer x;
    @Description("纵坐标")
    @Digits(length=10,scale=0)
    private Integer y;
    @Description("宽度")
    @Digits(length=10,scale=0)
    private Integer w;
    @Description("高度")
    @Digits(length=10,scale=0)
    private Integer h;
    @Description("索引")
    @Digits(length=2,scale=0)
    private Integer i;
    @Description("引用组建")
    @Length(max=400)
    private String component;
    @Description("参数")
    @Length(max=400)
    private String params;
}