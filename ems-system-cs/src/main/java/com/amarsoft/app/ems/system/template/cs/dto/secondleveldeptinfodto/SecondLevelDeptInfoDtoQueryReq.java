package com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

/**
 * 二级部门信息Info查询请求实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class SecondLevelDeptInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("机构编号")
    @NotEmpty
    @Length(max=40)
    private String orgId;
}
