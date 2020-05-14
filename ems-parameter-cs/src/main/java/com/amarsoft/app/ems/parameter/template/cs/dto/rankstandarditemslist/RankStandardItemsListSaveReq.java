/*
 * 文件名：RankStandardItemsListSaveReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：职级指标list模板保存请求实体类
 * 修改人：xphe
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈技能指标保存请求实体〉
 * @author xphe
 * @version 2020年5月13日
 * @see RankStandardItemsListSaveReq
 * @since
 */
@Getter
@Setter
@ToString
public class RankStandardItemsListSaveReq   implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("职级技能List")
    @NotEmpty
    private List<RankStandardItemsList> rankStandardItemsLists;
    
    @Description("总笔数")
    private Integer totalCount = 0;
}
