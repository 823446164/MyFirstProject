package com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 标签Info保存请求实体类
 * @author ylgao
 */
@Getter
@Setter
@ToString
public class LabelInfoSaveReq extends LabelInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
