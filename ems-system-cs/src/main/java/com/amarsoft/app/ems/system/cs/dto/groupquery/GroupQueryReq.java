/**
 * 查询角色组
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.groupquery;

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
public class GroupQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("角色组编号")
    @Length(max=40)
    private String groupId;
    @Description("所属法人")
    @Length(max=40)
    private String belongRootOrg;
    @Description("所属机构级别")
    private List<String> belongOrgLevel;
    @Description("开始值")
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("笔数")
    @Digits(length=10,scale=0)
    private Integer pageSize;
}