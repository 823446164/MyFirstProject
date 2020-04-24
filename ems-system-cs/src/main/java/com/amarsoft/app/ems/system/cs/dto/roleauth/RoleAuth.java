/**
 * 角色权限管理
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.roleauth;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.AuthType;
import com.amarsoft.aecd.system.constant.SystemStatus;

@Getter
@Setter
@ToString
public class RoleAuth implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("授权类型")
    @NotEmpty
    @Length(max=1)
    @Enum(AuthType.class)
    private String authType;
    @Description("后端服务API地址URL或菜单编号")
    @NotEmpty
    @Length(max=80)
    private String authNo;
    @Description("后端服务名称或菜单名称")
    @NotEmpty
    @Length(max=80)
    private String authName;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
}