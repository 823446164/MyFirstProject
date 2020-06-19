package com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 团队调整申请List保存请求实体类
 * @author jfan5
 */
@Getter
@Setter
@ToString
public class EmployeeBelongChangeListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("团队调整申请List")
    @NotEmpty
    private List<EmployeeBelongChangeList> employeeBelongChangeLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
