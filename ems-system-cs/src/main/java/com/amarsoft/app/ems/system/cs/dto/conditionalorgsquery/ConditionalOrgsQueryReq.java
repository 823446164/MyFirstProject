/**
 * 按条件查询机构
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.aecd.system.constant.CompanyType;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class ConditionalOrgsQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("父机构编号")
    @Length(max=40)
    private String parentOrgId;
    @Description("机构级别")
    @Length(max=1)
    @Enum(OrgLevel.class)
    private String orgLevel;
    @Description("机构类型")
    @Length(max=1)
    @Enum({OrgType.class,CompanyType.class})
    private String orgType;
    @Description("是否只查询当前机构及其子机构")
    @Length(max=1)
    @Enum(YesNo.class)
    private String entireFlag = "0";
    @Description("开始值")
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("笔数")
    @Digits(length=10,scale=0)
    private Integer pageSize;
}