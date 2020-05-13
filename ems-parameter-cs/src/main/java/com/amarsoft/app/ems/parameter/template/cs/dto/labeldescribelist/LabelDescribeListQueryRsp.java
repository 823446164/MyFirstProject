package com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 标签能力描述List查询响应实体类
 * @author yrong
 */
@Getter
@Setter
@ToString
public class LabelDescribeListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("标签能力描述List")
    private List<LabelDescribeList> labelDescribeLists;
}
