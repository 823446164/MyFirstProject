/**
 * 查询团队信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.teamquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import java.util.List;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class TeamQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    
    
    @Description("团队编号")
    @Length(max=40)
    private String teamId;
    @Description("团队名称")
    @Length(max=40)
    private String teamName;
    @Description("团队负责人")
    @Length(max=80)
    private String teamLeader;
    @Description("所属法人")
    @Length(max=40)
    private String belongRootOrg;
    @Description("所属机构编号")
    @Length(max=40)
    private String belongOrgId;
    @Description("所属机构编号")
    private List<String> belongOrgLevel;
    @Description("搜索项")
    @Length(max=40)
    private String searchAttribute;
    @Description("搜索内容")
    @Length(max=80)
    private String searchContent;
    @Description("开始值")
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("笔数")
    @Digits(length=10,scale=0)
    private Integer pageSize;
    @Description("部门编号")
    @Length(max=40)
    private String orgId;
    @Description("员工编号")
    @Length(max=40)
    private String UserId;
    @Description("团队人数")
    @Length(max=40)
    private Integer Count;
}