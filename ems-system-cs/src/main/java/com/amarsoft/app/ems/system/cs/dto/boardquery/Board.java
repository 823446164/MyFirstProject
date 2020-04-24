/**
 * 查询通告
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.boardquery;

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
import com.amarsoft.amps.acsc.annotation.DatePattern;
import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@ToString
public class Board implements Serializable{
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
    @NotEmpty
    @Length(max=80)
    private String boardTitle;
    @Description("通知描述")
    @NotEmpty
    @Length(max=4000)
    private String boardDesc;
    @Description("是否发布")
    @NotEmpty
    @Length(max=1)
    @Enum(YesNo.class)
    private String lauchFlag;
    @Description("是否最新")
    @NotEmpty
    @Length(max=1)
    @Enum(YesNo.class)
    private String latestFlag;
    @Description("是否弹出")
    @NotEmpty
    @Length(max=1)
    @Enum(YesNo.class)
    private String popupFlag;
    @Description("是否所有人查看")
    @Length(max=1)
    @Enum(YesNo.class)
    private String allowFlag;
    @Description("结束日期")
    @NotEmpty
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String endDate;
    @Description("作者")
    @NotEmpty
    @Length(max=40)
    private String author;
    @Description("发布时间")
    @NotEmpty
    @Length(max=20)
    private String lauchTime;
    @Description("附件路径数组")
    @Valid
    private List<UploadFile> filePath;
    @Description("角色数组")
    @Valid
    private List<Role> roles;
    @Description("角色组数组")
    @Valid
    private List<Group> groups;
}