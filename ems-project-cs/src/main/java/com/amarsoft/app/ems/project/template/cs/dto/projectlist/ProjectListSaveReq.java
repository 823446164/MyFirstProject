package com.amarsoft.app.ems.project.template.cs.dto.projectlist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 项目列表保存请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class ProjectListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("项目列表")
    @NotEmpty
    private List<ProjectList> projectLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
