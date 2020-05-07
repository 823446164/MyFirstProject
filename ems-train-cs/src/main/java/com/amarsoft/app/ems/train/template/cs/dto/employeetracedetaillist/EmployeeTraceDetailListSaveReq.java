package com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 追踪内容列表保存请求实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
public class EmployeeTraceDetailListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("追踪内容列表")
    @NotEmpty
    private List<EmployeeTraceDetailList> employeeTraceDetailLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
