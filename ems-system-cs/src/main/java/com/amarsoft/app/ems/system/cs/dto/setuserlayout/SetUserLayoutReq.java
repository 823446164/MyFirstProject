/**
 * 设置用户布局
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.setuserlayout;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayout;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class SetUserLayoutReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
    @Description("机构编号")
    @NotEmpty
    @Length(max=40)
    private String orgId;
    @Description("布局数组")
    @Valid
    private List<UserLayout> layouts;
}