/*
 * 文件名：RankStandardCatalogListServiceImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogListService;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryRsp;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogList;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogSonQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogSonQueryRsq;
import com.amarsoft.app.ems.parameter.entity.RankStandardCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListDeleteReq;


/**
 * 〈职级标准列表Service实现类〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
@Slf4j
@Service
public class RankStandardCatalogListServiceImpl implements RankStandardCatalogListService {

    /**
     * 〈查询结果集〉
     * 
     * @author xphe
     * @version 2020年5月8日
     * @see 
     * @since
     */
    public static class RankStandardCatalogListReqQuery implements RequestQuery<RankStandardCatalogListQueryReq> {
        @Override
        public Query apply(RankStandardCatalogListQueryReq rankStandardCatalogListQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(rankStandardCatalogListQueryReq, RankStandardCatalogList.class);

            String sql = "select RSC.serialNo as serialNo,RSC.rankStandard as rankStandard,RSC.rankName as rankName,RSC.parentRankNo as parentRankNo,RSC.ability as ability,RSC.rankDescribe as rankDescribe,RSC.responeDescribe as responeDescribe,RSC.abilityDescribe as abilityDescribe,RSC.belongTeam as belongTeam,RSC.rankType as rankType,RSC.inputUserId as inputUserId,RSC.inputTime as inputTime,RSC.inputOrgId as inputOrgId,RSC.updateUserId as updateUserId,RSC.updateTime as updateTime,RSC.updateOrgId as updateOrgId"
                         + " from RANK_STANDARD_CATALOG RSC" + " where 1=1  and RSC.rankType=1 and RSC.belongTeam= :belongTeam ";
            return queryProperties.assembleSql(sql,"belongTeam",rankStandardCatalogListQueryReq.getBelongTeam());
        }
    }

    /**
     * 〈查询到的数据转换为响应实体〉
     * 
     * @author xphe
     * @version 2020年5月8日
     * @see 
     * @since
     */
    public static class RankStandardCatalogListRspConvert implements Convert<RankStandardCatalogList> {
        @Override
        public RankStandardCatalogList apply(BusinessObject bo) {
            RankStandardCatalogList temp = new RankStandardCatalogList();

            //查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setRankStandard(bo.getString("RankStandard"));
            temp.setRankName(bo.getString("RankName"));
            temp.setParentRankNo(bo.getString("ParentRankNo"));
            temp.setAbility(bo.getString("Ability"));
            temp.setRankDescribe(bo.getString("RankDescribe"));
            temp.setResponeDescribe(bo.getString("ResponeDescribe"));
            temp.setAbilityDescribe(bo.getString("AbilityDescribe"));
            temp.setBelongTeam(bo.getString("BelongTeam"));
            temp.setRankType(bo.getString("RankType"));
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
     * 
     * Description: 职级标准列表多记录查询
     *
     * @param rankStandardCatalogListQueryReq
     * @return response
     * @see
     */
    @Override
    @Transactional
    public RankStandardCatalogListQueryRsp rankStandardCatalogListQuery(@Valid RankStandardCatalogListQueryReq rankStandardCatalogListQueryReq) {
        RankStandardCatalogListQueryRsp rankStandardCatalogListQueryRsp = new RankStandardCatalogListQueryRsp();

        Query query = new RankStandardCatalogListReqQuery().apply(rankStandardCatalogListQueryReq);
        String fullsql = query.getSql();

        RankStandardCatalogListRspConvert convert = new RankStandardCatalogListRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(rankStandardCatalogListQueryReq.getBegin(),
            rankStandardCatalogListQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();

        if (null != businessObjectList && !businessObjectList.isEmpty()) {
            List<RankStandardCatalogList> rankStandardCatalogLists = new ArrayList<RankStandardCatalogList>();
            for (BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                rankStandardCatalogLists.add(convert.apply(bo));
            }
            rankStandardCatalogListQueryRsp.setRankStandardCatalogLists(rankStandardCatalogLists);
        }
        rankStandardCatalogListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));

        return rankStandardCatalogListQueryRsp;
    }
    /**
     * 
     * Description: 根据团队查询管理下的职级列表
     *
     * @param rankStandardCatalogListQueryReq
     * @return response
     * @see
     */
    @Override
    @Transactional
        public RankStandardCatalogListQueryRsp ranStandardCatalogManagerQuery(@Valid RankStandardCatalogListQueryReq rankStandardCatalogListQueryReq) {
            BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
            List<RankStandardCatalog> ranCatalogs=bomanager.loadBusinessObjects(RankStandardCatalog.class, "belongTeam=:belongTeam and rankType=2",
                "belongTeam", rankStandardCatalogListQueryReq.getBelongTeam());
            RankStandardCatalogListQueryRsp response=new RankStandardCatalogListQueryRsp(); 
            List<RankStandardCatalogList> ranCatalogLists = null;
            if(!CollectionUtils.isEmpty(ranCatalogs)) {
                ranCatalogLists=new ArrayList<RankStandardCatalogList>();
                for(RankStandardCatalog rank:ranCatalogs) {
                    RankStandardCatalogList rankresponse=new RankStandardCatalogList();
                    rankresponse.setSerialNo(rank.getSerialNo());
                    rankresponse.setRankStandard(rank.getRankStandard());
                    rankresponse.setRankName(rank.getRankName());
                    rankresponse.setParentRankNo(rank.getParentRankNo());
                    rankresponse.setAbility(rank.getAbility());
                    rankresponse.setRankDescribe(rank.getRankDescribe());
                    rankresponse.setResponeDescribe(rank.getResponeDescribe());
                    rankresponse.setAbilityDescribe(rank.getAbilityDescribe());
                    rankresponse.setBelongTeam(rank.getBelongTeam());
                    rankresponse.setRankType(rank.getRankType());
                    rankresponse.setInputUserId(rank.getInputUserId());
                    rankresponse.setInputTime(rank.getInputTime());
                    rankresponse.setInputOrgId(rank.getInputOrgId());
                    rankresponse.setUpdateUserId(rank.getUpdateUserId());
                    rankresponse.setUpdateTime(rank.getUpdateTime());
                    rankresponse.setUpdateOrgId(rank.getUpdateOrgId());
                    ranCatalogLists.add(rankresponse);
                }
            }
            response.setRankStandardCatalogLists(ranCatalogLists);
            return response;
        }

    /**
     * 
     * Description: 子职级标准列表多记录查询
     *
     * @param rankStandardCatalogSonQueryReq
     * @return response
     * @see
     */
    @Override
    @Transactional
    public RankStandardCatalogSonQueryRsq rankStandardCatalogSonQuery(@Valid RankStandardCatalogSonQueryReq rankStandardCatalogSonQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RankStandardCatalog> ranCatalogs = bomanager.loadBusinessObjects(RankStandardCatalog.class, "parentRankNo=:parentRankNo",
            "parentRankNo", rankStandardCatalogSonQueryReq.getSerialNo());
        RankStandardCatalogSonQueryRsq response = new RankStandardCatalogSonQueryRsq();
        List<RankStandardCatalogList> ranCatalogLists = null;
            if(!CollectionUtils.isEmpty(ranCatalogs)) {
                ranCatalogLists=new ArrayList<RankStandardCatalogList>();
                for(RankStandardCatalog rank:ranCatalogs) {
                    RankStandardCatalogList rankresponse=new RankStandardCatalogList();
                    rankresponse.setSerialNo(rank.getSerialNo());
                    rankresponse.setRankStandard(rank.getRankStandard());
                    rankresponse.setRankName(rank.getRankName());
                    rankresponse.setParentRankNo(rank.getParentRankNo());
                    rankresponse.setAbility(rank.getAbility());
                    rankresponse.setRankDescribe(rank.getRankDescribe());
                    rankresponse.setResponeDescribe(rank.getResponeDescribe());
                    rankresponse.setAbilityDescribe(rank.getAbilityDescribe());
                    rankresponse.setBelongTeam(rank.getBelongTeam());
                    rankresponse.setRankType(rank.getRankType());
                    rankresponse.setInputUserId(rank.getInputUserId());
                    rankresponse.setInputTime(rank.getInputTime());
                    rankresponse.setInputOrgId(rank.getInputOrgId());
                    rankresponse.setUpdateUserId(rank.getUpdateUserId());
                    rankresponse.setUpdateTime(rank.getUpdateTime());
                    rankresponse.setUpdateOrgId(rank.getUpdateOrgId());
                    ranCatalogLists.add(rankresponse);
                }
                
            }
            response.setRankStandardCatalogLists(ranCatalogLists);
            return response;
            
    }

    /**
     * 
     * Description: 职级标准列表多记录保存
     *
     * @param rankStandardCatalogListSaveReq
     * @return response
     * @see
     */
    @Override
    public void rankStandardCatalogListSave(@Valid RankStandardCatalogListSaveReq rankStandardCatalogListSaveReq) {
        rankStandardCatalogListSaveAction(rankStandardCatalogListSaveReq.getRankStandardCatalogLists());
    }

    /**
     * 
     * Description: 职级标准列表多记录保存
     *
     * @param rankStandardCatalogLists
     * @return 
     * @see
     */
    @Transactional
    public void rankStandardCatalogListSaveAction(List<RankStandardCatalogList> rankStandardCatalogLists) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (rankStandardCatalogLists != null) {
            for (RankStandardCatalogList rankStandardCatalogListTmp : rankStandardCatalogLists) {}
        }
        bomanager.updateDB();
    }

    /**
     * 
     * Description: 职级标准列表删除
     *
     * @param rankStandardCatalogListDeleteReq
     * @return 
     * @see
     */
    @Override
    @Transactional
    public void rankStandardCatalogListDelete(@Valid RankStandardCatalogListDeleteReq rankStandardCatalogListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class,
            rankStandardCatalogListDeleteReq.getSerialNo());
        bomanager.deleteBusinessObject(rankStandardCatalog);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();
    }
}
