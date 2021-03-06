package com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 项目组人员信息保存请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class ProjectEmployeeListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("项目组人员信息")
    @NotEmpty
    private List<ProjectEmployeeList> projectEmployeeLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
