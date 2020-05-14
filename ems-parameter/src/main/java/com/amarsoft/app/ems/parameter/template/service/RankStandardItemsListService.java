/*
 * 文件名：RankStandardItemsListService.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：技能指标service接口
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListSaveReq;

/**
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
public interface RankStandardItemsListService {

    /**
     * 
     * Description: 职级指标列表查询
     *
     * @param rankStandardItemsInfoQueryReq
     * @return 
     * @see
     */
    public RankStandardItemsListQueryRsp rankStandardItemsListQuery(@Valid RankStandardItemsListQueryReq rankStandardItemsInfoQueryReq) ;

    /**
     * 
     * Description: 职级指标列表保存
     *
     * @param rankStandardItemsInfoSaveReq
     * @return 
     * @see
     */
    public void rankStandardItemsListSave(@Valid RankStandardItemsListSaveReq rankStandardItemsInfoSaveReq);
    
    /**
     * 
     * Description: 指标选择后展示当前节点指标列表
     *
     * @param treeQueryReq
     * @return 
     * @see
     */
    public  TreeLabelQueryRsp treeLabelListQuery(@Valid TreeLabelQueryReq treeQueryReq);
    
    /**
     * 
     * Description: 指标选择后指标列表保存
     *
     * @param treesavereq
     * @return 
     * @see
     */
    public void rankLabelTreeSave(@Valid TreeLabelSaveReq treesavereq);
}
