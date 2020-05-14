/*
 * 文件名：TreeLabelSaveReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈标签列表保存请求实体类〉
 * @author xphe
 * @version 2020年5月13日
 * @see TreeLabelSaveReq
 * @since
 */
@Getter
@Setter
@ToString
public class TreeLabelSaveReq extends TreeLabel implements Serializable{
    private static final long serialVersionUID = 1L;
    @Description("标签List")
    @NotEmpty
    private List<TreeLabel> treeLabel;

    @Description("总笔数")
    private Integer totalCount = 0;
}
