package com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange;

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
 * 项目组人员变更信息查询请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class ProjectEmployeeChangeQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("变更编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("PMC.changeNo")
    private String changeNo;
}
