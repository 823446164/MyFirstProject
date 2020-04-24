/**
 * 用户角色管理
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userrole;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class Role implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("角色编号")
    @Length(max=40)
    private String roleId;
    @Description("角色名称")
    @Length(max=80)
    private String roleName;
    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("所属机构类型")
    @Length(max=1)
    private String belongOrgType;
    @Description("所属法人")
    @Length(max=80)
    private String belongRootOrg;
    @Description("所属机构级别")
    @Length(max=1)
    private String belongOrgLevel;
}