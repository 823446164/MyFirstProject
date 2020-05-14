/*
 * 文件名：LabelCatalogListServiceImpl 
 * 版权：Copyright by www.amarsoft.com 
 * 描述：为LabelCatalogInfo模板提供方法
 * 修改人：yrong 
 * 修改时间：2020年5月11日 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogListService;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryRsp;
import com.amarsoft.aecd.parameter.constant.LabelType;
import com.amarsoft.aecd.system.constant.LabelStatus;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogList;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListDeleteReq;


/**
 * 标签目录树图Service实现类
 * 
 * @author yrong
 */
@Slf4j
@Service
public class LabelCatalogListServiceImpl implements LabelCatalogListService {
    /**
     * 查询结果集
     */
    public static class LabelCatalogListReqQuery implements RequestQuery<LabelCatalogListQueryReq> {
        @Override
        public Query apply(LabelCatalogListQueryReq labelCatalogListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(labelCatalogListQueryReq, LabelCatalogList.class);

            String sql = "select LC.serialNo as serialNo,LC.labelName as labelName,LC.parentNo as parentNo,LC.catalogRemark as catalogRemark,LC.author as author,LC.labelVersion as labelVersion,LC.inputUserId as inputUserId,LC.inputTime as inputTime,LC.inputOrgId as inputOrgId,LC.updateUserId as updateUserId,LC.updateTime as updateTime,LC.updateOrgId as updateOrgId"
                         + " from LABEL_CATALOG LC" + " where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
     * 查询到的数据转换为响应实体
     */
    public static class LabelCatalogListRspConvert implements Convert<LabelCatalogList> {
        @Override
        public LabelCatalogList apply(BusinessObject bo) {
            LabelCatalogList temp = new LabelCatalogList();
            // 查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setLabelName(bo.getString("LabelName"));
            temp.setParentNo(bo.getString("ParentNo"));
            temp.setCatalogRemark(bo.getString("CatalogRemark"));
            temp.setAuthor(bo.getString("Author"));
            temp.setLabelVersion(bo.getString("LabelVersion"));
            temp.setInputUserId(bo.getString("InputUserId"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
            temp.setUpdateUserId(bo.getString("UpdateUserId"));
            temp.setUpdateTime(bo.getString("UpdateTime"));
            temp.setUpdateOrgId(bo.getString("UpdateOrgId"));

            return temp;
        }
    }

    /**
     * 标签目录树图多记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public LabelCatalogListQueryRsp labelCatalogListQuery(@Valid LabelCatalogListQueryReq labelCatalogListQueryReq) {
        LabelCatalogListQueryRsp labelCatalogListQueryRsp = new LabelCatalogListQueryRsp();

        Query query = new LabelCatalogListReqQuery().apply(labelCatalogListQueryReq);
        String fullsql = query.getSql();

        LabelCatalogListRspConvert convert = new LabelCatalogListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(labelCatalogListQueryReq.getBegin(),
            labelCatalogListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();

        if (null != businessObjectList && !businessObjectList.isEmpty()) {
            List<LabelCatalogList> labelCatalogLists = new ArrayList<LabelCatalogList>();
            for (BusinessObject bo : businessObjectList) {
                // 查询到的数据转换为响应实体
                labelCatalogLists.add(convert.apply(bo));
            }
            labelCatalogListQueryRsp.setLabelCatalogLists(labelCatalogLists);
        }
        labelCatalogListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));

        return labelCatalogListQueryRsp;
    }

    /**
     * 标签目录树图多记录保存
     * 
     * @param request
     * @return
     */
    @Override
    public void labelCatalogListSave(@Valid LabelCatalogListSaveReq labelCatalogListSaveReq) {
        labelCatalogListSaveAction(labelCatalogListSaveReq.getLabelCatalogLists());
    }

    /**
     * 标签目录树图多记录保存
     * 
     * @param
     * @return
     */
    @Transactional
    public void labelCatalogListSaveAction(List<LabelCatalogList> labelCatalogLists) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (labelCatalogLists != null) {
            for (LabelCatalogList labelCatalogListTmp : labelCatalogLists) {}
        }
        bomanager.updateDB();
    }

    /**
     * 标签目录树图删除
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void labelCatalogListDelete(@Valid LabelCatalogListDeleteReq labelCatalogListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        boolean isNotHaveStatusOk = selectLabelByLabelCatalog(labelCatalogListDeleteReq.getSerialNo());
        //isNotHaveStatusOk 为true则说明有生效标签，不能删除   false为没有生效标签，可以删除
        if (!isNotHaveStatusOk) {
            LabelCatalog labelCatalog = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelCatalogListDeleteReq.getSerialNo());
            bomanager.deleteBusinessObject(labelCatalog);
            bomanager.updateDB();
        }
        else {
            // TODO需要修改System.out.println
            System.out.println("此目录下包含生效标签，不可删除");
        }
    }

    
    /**
     * 查询出选中目录下所有标签
     * 
     * @param labelDescribeInfo
     * @return
     */
    public List<LabelCatalog> queryChildrenCatalog(BusinessObjectManager bomanager, String serialNo) {
        // 存放查询到的目录
        List<LabelCatalog> LabelCatalogs = new ArrayList<>();
        List<LabelCatalog> childrenLabelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class, "parentNo=:serialNo and (labelType=:labelType2 or labelType=:labelType3) order by serialNo",
            "labelType2",LabelType._3.id, "labelType3",LabelType._2.id,"serialNo", serialNo);
//        bomanager.selectBusinessObjectsBySql("select * from LabelCatalog where parentNo=:serialNo and (labelType=2 or labelType=3)", parameters)
        childrenLabelCatalogs.forEach(childrenLabelCatalog -> {
            LabelCatalogs.add(childrenLabelCatalog);
            LabelCatalogs.addAll(queryChildrenCatalog(bomanager, childrenLabelCatalog.getSerialNo()));
        });
        return LabelCatalogs;
    }
    

    /**
     * 判断标签目录能否删除标签目录树图 
     * 标签目录树图(若该目录下有已经生效的标签，则不能删除，返回false。若可以删除，返回true)
     * @param serialNo
     * @return isNotHaveStatusOk
     */
    public boolean selectLabelByLabelCatalog(String serialNo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        boolean isHaveStatusOk = false;
        List<LabelCatalog> labelCatalogs = queryChildrenCatalog(bomanager, serialNo);
        for (LabelCatalog labelCatalogTemp : labelCatalogs) {
            if(LabelStatus.Enabled.id.equals(labelCatalogTemp.getLabelStatus())) {
                isHaveStatusOk = true;
            } 
        }
        return isHaveStatusOk;

    }
}