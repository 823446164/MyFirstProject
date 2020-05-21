package com.amarsoft.app.ems.system.template.cs.dto.rolelistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 角色信息List保存请求实体类
 * @author cmhuang
 */
@Getter
@Setter
@ToString
public class RoleListDtoSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("角色信息List")
    @NotEmpty
    private List<RoleListDto> roleListDtos;

    @Description("总笔数")
    private Integer totalCount = 0;
}
