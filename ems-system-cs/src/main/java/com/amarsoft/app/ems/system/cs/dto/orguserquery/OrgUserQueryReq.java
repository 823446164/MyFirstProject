/**
 * 查询机构用户
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.orguserquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class OrgUserQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("机构级别")
    @Length(max=1)
    private String orgLevel;
    @Description("搜索项")
    @Length(max=40)
    private String searchAttribute;
    @Description("搜素内容")
    @Length(max=80)
    private String searchContent;
    @Description("是否查询子机构")
    @Length(max=1)
    @Enum(YesNo.class)
    private String entireFlag = "1";
    @Description("开始值")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("笔数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer pageSize;
}