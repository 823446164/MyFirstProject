package com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo;

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
 * 追踪记录详情查询请求实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"serialNo", })
public class EmployeeTraceRecordInfoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("追踪编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TR.serialNo")
    private String serialNo;
}
