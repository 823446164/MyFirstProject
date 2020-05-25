package com.amarsoft.app.ems.system.template.cs.dto.roleinfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

/**
 * 角色信息Info查询请求实体类
 * @author cmhuang
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class RoleInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("角色编号")
    private String roleId;
    
    @Description("角色状态")
    private String status;
    
}
