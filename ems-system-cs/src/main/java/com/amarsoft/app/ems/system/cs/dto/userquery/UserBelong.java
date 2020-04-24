/**
 * 查询用户
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.DataAuth;
import com.amarsoft.aecd.system.constant.MigrationStatus;

@Getter
@Setter
@ToString
public class UserBelong implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("机构名称")
    @Length(max=80)
    private String orgName;
    @Description("所属法人")
    @Length(max=40)
    private String belongRootOrg;
    @Description("所属机构级别")
    @Length(max=1)
    @Enum(OrgLevel.class)
    private String belongOrgLevel;
    @Description("数据权限")
    @Length(max=1)
    @Enum(DataAuth.class)
    private String dataAuth;
    @Description("是否默认")
    @Length(max=1)
    private String defaultFlag;
    @Description("机构变更状态")
    @Length(max=1)
    @Enum(MigrationStatus.class)
    private String migrationStatus;
}