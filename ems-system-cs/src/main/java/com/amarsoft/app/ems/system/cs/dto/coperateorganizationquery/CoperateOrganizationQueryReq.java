/**
 * 法人机构查询
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.coperateorganizationquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.aecd.system.constant.CompanyType;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class CoperateOrganizationQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("机构类型")
    @Length(max=1)
    @Enum({OrgType.class,CompanyType.class})
    private String orgType;
    @Description("级别")
    @Length(max=1)
    @Enum(OrgLevel.class)
    private String orgLevel;
    @Description("开始值")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("笔数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer pageSize;
}