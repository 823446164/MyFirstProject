/*
 * 文件名：RankStandardItemsListServiceImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.stereotype.Service;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.RankStandardItemsListService;
import com.amarsoft.app.ems.parameter.entity.RankStandardItems;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogList;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabel;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogList;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsList;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListSaveReq;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.entity.RankStandardCatalog;;

/**
 * 〈技能详情Service实现类〉
 * 
 * @author xphe
 * @version 2020年5月13日
 * @see 
 * @since
 */

@Slf4j
@Service
public class RankStandardItemsListServiceImpl implements RankStandardItemsListService{


    /**
     * 
     * Description: 职级指标多记录查询
     *
     * @param rankStandardItemslistQueryReq
     * @return rankQueryRsp
     * @see
     */
    @Override
    @Transactional
    public RankStandardItemsListQueryRsp rankStandardItemsListQuery(@Valid RankStandardItemsListQueryReq rankStandardItemslistQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        List<RankStandardItems> rankStandardItems = bomanager.loadBusinessObjects(RankStandardItems.class,"belongCatalog=:belongCatalog ","belongCatalog",rankStandardItemslistQueryReq.getBelongCatalog());
        RankStandardItemsListQueryRsp rankQueryRsp=new RankStandardItemsListQueryRsp();
        List<RankStandardItemsList> ranStandardItemsLists=null;
        if (!CollectionUtils.isEmpty(rankStandardItems)) {
            ranStandardItemsLists= new ArrayList<RankStandardItemsList>();
            for(RankStandardItems ranItems : rankStandardItems) {
                RankStandardItemsList rankresponse=new RankStandardItemsList();
                rankresponse.setLabelName(ranItems.getLabelName());
                rankresponse.setLabelLevel(ranItems.getLabelLevel());
                ranStandardItemsLists.add(rankresponse);
            }
        }
        rankQueryRsp.setRankStandardItemsList(ranStandardItemsLists);
        rankQueryRsp.setTotalCount(rankQueryRsp.getRankStandardItemsList().size());
        return rankQueryRsp;
    }

    /**
     * 
     * Description: 标签选择按钮时，查询对应目录下的标签
     *
     * @param treeQueryReq
     * @return response
     * @see
     */
    @Override
    @Transactional
      public  TreeLabelQueryRsp treeLabelListQuery(@Valid TreeLabelQueryReq treeQueryReq) {
          BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
          List<LabelCatalog>  labelCatalogs=bomanager.loadBusinessObjects(LabelCatalog.class,"belongCatalog=:belongCatalog","belongCatalog",treeQueryReq.getSerialNo());
          TreeLabelQueryRsp response=new TreeLabelQueryRsp();
          List<TreeLabel> labelLists=null; 
          if (!CollectionUtils.isEmpty(labelCatalogs)) {
              labelLists = new ArrayList<TreeLabel>();
              for (LabelCatalog label : labelCatalogs) {
                  TreeLabel labelresponse = new TreeLabel();
                  labelresponse.setSerialNo(label.getSerialNo());
                  labelresponse.setLabelName(label.getLabelName());
                  labelresponse.setBelongCatalog(label.getBelongCataLog());
                  labelLists.add(labelresponse);
              }

          }
          response.setTreeLabel(labelLists);
          response.setTotalCount(response.getTreeLabel().size());
          return response;

      }

    /**
     * 
     * Description: 职级指标列表页面多记录更新保存
     *
     * @param rankStandardItemsInfoSaveReq
     * @return 
     * @see
     */
    @Override
    public void rankStandardItemsListSave(@Valid RankStandardItemsListSaveReq rankStandardItemsInfoSaveReq) {
        rankStandardItemsListSaveAction(rankStandardItemsInfoSaveReq.getRankStandardItemsLists());
    }
    
    /**
     * 
     * Description: 标签选择页面多记录新增保存
     *
     * @param treeLSaveReq
     * @return 
     * @see
     */
    @Override
    public void  rankLabelTreeSave(@Valid TreeLabelSaveReq treeLSaveReq) {
        rankLabelTreeSaveActiton(treeLSaveReq.getTreeLabel());
    }
    
