/**
 * 更新通告
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.updateboard;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.usergroup.Group;
import com.amarsoft.app.ems.system.cs.dto.userrole.Role;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.SystemStatus;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class UpdateBoardReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("通知编号")
    @NotEmpty
    @Length(max=40)
    private String boardId;
    @Description("通知名称")
    @NotEmpty
    @Length(max=80)
    private String boardName;
    @Description("通知标题")
    @Length(max=80)
    private String boardTitle;
    @Description("通知描述")
    @Length(max=4000)
    private String boardDesc;
    @Description("是否发布")
    @Length(max=1)
    @Enum(YesNo.class)
    private String lauchFlag;
    @Description("是否最新")
    @Length(max=1)
    @Enum(YesNo.class)
    private String latestFlag;
    @Description("是否弹出")
    @Length(max=1)
    @Enum(YesNo.class)
    private String popupFlag;
    @Description("是否所有人查看")
    @Length(max=1)
    @Enum(YesNo.class)
    private String allowFlag;
    @Description("结束日期")
    @Length(max=10)
    private String endDate;
    @Description("状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
    @Description("角色数组")
    @Valid
    private List<Role> roles;
    @Description("角色组数组")
    @Valid
    private List<Group> groups;
}