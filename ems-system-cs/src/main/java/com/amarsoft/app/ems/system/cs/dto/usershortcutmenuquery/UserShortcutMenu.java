/**
 * 获取快捷菜单
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class UserShortcutMenu implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @Length(max=40)
    private String userId;
    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("菜单编号")
    @Length(max=40)
    private String menuId;
    @Description("菜单名称")
    @Length(max=80)
    private String name;
    @Description("菜单路由")
    @Length(max=80)
    private String url;
    @Description("菜单参数")
    @Length(max=400)
    private String params;
}