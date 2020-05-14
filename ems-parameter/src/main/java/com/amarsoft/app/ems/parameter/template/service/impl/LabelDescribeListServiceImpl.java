package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelDescribeListService;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeList;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListSaveReq;
import com.amarsoft.app.ems.parameter.entity.LabelDescribe;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListDeleteReq;


/**
 * 标签能力描述ListService实现类
 * 
 * @author yrong
 */
@Slf4j
@Service
public class LabelDescribeListServiceImpl implements LabelDescribeListService {
    /**
     * 查询结果集
     */
    public static class LabelDescribeListReqQuery implements RequestQuery<LabelDescribeListQueryReq> {
        @Override
        public Query apply(LabelDescribeListQueryReq labelDescribeListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(labelDescribeListQueryReq, LabelDescribeList.class);

            String sql = "select LD.serialNo as serialNo,LD.labelNo as labelNo,LD.labelLevel as labelLevel,LD.levelDescribe as levelDescribe,LD.inputUserId as inputUserId,LD.inputTime as inputTime,LD.inputOrgId as inputOrgId,LD.updateUserId as updateUserId,LD.updateTime as updateTime,LD.updateOrgId as updateOrgId"
                         + " from LABEL_DESCRIBE LD" + " where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
     * 查询到的数据转换为响应实体
     */
    public static class LabelDescribeListRspConvert implements Convert<LabelDescribeList> {
        @Override
        public LabelDescribeList apply(BusinessObject bo) {
            LabelDescribeList temp = new LabelDescribeList();
            // 查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setLabelNo(bo.getString("LabelNo"));
            temp.setLabelLevel(bo.getString("LabelLevel"));
            temp.setLevelDescribe(bo.getString("LevelDescribe"));
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
     * 标签能力描述List多记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public LabelDescribeListQueryRsp labelDescribeListQuery(@Valid LabelDescribeListQueryReq labelDescribeListQueryReq) {
        LabelDescribeListQueryRsp labelDescribeListQueryRsp = new LabelDescribeListQueryRsp();

        Query query = new LabelDescribeListReqQuery().apply(labelDescribeListQueryReq);
        String fullsql = query.getSql();

        LabelDescribeListRspConvert convert = new LabelDescribeListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(labelDescribeListQueryReq.getBegin(),
            labelDescribeListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();

        if (null != businessObjectList && !businessObjectList.isEmpty()) {
            List<LabelDescribeList> labelDescribeLists = new ArrayList<LabelDescribeList>();
            for (BusinessObject bo : businessObjectList) {
                // 查询到的数据转换为响应实体
                labelDescribeLists.add(convert.apply(bo));
            }
            labelDescribeListQueryRsp.setLabelDescribeLists(labelDescribeLists);
        }
        labelDescribeListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));

        return labelDescribeListQueryRsp;
    }

    /**
     * 标签能力描述List多记录保存
     * 
     * @param request
     * @return
     */
    @Override
    public void labelDescribeListSave(@Valid LabelDescribeListSaveReq labelDescribeListSaveReq) {
        labelDescribeListSaveAction(labelDescribeListSaveReq.getLabelDescribeLists());
    }

    /**
     * 标签能力描述List多记录保存
     * 
     * @param
     * @return
     */
    @Transactional
    public void labelDescribeListSaveAction(List<LabelDescribeList> labelDescribeLists) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (labelDescribeLists != null) {
            for (LabelDescribeList labelDescribeListTmp : labelDescribeLists) {}
        }
        bomanager.updateDB();
    }

    /**
     * 标签能力描述List删除
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void labelDescribeListDelete(@Valid LabelDescribeListDeleteReq labelDescribeListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelDescribe labelDescribe = bomanager.keyLoadBusinessObject(LabelDescribe.class, labelDescribeListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(labelDescribe);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();

    }
}
