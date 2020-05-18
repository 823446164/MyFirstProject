/**
 * 查询机构用户
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.orguserquery;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserTeamOrgInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @Length(max=40)
    private String userId;
    @Description("部门编号")
    @Length(max=80)
    private String orgId;
    @Description("部门名称")
    @Length(max=40)
    private String orgName;
    @Description("团队编号")
    @Length(max=80)
    private String teamId;
    @Description("团队名称")
    @Length(max=40)
    private String teamName;

}