/**
 * 查询用户
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class UserQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @Length(max=40)
    private String userId;
    @Description("登录编号")
    @Length(max=40)
    private String logonId;
    @Description("机构编号")
    @Length(max=40)
    private String orgId;
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
}