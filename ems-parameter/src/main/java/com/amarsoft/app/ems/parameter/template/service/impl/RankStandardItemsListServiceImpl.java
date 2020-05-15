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


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.entity.RankStandardItems;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabel;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsList;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListSaveReq;
import com.amarsoft.app.ems.parameter.template.service.RankStandardItemsListService;

import lombok.extern.slf4j.Slf4j;


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
public class RankStandardItemsListServiceImpl implements RankStandardItemsListService {

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
        // 1.点击左侧树图，根据所属目录获取职级指标List
        List<RankStandardItems> rankStandardItems = bomanager.loadBusinessObjects(RankStandardItems.class, "belongCatalog=:belongCatalog ",
            "belongCatalog", rankStandardItemslistQueryReq.getBelongCatalog());
        RankStandardItemsListQueryRsp rankQueryRsp = new RankStandardItemsListQueryRsp();
        List<RankStandardItemsList> ranStandardItemsLists = null;
        if (!CollectionUtils.isEmpty(rankStandardItems)) {
            ranStandardItemsLists = new ArrayList<RankStandardItemsList>();
            // 2.遍历输出List
            for (RankStandardItems ranItems : rankStandardItems) {
                RankStandardItemsList rankresponse = new RankStandardItemsList();
                rankresponse.setSerialNo(ranItems.getSerialNo());
                rankresponse.setBelongCatalog(ranItems.getBelongCatalog());
                rankresponse.setRankNo(ranItems.getRankNo());
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
    public TreeLabelQueryRsp treeLabelListQuery(@Valid TreeLabelQueryReq treeQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 1.点击子节点获取当前节点下的标签
        List<LabelCatalog> labelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class, "belongCatalog=:belongCatalog",
            "belongCatalog", treeQueryReq.getSerialNo());
        TreeLabelQueryRsp response = new TreeLabelQueryRsp();
        List<TreeLabel> labelLists = null;
        if (!CollectionUtils.isEmpty(labelCatalogs)) {
            labelLists = new ArrayList<TreeLabel>();
            // 2.循环输出List
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
    public void rankLabelTreeSave(@Valid TreeLabelSaveReq treeLSaveReq) {
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
    public void rankStandardItemsListSaveAction(List<RankStandardItemsList> rankStandardItemslists) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (rankStandardItemslists != null) {
            List<RankStandardItems> rankSItemsList = new ArrayList<RankStandardItems>();
            // 循环List更改每个记录
            for (RankStandardItemsList ranItemsListTmp : rankStandardItemslists) {
                RankStandardItems rankItems = bomanager.keyLoadBusinessObject(RankStandardItems.class, ranItemsListTmp.getSerialNo());
                rankItems.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
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
     * 标签编号　标签名字　 标签目录的List
     * @return 
     * @see
     */
    @Transactional
    public void rankLabelTreeSaveActiton(List<TreeLabel> treeLabels) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RankStandardItems> rankSItemsList = new ArrayList<RankStandardItems>();
        if (treeLabels != null) {
            RankStandardItems rankItems = null;
            // 1.循环树图节点下的标签List
            for (TreeLabel treeLabelTmp : treeLabels) {
                // 2.通过List里面的每个元素的serialNo得到本身元素及其下属元素
                List<BusinessObject> boas = bomanager.selectBusinessObjectsBySql(
                    "select serialNo,labelName,belongCataLog from LabelCatalog where belongCataLog=:serialNo or serialNo=:serialNo ",
                    "serialNo", treeLabelTmp.getSerialNo()).getBusinessObjects();
                if (!CollectionUtils.isEmpty(boas)) {
                    // 3.将上一步获取的List循环输出至职级指标表
                    for (BusinessObject boasTmp : boas) {
                        rankItems = new RankStandardItems();
                        rankItems.generateKey();
                        rankItems.setInputOrgId(GlobalShareContextHolder.getOrgId());
                        rankItems.setRankNo(treeLabelTmp.getRankSerialNo());
                        rankItems.setLabelNo(boasTmp.getString("serialNo"));
                        rankItems.setLabelName(boasTmp.getString("labelName"));
                        rankItems.setBelongCatalog(boasTmp.getString("belongCataLog"));
                        rankSItemsList.add(rankItems);
                    }
                }
            }
            bomanager.updateBusinessObjects(rankSItemsList);
        }
        bomanager.updateDB();
    }

}
