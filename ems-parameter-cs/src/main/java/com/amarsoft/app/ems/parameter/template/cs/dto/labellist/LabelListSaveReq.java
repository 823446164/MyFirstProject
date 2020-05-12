/*
 * 文件名：LabelListSaveReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签列表保存的请求体
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.cs.dto.labellist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 标签List保存请求实体类
 * @author yrong
 */
@Getter
@Setter
@ToString
public class LabelListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("标签List")
    @NotEmpty
    private List<LabelList> labelLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
