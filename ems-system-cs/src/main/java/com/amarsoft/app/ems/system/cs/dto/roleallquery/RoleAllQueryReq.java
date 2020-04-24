/**
 * 查询所有角色
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.roleallquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;

@Getter
@Setter
@ToString
public class RoleAllQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("所属法人")
    @Length(max=40)
    private String belongRootOrg;
    @Description("所属机构级别")
    @Length(max=1)
    private String belongOrgLevel;
    @Description("状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
}