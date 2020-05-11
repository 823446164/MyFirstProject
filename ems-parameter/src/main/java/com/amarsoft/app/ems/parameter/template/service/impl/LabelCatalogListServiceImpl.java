package com.amarsoft.app.ems.parameter.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogListService;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryRsp;
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
 * @author ylgao
 */
@Slf4j
@Service
public class LabelCatalogListServiceImpl implements LabelCatalogListService{
    /**
                   * 查询结果集
     */
    public static class LabelCatalogListReqQuery implements RequestQuery<LabelCatalogListQueryReq> {
        @Override
        public Query apply(LabelCatalogListQueryReq labelCatalogListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(labelCatalogListQueryReq, LabelCatalogList.class);
            
            String sql = "select LC.serialNo as serialNo,LC.labelName as labelName,LC.parentNo as parentNo,LC.catalogRemark as catalogRemark,LC.author as author,LC.labelVersion as labelVersion,LC.inputUserId as inputUserId,LC.inputTime as inputTime,LC.inputOrgId as inputOrgId,LC.updateUserId as updateUserId,LC.updateTime as updateTime,LC.updateOrgId as updateOrgId"
                +" from LABEL_CATALOG LC"
                +" where 1=1";
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
                
            //查询到的数据转换为响应实体
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
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(labelCatalogListQueryReq.getBegin(), labelCatalogListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<LabelCatalogList> labelCatalogLists = new ArrayList<LabelCatalogList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                labelCatalogLists.add(convert.apply(bo));
            }
            labelCatalogListQueryRsp.setLabelCatalogLists(labelCatalogLists);
        }
        labelCatalogListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return labelCatalogListQueryRsp;
    }

    /**
     * 标签目录树图多记录保存
     * @param request
     * @return
     */
    @Override
    public void labelCatalogListSave(@Valid LabelCatalogListSaveReq labelCatalogListSaveReq) {
        labelCatalogListSaveAction(labelCatalogListSaveReq.getLabelCatalogLists());
    }
    /**
     * 标签目录树图多记录保存
     * @param
     * @return
     */
    @Transactional
    public void labelCatalogListSaveAction(List<LabelCatalogList> labelCatalogLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(labelCatalogLists!=null){
            for(LabelCatalogList labelCatalogListTmp :labelCatalogLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 标签目录树图删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void labelCatalogListDelete(@Valid LabelCatalogListDeleteReq labelCatalogListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelCatalog labelCatalog=bomanager.keyLoadBusinessObject(LabelCatalog.class, labelCatalogListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(labelCatalog);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
