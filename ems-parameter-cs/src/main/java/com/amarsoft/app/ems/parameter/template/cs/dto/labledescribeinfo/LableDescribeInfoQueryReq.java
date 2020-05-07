package com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;

/**
 * 标签树图查询请求实体类
 * @author ylgao
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class LableDescribeInfoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("流水号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("LD.serialNo")
    private String serialNo;
}
