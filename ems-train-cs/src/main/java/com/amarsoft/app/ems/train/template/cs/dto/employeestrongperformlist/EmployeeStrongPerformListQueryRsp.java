package com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 员工强化培训表现查询响应实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
public class EmployeeStrongPerformListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("员工强化培训表现")
    private List<EmployeeStrongPerformList> employeeStrongPerformLists;
}
