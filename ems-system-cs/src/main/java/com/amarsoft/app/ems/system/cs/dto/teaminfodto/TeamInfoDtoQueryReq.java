package com.amarsoft.app.ems.system.cs.dto.teaminfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;

/**
 * 团队信息查询请求实体类
 * @author hpli
 */



@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class TeamInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("团队编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TINFO.teamId")
    private String teamId;
    /**
     * Description:执行状态 <br>
     * @return
     * @see
     */
    @Description("执行状态")
    public boolean getStringflag ;
    
 
    
}
