/**
 * 修改菜单
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.updatemenu;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;
import java.util.List;

@Getter
@Setter
@ToString
public class UpdateMenuReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("菜单编号")
    @NotEmpty
    @Length(max=40)
    private String menuId;
    @Description("排序编号")
    @Length(max=40)
    private String sortNo;
    @Description("菜单名称")
    @NotEmpty
    @Length(max=80)
    private String menuName;
    @Description("菜单繁体名称")
    @NotEmpty
    @Length(max=80)
    private String menuTwName;
    @Description("菜单英文名称")
    @NotEmpty
    @Length(max=80)
    private String menuEnName;
    @Description("菜单图标")
    @Length(max=50)
    private String icon;
    @Description("菜单路径")
    @Length(max=80)
    private String url;
    @Description("菜单路径参数")
    @Length(max=400)
    private String urlParam;
    @Description("上级菜单编号")
    @Length(max=40)
    private String parentId;
    @Description("菜单权限（正则表达式逗号分隔）")
    @Length(max=400)
    private String menuAuth;
    @Description("状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
    @Description("角色数组")
    private List<String> roleId;
}