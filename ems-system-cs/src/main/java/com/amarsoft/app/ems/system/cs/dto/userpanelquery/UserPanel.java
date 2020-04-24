/**
 * 获取用户版块组件
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userpanelquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.YesNo;

@Getter
@Setter
@ToString
public class UserPanel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("编号")
    @NotEmpty
    @Length(max=40)
    private String id;
    @Description("中文名称")
    @NotEmpty
    @Length(max=100)
    private String chineseName;
    @Description("英文名")
    @Length(max=100)
    private String name;
    @Description("描述")
    @Length(max=400)
    private String panelDescription;
    @Description("是否启用")
    @Length(max=1)
    @Enum(YesNo.class)
    private String status;
}