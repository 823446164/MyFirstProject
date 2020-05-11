package com.amarsoft.app.ems.parameter.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelListService;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelList;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListSaveReq;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListDeleteReq;

/**
 * 标签ListService实现类
 * @author ylgao
 */
@Slf4j
@Service
public class LabelListServiceImpl implements LabelListService{
    /**
                   * 查询结果集
     */
    public static class LabelListReqQuery implements RequestQuery<LabelListQueryReq> {
        @Override
        public Query apply(LabelListQueryReq labelListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(labelListQueryReq, LabelList.class);
            
            String sql = "select LC.serialNo as serialNo,LC.labelName as labelName,LC.codeNo as codeNo,LC.labelStatus as labelStatus,LC.belongCataLog as belongCataLog,LC.rootNo as rootNo,LC.abilityType as abilityType,LC.labelDescribe as labelDescribe,LC.labelVersion as labelVersion,LC.inputUserId as inputUserId,LC.inputTime as inputTime,LC.inputOrgId as inputOrgId,LC.updateUserId as updateUserId,LC.updateTime as updateTime,LC.updateOrgId as updateOrgId,"
                +"LD.labelNo as labelNo,LD.labelLevel as labelLevel,LD.inputUserId as inputUserId,LD.inputTime as inputTime,LD.inputOrgId as inputOrgId,LD.updateUserId as updateUserId,LD.updateTime as updateTime,LD.updateOrgId as updateOrgId"
                +" from LABEL_CATALOG LC,LABEL_DESCRIBE LD"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
                  * 查询到的数据转换为响应实体
     */
    public static class LabelListRspConvert implements Convert<LabelList> {
        @Override
        public LabelList apply(BusinessObject bo) {
            LabelList temp = new LabelList();
                
            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setLabelName(bo.getString("LabelName"));
            temp.setCodeNo(bo.getString("CodeNo"));
            temp.setLabelStatus(bo.getString("LabelStatus"));
            temp.setBelongCataLog(bo.getString("BelongCataLog"));
            temp.setRootNo(bo.getString("RootNo"));
            temp.setAbilityType(bo.getString("AbilityType"));
            temp.setLabelDescribe(bo.getString("LabelDescribe"));
            temp.setLabelVersion(bo.getString("LabelVersion"));
            temp.setLabelNo(bo.getString("LabelNo"));
            temp.setLabelLevel(bo.getString("LabelLevel"));
            temp.setLC_InputUserId(bo.getString("InputUserId"));
            temp.setLC_InputTime(bo.getString("InputTime"));
            temp.setLC_InputOrgId(bo.getString("InputOrgId"));
            temp.setLC_UpdateUserId(bo.getString("UpdateUserId"));
            temp.setLC_UpdateTime(bo.getString("UpdateTime"));
            temp.setLC_UpdateOrgId(bo.getString("UpdateOrgId"));
            temp.setLD_InputUserId(bo.getString("InputUserId"));
            temp.setLD_InputTime(bo.getString("InputTime"));
            temp.setLD_InputOrgId(bo.getString("InputOrgId"));
            temp.setLD_UpdateUserId(bo.getString("UpdateUserId"));
            temp.setLD_UpdateTime(bo.getString("UpdateTime"));
            temp.setLD_UpdateOrgId(bo.getString("UpdateOrgId"));
            
            return temp;
        }
    }

    /**
     * 标签List多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public LabelListQueryRsp labelListQuery(@Valid LabelListQueryReq labelListQueryReq) {
        LabelListQueryRsp labelListQueryRsp = new LabelListQueryRsp();
        
        Query query = new LabelListReqQuery().apply(labelListQueryReq);
        String fullsql = query.getSql();
        
        LabelListRspConvert convert = new LabelListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(labelListQueryReq.getBegin(), labelListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<LabelList> labelLists = new ArrayList<LabelList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                labelLists.add(convert.apply(bo));
            }
            labelListQueryRsp.setLabelLists(labelLists);
        }
        labelListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return labelListQueryRsp;
    }

    /**
     * 标签List多记录保存
     * @param request
     * @return
     */
    @Override
    public void labelListSave(@Valid LabelListSaveReq labelListSaveReq) {
        labelListSaveAction(labelListSaveReq.getLabelLists());
    }
    /**
     * 标签List多记录保存
     * @param
     * @return
     */
    @Transactional
    public void labelListSaveAction(List<LabelList> labelLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(labelLists!=null){
            for(LabelList labelListTmp :labelLists){
            }
        }
        bomanager.updateDB();
    }


    /**
     * 标签List删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void labelListDelete(@Valid LabelListDeleteReq labelListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelCatalog labelCatalog=bomanager.keyLoadBusinessObject(LabelCatalog.class, labelListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(labelCatalog);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
