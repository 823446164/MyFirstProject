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
import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@ToString
public class RoleAuthReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("角色编号")
    @NotEmpty
    @Length(max=40)
    private String roleId;
    @Description("授权数组")
    @Valid
    @NotEmpty
    private List<RoleAuth> roleAuths;
}