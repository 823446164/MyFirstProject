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
import com.amarsoft.amps.acsc.annotation.Digits;
import javax.validation.Valid;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class TeamQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("总数")
    @Digits(length=10,scale=0)
    private Integer totalCount;
    @Description("团队数组")
    @Valid
    @NotEmpty
    private List<TeamInfo> teamInfos;
}