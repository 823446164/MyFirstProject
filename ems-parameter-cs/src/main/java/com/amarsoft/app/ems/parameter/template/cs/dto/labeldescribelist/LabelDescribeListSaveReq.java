package com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 标签能力描述List保存请求实体类
 * @author yrong
 */
@Getter
@Setter
@ToString
public class LabelDescribeListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("标签能力描述List")
    @NotEmpty
    private List<LabelDescribeList> labelDescribeLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
