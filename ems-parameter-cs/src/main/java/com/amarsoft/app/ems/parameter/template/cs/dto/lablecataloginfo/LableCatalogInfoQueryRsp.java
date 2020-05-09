package com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 标签目录详情查询响应实体类
 * @author ylgao
 */
@Getter
@Setter
@ToString
public class LableCatalogInfoQueryRsp extends LableCatalogInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("根据目录查询到的标签")
    private List<LableCatalogInfo> LableCatalogInfos;
}
