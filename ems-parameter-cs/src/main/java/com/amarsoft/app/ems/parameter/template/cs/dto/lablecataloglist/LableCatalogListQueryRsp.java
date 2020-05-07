package com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 标签目录树图查询响应实体类
 * @author ylgao
 */
@Getter
@Setter
@ToString
public class LableCatalogListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("标签目录树图")
    private List<LableCatalogList> lableCatalogLists;
}
