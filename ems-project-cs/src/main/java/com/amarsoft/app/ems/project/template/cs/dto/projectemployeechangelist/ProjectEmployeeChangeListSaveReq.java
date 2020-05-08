package com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 项目组人员变更信息保存请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class ProjectEmployeeChangeListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("项目组人员变更信息")
    @NotEmpty
    private List<ProjectEmployeeChangeList> projectEmployeeChangeLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
