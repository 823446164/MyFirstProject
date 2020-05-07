package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 职级标准列表保存请求实体类
 * @author ylgao
 */
@Getter
@Setter
@ToString
public class RankStandardCatalogListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("职级标准列表")
    @NotEmpty
    private List<RankStandardCatalogList> rankStandardCatalogLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
