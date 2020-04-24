/**
 * 按法人查找角色组
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.levelgroupquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.groupquery.Group;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.aecd.system.constant.CompanyType;
import com.amarsoft.amps.acsc.annotation.Digits;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class CooperateGroup implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("机构编号")
    @NotEmpty
    @Length(max=40)
    private String orgId;
    @Description("机构名称")
    @NotEmpty
    @Length(max=80)
    private String orgName;
    @Description("机构类型")
    @NotEmpty
    @Length(max=1)
    @Enum({OrgType.class,CompanyType.class})
    private String orgType;
    @Description("总行角色组总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer headTotalCount;
    @Description("分行角色组总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer branchTotalCount;
    @Description("支行角色组总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer subbranchTotalCount;
    @Description("总行角色组数组")
    @Valid
    @NotEmpty
    private List<Group> headList;
    @Description("分行角色组数组")
    @Valid
    @NotEmpty
    private List<Group> branchList;
    @Description("支行角色组数组")
    @Valid
    @NotEmpty
    private List<Group> subbranchList;
}