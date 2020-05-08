package com.amarsoft.app.ems.project.template.cs.dto.projectinfo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 项目列表保存请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class ProjectInfoSaveReq extends ProjectInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
