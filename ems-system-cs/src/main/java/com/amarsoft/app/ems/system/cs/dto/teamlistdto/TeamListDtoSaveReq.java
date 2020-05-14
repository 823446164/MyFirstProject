package com.amarsoft.app.ems.system.cs.dto.teamlistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 团队信息保存请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class TeamListDtoSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("团队信息")
    @NotEmpty
    private List<TeamListDto> teamListDtos;

    @Description("总笔数")
    private Integer totalCount = 0;
   

    
    
    
}
