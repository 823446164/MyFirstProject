package com.amarsoft.app.ems.project.template.cs.dto.projectemployee;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 项目组人员信息保存请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class ProjectEmployeeSaveReq extends ProjectEmployee implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
