package com.amarsoft.app.ems.parameter.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LableDescribeListService;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeList;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListSaveReq;
import com.amarsoft.app.ems.parameter.entity.LableCatalog;
import com.amarsoft.app.ems.parameter.entity.LableDescribe;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListDeleteReq;

/**
 * 标签树图Service实现类
 * @author ylgao
 */
@Slf4j
@Service
public class LableDescribeListServiceImpl implements LableDescribeListService{
    /**
                   * 查询结果集
     */
    public static class LableDescribeListReqQuery implements RequestQuery<LableDescribeListQueryReq> {
        @Override
        public Query apply(LableDescribeListQueryReq lableDescribeListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(lableDescribeListQueryReq, LableDescribeList.class);
            
            String sql = "select LC.labelName as labelName,LC.codeNo as codeNo,LC.labelStatus as labelStatus,LC.cataLog as cataLog,LC.rootNo as rootNo,LC.abilityType as abilityType,LC.describe as describe,LC.version as version,LC.inputUserId as inputUserId,LC.inputTime as inputTime,LC.inputOrgId as inputOrgId,LC.updateUserId as updateUserId,LC.updateTime as updateTime,LC.updateOrgId as updateOrgId,"
                +"LD.serialNo as serialNo,LD.labelNo as labelNo,LD.labelLevel as labelLevel,LD.labelDescribe as labelDescribe,LD.inputUserId as inputUserId,LD.inputTime as inputTime,LD.inputOrgId as inputOrgId,LD.updateUserId as updateUserId,LD.updateTime as updateTime,LD.updateOrgId as updateOrgId"
                +" from LABEL_CATALOG LC,LABEL_DESCRIBE LD"
                +" where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }
  
    /**
                  * 查询到的数据转换为响应实体
     */
    public static class LableDescribeListRspConvert implements Convert<LableDescribeList> {
        @Override
        public LableDescribeList apply(BusinessObject bo) {
            LableDescribeList temp = new LableDescribeList();
                
            //查询到的数据转换为响应实体
            temp.setLabelName(bo.getString("LabelName"));
            temp.setCodeNo(bo.getString("CodeNo"));
            temp.setLabelStatus(bo.getString("LabelStatus"));
            temp.setBelongCataLog(bo.getString("BelongCataLog"));
            temp.setRootNo(bo.getString("RootNo"));
            temp.setAbilityType(bo.getString("AbilityType"));
            temp.setLabelDescribe(bo.getString("LabelDescribe"));
            temp.setLabelVersion(bo.getString("LabelVersion"));
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setLabelNo(bo.getString("LabelNo"));
            temp.setLabelLevel(bo.getString("LabelLevel"));
            temp.setLevelDescribe(bo.getString("LevelDescribe"));
            temp.setLC_inputUserId(bo.getString("InputUserId"));
            temp.setLC_inputTime(bo.getString("InputTime"));
            temp.setLC_inputOrgId(bo.getString("InputOrgId"));
            temp.setLC_updateUserId(bo.getString("UpdateUserId"));
            temp.setLC_updateTime(bo.getString("UpdateTime"));
            temp.setLC_updateOrgId(bo.getString("UpdateOrgId"));
            temp.setLD_inputUserId(bo.getString("InputUserId"));
            temp.setLD_inputTime(bo.getString("InputTime"));
            temp.setLD_inputOrgId(bo.getString("InputOrgId"));
            temp.setLD_updateUserId(bo.getString("UpdateUserId"));
            temp.setLD_updateTime(bo.getString("UpdateTime"));
            temp.setLD_updateOrgId(bo.getString("UpdateOrgId"));
            
            return temp;
        }
    }

    /**
     * 标签树图多记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public LableDescribeListQueryRsp lableDescribeListQuery(@Valid LableDescribeListQueryReq lableDescribeListQueryReq) {
        LableDescribeListQueryRsp lableDescribeListQueryRsp = new LableDescribeListQueryRsp();
        
        Query query = new LableDescribeListReqQuery().apply(lableDescribeListQueryReq);
        String fullsql = query.getSql();
        
        LableDescribeListRspConvert convert = new LableDescribeListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(lableDescribeListQueryReq.getBegin(), lableDescribeListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<LableDescribeList> lableDescribeLists = new ArrayList<LableDescribeList>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                lableDescribeLists.add(convert.apply(bo));
            }
            lableDescribeListQueryRsp.setLableDescribeLists(lableDescribeLists);
        }
        lableDescribeListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return lableDescribeListQueryRsp;
    }

    /**
     * 标签树图多记录保存
     * @param request
     * @return
     */
    @Override
    public void lableDescribeListSave(@Valid LableDescribeListSaveReq lableDescribeListSaveReq) {
        lableDescribeListSaveAction(lableDescribeListSaveReq.getLableDescribeLists());
    }
    /**
     * 标签树图多记录保存
     * @param
     * @return
     */
    @Transactional
    public void lableDescribeListSaveAction(List<LableDescribeList> lableDescribeLists){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(lableDescribeLists!=null){
            
            for(LableDescribeList lableDescribeListTmp :lableDescribeLists){
              //新增或更新label_describe表
                LableDescribe ld = bomanager.loadBusinessObject(LableDescribe.class, lableDescribeListTmp.getLabelNo());
                if(ld==null) {
                    ld = new LableDescribe();
                    ld.generateKey();
                }
                BeanUtils.copyProperties(lableDescribeListTmp, ld);
                bomanager.updateBusinessObject(ld);
           
              //新增或更新label_catalog表
              LableCatalog lc = bomanager.keyLoadBusinessObject(LableCatalog.class, lableDescribeListTmp.getSerialNo());
            if(lc==null) {
                lc = new LableCatalog();
                lc.generateKey();
            }
            BeanUtils.copyProperties(lableDescribeListTmp, lc);
            bomanager.updateBusinessObject(lc);
            }           
        }
        bomanager.updateDB();
    }


    /**
     * 标签树图删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void lableDescribeListDelete(@Valid LableDescribeListDeleteReq lableDescribeListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LableCatalog lableCatalog=bomanager.keyLoadBusinessObject(LableCatalog.class);
        bomanager.deleteBusinessObject(lableCatalog);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
