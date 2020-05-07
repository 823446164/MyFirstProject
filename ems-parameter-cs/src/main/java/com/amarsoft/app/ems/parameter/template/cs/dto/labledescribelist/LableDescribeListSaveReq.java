package com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 标签树图保存请求实体类
 * @author ylgao
 */
@Getter
@Setter
@ToString
public class LableDescribeListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("标签树图")
    @NotEmpty
    private List<LableDescribeList> lableDescribeLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}