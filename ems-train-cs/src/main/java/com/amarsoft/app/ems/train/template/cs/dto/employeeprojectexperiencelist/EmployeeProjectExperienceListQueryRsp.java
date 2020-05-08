package com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 员工参与培训项目列表查询响应实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
public class EmployeeProjectExperienceListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("员工参与培训项目列表")
    private List<EmployeeProjectExperienceList> employeeProjectExperienceLists;
}
