/**
 * 按法人查询团队
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.levelteamquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.OrgLevel;
import java.util.List;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class LevelTeamQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("所属机构级别")
    @Enum(OrgLevel.class)
    private List<String> belongOrgLevel;
    @Description("开始值")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("机构类型")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer pageSize;
}