    /**
     * 
     * Description: 职级指标列表页面多记录更新保存动作
     *
     * @param rankStandardItemsInfoSaveReq
     * @return 
     * @see
     */
    @Transactional
    public void rankStandardItemsListSaveAction(List<RankStandardItemsList> rankStandardItemslists){
        LocalDateTime inputDate = LocalDateTime.now();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(rankStandardItemslists!=null){
            List<RankStandardItems> rankSItemsList=new ArrayList<RankStandardItems>();
            for(RankStandardItemsList ranItemsListTmp:rankStandardItemslists) {
                RankStandardItems rankItems=bomanager.keyLoadBusinessObject(RankStandardItems.class, ranItemsListTmp.getSerialNo());
                rankItems.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                rankItems.setUpdateTime(inputDate);
                rankItems.setUpdateUserId(GlobalShareContextHolder.getUserId());
                rankItems.setLabelName(ranItemsListTmp.getLabelLevel());
                rankSItemsList.add(rankItems);
            }
            bomanager.updateBusinessObjects(rankSItemsList);
        }
        bomanager.updateDB();
    }
    
    /**
     * 
     * Description: 标签选择页面多记录新增保存动作
     *
     * @param treeLSaveReq
     * @return 
     * @see
     */
    @Transactional
    public void rankLabelTreeSaveActiton( List<TreeLabel> treeLabels) {
        LocalDateTime inputDate = LocalDateTime.now();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(treeLabels!=null){
            List<RankStandardItems> rankSItemsList=new ArrayList<RankStandardItems>();
            for(TreeLabel treeLabelTmp:treeLabels) {
                RankStandardItems rankItems=new RankStandardItems();
                    rankItems.generateKey();
                    rankItems.setInputTime(inputDate);
                    rankItems.setInputUserId(GlobalShareContextHolder.getUserId());
                    rankItems.setInputOrgId(GlobalShareContextHolder.getOrgId());
                rankItems.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                rankItems.setUpdateTime(inputDate);
                rankItems.setUpdateUserId(GlobalShareContextHolder.getUserId());
                rankItems.setRankNo(treeLabelTmp.getRankSerialNo());
                rankItems.setLabelNo(treeLabelTmp.getSerialNo());
                rankItems.setLabelName(treeLabelTmp.getLabelName());
                rankItems.setBelongCatalog(treeLabelTmp.getBelongCatalog());
                rankSItemsList.add(rankItems);
                //TODO xphe 代码优化，将重复部分写成一个函数
                List<LabelCatalog> labelCatalogs=bomanager.loadBusinessObjects(LabelCatalog.class, "belongCatalog=:belongCatalog","belongCatalog",treeLabelTmp.getSerialNo());
                if(labelCatalogs!=null) {
                    for(LabelCatalog labelCatalogTmp : labelCatalogs) {
                        RankStandardItems  rankItems1=new RankStandardItems();;

                            rankItems1=new RankStandardItems();
                            rankItems1.generateKey();
                            rankItems1.setInputTime(inputDate);
                            rankItems1.setInputUserId(GlobalShareContextHolder.getUserId());
                            rankItems1.setInputOrgId(GlobalShareContextHolder.getOrgId());
                        rankItems1.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                        rankItems1.setUpdateTime(inputDate);
                        rankItems1.setUpdateUserId(GlobalShareContextHolder.getUserId());
                        rankItems1.setRankNo(treeLabelTmp.getRankSerialNo());
                        rankItems1.setLabelNo(labelCatalogTmp.getSerialNo());
                        rankItems1.setLabelName(labelCatalogTmp.getLabelName());
                        rankItems1.setBelongCatalog(labelCatalogTmp.getBelongCataLog());
                        rankSItemsList.add(rankItems1);
                    }
                }
            }
            bomanager.updateBusinessObjects(rankSItemsList);
        }
        bomanager.updateDB();
    }
}
