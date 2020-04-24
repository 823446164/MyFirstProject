/**
 * 按机构和角色查询用户
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userquerybyorg;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.UserStatus;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class UserQueryByOrgReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("机构编号")
    @NotEmpty
    @Length(max=40)
    private String orgId;
    @Description("角色编号")
    @Length(max=40)
    private String roleId;
    @Description("状态")
    @Length(max=1)
    @Enum(UserStatus.class)
    private String status;
    @Description("开始笔数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("总笔数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer pageSize;
    @Description("搜索项")
    @Length(max=40)
    private String searchAttribute;
    @Description("搜索内容")
    @Length(max=80)
    private String searchContent;
}