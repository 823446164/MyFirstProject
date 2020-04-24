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
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.aecd.system.constant.CompanyType;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Digits;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class CoperateOrg implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("机构名称")
    @Length(max=80)
    private String orgName;
    @Description("机构类型")
    @Length(max=1)
    @Enum({OrgType.class,CompanyType.class})
    private String orgType;
    @Description("部室/总公司总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer firstTotalCount;
    @Description("一级分行/分公司总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer secondTotalCount;
    @Description("部室")
    @Valid
    @NotEmpty
    private List<OrgInfo> departments;
    @Description("一级分行")
    @Valid
    @NotEmpty
    private List<OrgInfo> branchOrgs;
    @Description("总公司")
    @Valid
    @NotEmpty
    private List<OrgInfo> headComponies;
    @Description("分公司")
    @Valid
    @NotEmpty
    private List<OrgInfo> componies;
}