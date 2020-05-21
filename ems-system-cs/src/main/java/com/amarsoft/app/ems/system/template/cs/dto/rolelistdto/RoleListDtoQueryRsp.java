package com.amarsoft.app.ems.system.template.cs.dto.rolelistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 角色信息List查询响应实体类
 * @author cmhuang
 */
@Getter
@Setter
@ToString
public class RoleListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("角色信息List")
    private List<RoleListDto> roleListDtos;
}
