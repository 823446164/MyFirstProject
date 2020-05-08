package com.amarsoft.app.ems.project.template.cs.dto.projectemployee;

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
 * 项目组人员信息查询请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class ProjectEmployeeQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("项目编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EP.serialNo")
    private String serialNo;
}
