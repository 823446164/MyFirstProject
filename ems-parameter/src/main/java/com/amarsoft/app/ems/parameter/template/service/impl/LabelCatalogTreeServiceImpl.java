/*
 * 文件名：LabelCatalogTreeServiceImpl.java 版权：Copyright by www.amarsoft.com 描述： 修改人：yrong
 * 修改时间：2020年5月12日 跟踪单号： 修改单号： 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.service.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amarsoft.aecd.parameter.constant.LabelType;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcatalogtreequery.LabelCatalogTreeQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcatalogtreequery.Tree;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogTreeService;

import lombok.extern.slf4j.Slf4j;


/**
 * 标签目录树图 生成标签管理左侧树图
 * 
 * @author yrong
 * @version 2020年5月12日
 * @see LabelCatalogTreeServiceImpl
 * @since
 */
@Slf4j
@Service
public class LabelCatalogTreeServiceImpl implements LabelCatalogTreeService {

    /**
     * 查询出所有目录列表
     * 
     * @param labelDescribeInfo
     * @return
     */
    public List<LabelCatalog> queryChildrenCatalog(BusinessObjectManager bomanager, String serialNo) {
        // 存放查询到的目录
        List<LabelCatalog> LabelCatalogs = new ArrayList<>();
        List<LabelCatalog> childrenLabelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class,
            "parentNo=:serialNo and labelType=:labelType order by serialNo", "labelType", LabelType._1.id, "serialNo", serialNo);
        childrenLabelCatalogs.forEach(childrenLabelCatalog -> {
            LabelCatalogs.add(childrenLabelCatalog);
            LabelCatalogs.addAll(queryChildrenCatalog(bomanager, childrenLabelCatalog.getSerialNo()));
        });
        return LabelCatalogs;
    }

    /**
     * 获取目录下的标签，并生成节点数图
     * 
     * @param rootNode
     * @param labelCatalogTemp
     * @param allCatalogs
     */
    private void addChildren(Tree rootNode, LabelCatalog labelCatalogTemp, List<LabelCatalog> allCatalogs) {
        for (LabelCatalog labelCatalog : allCatalogs) {
            log.info(labelCatalog.getParentNo());
            if (labelCatalog.getParentNo() != null && labelCatalog.getParentNo().equals(labelCatalogTemp.getSerialNo())) {
                Tree node = new Tree();
                node.setChildren(new ArrayList<Tree>());
                node.setTitle(labelCatalog.getLabelName());
                node.setLabelType(labelCatalog.getLabelType());
                node.setKey(labelCatalog.getSerialNo());
                node.setExpanded(CollectionUtils.isEmpty(rootNode.getChildren()));// 展开第一个根节点
                node.setDisable(true);
                this.addChildren(node, labelCatalog, allCatalogs);
                rootNode.getChildren().add(node);
                // this.sortTreeNode(node);
                node.setLeaf(CollectionUtils.isEmpty(node.getChildren()));
            }
        }
    }

    /**
     * 查询标签目录树图
     * 
     * @return LabelCatalogTreeServiceImpl 查询机构结果
     */
    public LabelCatalogTreeQueryRsp labelCatalogTreeQuery() {
        // 开启数据实体管理器
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelCatalogTreeQueryRsp rsp = new LabelCatalogTreeQueryRsp();
        rsp.setTrees(new ArrayList<Tree>());
        // 创建存放所有标签和目录的集合
        List<LabelCatalog> allCatalogs = new ArrayList<>();
        // 获得根目录的信息
        LabelCatalog labelCatalog = bomanager.keyLoadBusinessObject(LabelCatalog.class, "000");
        allCatalogs.add(labelCatalog);
        // 调用queryChildrenCatalog方法查询出所有目录
        allCatalogs.addAll(this.queryChildrenCatalog(bomanager, labelCatalog.getSerialNo()));

        for (LabelCatalog labelCatalogTemp : allCatalogs) {
            if ("root".equals(labelCatalogTemp.getParentNo())) {
                // 以"技能"为跟节点生成树图
                Tree rootNode = new Tree();
                // 这个存放该目录下的子目录
                rootNode.setChildren(new ArrayList<Tree>());
                rootNode.setTitle(labelCatalogTemp.getLabelName());
                rootNode.setLabelType(labelCatalogTemp.getLabelType());
                rootNode.setKey(labelCatalogTemp.getSerialNo());
                // 展开第一个根节点
                rootNode.setExpanded(CollectionUtils.isEmpty(rsp.getTrees()));
                rootNode.setLeaf(Boolean.FALSE);
                rootNode.setDisable(Boolean.TRUE);
                this.addChildren(rootNode, labelCatalogTemp, allCatalogs);
                // this.sortTreeNode(rootNode);
                if (rsp.getTrees().stream().anyMatch(tree -> tree.getKey().equals(rootNode.getKey()))) { // 去重
                    continue;
                }
                rsp.getTrees().add(rootNode);
            }
        }
        return rsp;
    }

}