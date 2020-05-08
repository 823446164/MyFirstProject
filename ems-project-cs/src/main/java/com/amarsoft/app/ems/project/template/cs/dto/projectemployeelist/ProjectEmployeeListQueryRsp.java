package com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 项目组人员信息查询响应实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class ProjectEmployeeListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("项目组人员信息")
    private List<ProjectEmployeeList> projectEmployeeLists;
}
