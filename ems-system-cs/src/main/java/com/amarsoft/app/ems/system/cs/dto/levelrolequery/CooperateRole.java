/**
 * 按法人查找角色
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.levelrolequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.rolequery.Role;
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
public class CooperateRole implements Serializable{
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
    @Description("总行角色总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer headTotalCount;
    @Description("分行角色总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer branchTotalCount;
    @Description("支行角色总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer subbranchTotalCount;
    @Description("总行角色数组")
    @Valid
    @NotEmpty
    private List<Role> headList;
    @Description("分行角色数组")
    @Valid
    @NotEmpty
    private List<Role> branchList;
    @Description("支行角色数组")
    @Valid
    @NotEmpty
    private List<Role> subbranchList;
}