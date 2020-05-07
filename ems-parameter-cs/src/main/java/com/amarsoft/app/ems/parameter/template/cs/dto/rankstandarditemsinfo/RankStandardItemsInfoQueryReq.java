package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo;

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
 * 技能详情查询请求实体类
 * @author ylgao
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class RankStandardItemsInfoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("指标编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("RSI.serialNo")
    private String serialNo;

    @Description("职级编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("RSI.rankNo")
    private String rankNo;
}
