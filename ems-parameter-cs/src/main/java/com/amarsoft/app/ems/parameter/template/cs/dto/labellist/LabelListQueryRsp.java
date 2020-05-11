package com.amarsoft.app.ems.parameter.template.cs.dto.labellist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 标签List查询响应实体类
 * @author ylgao
 */
@Getter
@Setter
@ToString
public class LabelListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("标签List")
    private List<LabelList> labelLists;
}
