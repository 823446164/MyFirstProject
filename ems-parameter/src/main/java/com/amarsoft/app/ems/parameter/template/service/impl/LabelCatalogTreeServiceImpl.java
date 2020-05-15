/*
 * 文件名：LabelCatalogTreeServiceImpl.java 
 * 版权：Copyright by www.amarsoft.com 
 * 描述： 
 * 修改人：yrong
 * 修改时间：2020年5月12日 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amarsoft.aecd.employee.constant.ParentNo;
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
     * 通过递归的方法，查询出所有的标签目录
     * @param labelDescribeInfo
     * @return
     */
    public List<LabelCatalog> queryChildrenCatalog(BusinessObjectManager bomanager, String serialNo) {
        // 存放查询到的目录
        List<LabelCatalog> LabelCatalogs = new ArrayList<>();
        //查找当前目录的子目录，依靠parentNo
        List<LabelCatalog> childrenLabelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class,
            "parentNo=:serialNo and labelType=:labelType order by serialNo", "labelType", LabelType._1.id, "serialNo", serialNo);
        //遍历查询出的子目录
        childrenLabelCatalogs.forEach(childrenLabelCatalog -> {
            //将当前子目录放入存放所有目录的集合
            LabelCatalogs.add(childrenLabelCatalog);
            //把当前查询到的子目录当做父目录，再次执行此方法，并将所有查到的集合再次放入大集合
            LabelCatalogs.addAll(queryChildrenCatalog(bomanager, childrenLabelCatalog.getSerialNo()));
        });
        return LabelCatalogs;
    }

    /**
     * 获取目录下的标签，并生成节点数图
     * 此方法主要把查询到的所有目录分好层级，梳理成树图结构
     * @param rootNode
     * @param labelCatalogTemp
     * @param allCatalogs
     */
    private void addChildren(Tree rootNode, LabelCatalog labelCatalogTemp, List<LabelCatalog> allCatalogs) {
        //遍历存放所有目录的集合
        for (LabelCatalog labelCatalog : allCatalogs) {
            //查找属于前目录下的子目录
            if (labelCatalog.getParentNo() != null && labelCatalog.getParentNo().equals(labelCatalogTemp.getSerialNo())) {
                //把子目录信息放进node里面，此node就是rootNode的子机构信息
                Tree node = new Tree();
                node.setChildren(new ArrayList<Tree>());
                node.setTitle(labelCatalog.getLabelName());
                node.setLabelType(labelCatalog.getLabelType());
                node.setKey(labelCatalog.getSerialNo());
                // 展开第一个根节点
                node.setExpanded(CollectionUtils.isEmpty(rootNode.getChildren()));
                //是否禁用
                node.setDisable(true);
               //再次调用此方法，查询属于当前目录的子目录录入信息
                this.addChildren(node, labelCatalog, allCatalogs);
                rootNode.getChildren().add(node);
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
        //树图主要靠Tree来显示层级
        rsp.setTrees(new ArrayList<Tree>());
        // 创建存放所有目录的集合
        List<LabelCatalog> allCatalogs = new ArrayList<>();
        // 获得根目录的信息
        // modify by yrong 20200514 为了不把代码写死，由主键获得根目录信息改为由parentNo获得。
        // begin
        LabelCatalog labelCatalog = bomanager.loadBusinessObject(LabelCatalog.class,"parentNo", ParentNo._1.name);
        // end
        //把查询到的根目录的信息保存进allCatalogs集合
        allCatalogs.add(labelCatalog);
        // 调用queryChildrenCatalog方法查询出所有目录并放入allCatalogs集合
        allCatalogs.addAll(this.queryChildrenCatalog(bomanager, labelCatalog.getSerialNo()));
        //遍历查询到的所有目录
        for (LabelCatalog labelCatalogTemp : allCatalogs) {
            //找到根目录
            // modify by yrong 20200514 为了不把代码写死，root由枚举类获得
            // begin
            if (ParentNo._1.name.equals(labelCatalogTemp.getParentNo())) {
                // 以"技能"为跟节点生成树图，Tree里放Tree，来显示层级
                Tree rootNode = new Tree();
                // 这个存放该目录下的子目录
                //在根目录的这个rootNode里面再准备好一个集合准备存标签目录
                rootNode.setChildren(new ArrayList<Tree>());
                //标签名称当做树图节点名称
                rootNode.setTitle(labelCatalogTemp.getLabelName());
                rootNode.setLabelType(labelCatalogTemp.getLabelType());
                rootNode.setKey(labelCatalogTemp.getSerialNo());
                // 展开第一个根节点（有内容则展开）
                rootNode.setExpanded(CollectionUtils.isEmpty(rsp.getTrees()));
                rootNode.setLeaf(Boolean.FALSE);
                rootNode.setDisable(Boolean.TRUE);
                //将所有的目录根据要求分级，形成树图
                this.addChildren(rootNode, labelCatalogTemp, allCatalogs);
                // 去重
                if (rsp.getTrees().stream().anyMatch(tree -> tree.getKey().equals(rootNode.getKey()))) { 
                    continue;
                }
                //把得到的树图放入响应中
                rsp.getTrees().add(rootNode);
            }
            // end
        }
        return rsp;
    }
}