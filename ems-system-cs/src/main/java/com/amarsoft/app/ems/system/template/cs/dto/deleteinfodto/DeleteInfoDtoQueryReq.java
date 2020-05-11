package com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;

/**
 * 删除部门Info查询请求实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class DeleteInfoDtoQueryReq extends DeleteInfoDto   implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
