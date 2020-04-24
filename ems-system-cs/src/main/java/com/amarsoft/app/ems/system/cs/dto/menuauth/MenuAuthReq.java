/**
 * 菜单权限管理
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.menuauth;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;

@Getter
@Setter
@ToString
public class MenuAuthReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("菜单编号")
    @NotEmpty
    @Length(max=40)
    private String menuId;
    @Description("菜单名称")
    @NotEmpty
    @Length(max=80)
    private String menuName;
    @Description("菜单权限")
    @NotEmpty
    @Length(max=400)
    private String menuAuth;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
}