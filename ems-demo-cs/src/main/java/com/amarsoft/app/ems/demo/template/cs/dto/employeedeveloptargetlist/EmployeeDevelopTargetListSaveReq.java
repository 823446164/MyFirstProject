package com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 员工成长目标跟踪List保存请求实体类
 * @author jfan5
 */
@Getter
@Setter
@ToString
public class EmployeeDevelopTargetListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工成长目标跟踪List")
    @NotEmpty
    private List<EmployeeDevelopTargetList> employeeDevelopTargetLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
