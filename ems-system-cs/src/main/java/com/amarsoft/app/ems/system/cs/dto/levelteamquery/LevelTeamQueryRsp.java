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
import javax.validation.Valid;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class LevelTeamQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("团队数组")
    @Valid
    @NotEmpty
    private List<CooperateTeam> rspList;
}