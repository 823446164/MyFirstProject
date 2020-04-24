/**
 * 用户登录信息一览
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.alllogininfoquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class AllLoginInfoQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("搜索项")
    @Length(max=40)
    private String searchAttribute;
    @Description("搜索内容")
    @Length(max=80)
    private String searchContent;
    @Description("搜索开始时间")
    @Length(max=19)
    private String searchBeginTime;
    @Description("搜索结束时间")
    @Length(max=19)
    private String searchEndTime;
    @Description("开始值")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("笔数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer pageSize;
}