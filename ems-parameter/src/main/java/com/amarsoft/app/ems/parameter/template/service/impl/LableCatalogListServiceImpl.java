package com.amarsoft.app.ems.parameter.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LableCatalogListService;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogList;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.entity.LableCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListDeleteReq;

/**
 * 
 * 为LabelCatalogList模板提供方法
 * 〈功能详细描述〉
 * @author amarsoft
 * @version 2020年5月9日
 * @see LableCatalogListServiceImpl
 * @since
 */
@Slf4j 
@Service
public class LableCatalogListServiceImpl implements LableCatalogListService{
    /**
                   * 查询结果集
     */
    public static class LableCatalogListReqQuery implements RequestQuery<LableCatalogListQueryReq> {
        @Override 
        public Query apply(LableCatalogListQueryReq lableCatalogListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(lableCatalogListQueryReq, LableCatalogList.class);
            
            String sql = "select LC.serialNo as serialNo,LC.labelName as labelName,LC.parentNo as parentNo,LC.catalogRemark as catalogRemark,LC.author as author,LC.version as version,LC.inputUserId as inputUserId,LC.inputTime as inputTime,LC.inputOrgId as inputOrgId,LC.updateUserId as updateUserId,LC.updateTime as updateTime,LC.updateOrgId as updateOrgId"
                +" from LABEL_CATALOG LC"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class LableCatalogListRspConvert implements Convert<LableCatalogList> {
        @Override
        public LableCatalogList apply(BusinessObject bo) {
            LableCatalogList temp = new LableCatalogList();
                
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
    public LableCatalogListQueryRsp lableCatalogListQuery(@Valid LableCatalogListQueryReq lableCatalogListQueryReq) {
        LableCatalogListQueryRsp lableCatalogListQueryRsp = new LableCatalogListQueryRsp();
        
        Query query = new LableCatalogListReqQuery().apply(lableCatalogListQueryReq);
        String fullsql = query.getSql();
        
        LableCatalogListRspConvert convert = new LableCatalogListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(lableCatalogListQueryReq.getBegin(), lableCatalogListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<LableCatalogList> lableCatalogLists = new ArrayList<LableCatalogList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                lableCatalogLists.add(convert.apply(bo));
            }
            lableCatalogListQueryRsp.setLableCatalogLists(lableCatalogLists);
        }
        lableCatalogListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));   
        return lableCatalogListQueryRsp;
    }

    

    
    
    
    
    
    
    
    
    
    /**
     * 标签目录树图多记录保存
     * @param request
     * @return
     */
    @Override
    public void lableCatalogListSave(@Valid LableCatalogListSaveReq lableCatalogListSaveReq) {
        lableCatalogListSaveAction(lableCatalogListSaveReq.getLableCatalogLists());
    }
    /**
     * 标签目录树图多记录保存
     * @param
     * @return
     */
    @Transactional
    public void lableCatalogListSaveAction(List<LableCatalogList> lableCatalogLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(lableCatalogLists!=null){
            for(LableCatalogList lableCatalogListTmp :lableCatalogLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 标签目录删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void lableCatalogListDelete(@Valid LableCatalogListDeleteReq lableCatalogListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LableCatalog lableCatalog=bomanager.keyLoadBusinessObject(LableCatalog.class, lableCatalogListDeleteReq.getSerialNo());
       //根据LabelType码值0为标签目录，1为标签。判断是要删除标签还是目录
        if(lableCatalog.getLabelType()=="1"){
            if(lableCatalog.getLabelStatus()=="3") {
                bomanager.deleteBusinessObject(lableCatalog);
            }else {
                System.out.println("此标签不为失效标签，无法删除");
            }
        }else {
            
        }
        
        bomanager.deleteBusinessObject(lableCatalog);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
