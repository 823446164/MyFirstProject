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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.parameter.constant.ChildRankNo;
import com.amarsoft.aecd.parameter.constant.ManaRankName;
import com.amarsoft.aecd.parameter.constant.ManaRankStandard;
import com.amarsoft.aecd.parameter.constant.RankName;
import com.amarsoft.aecd.parameter.constant.RankStandard;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.acsc.util.DTOHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.parameter.entity.RankStandardCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogList;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogChildQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogChildQueryRsq;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogListService;
import com.amarsoft.app.ems.parameter.template.util.ParameterHelper;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;

import lombok.extern.slf4j.Slf4j;


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
     * 〈查询职等结果集〉
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
            String sql =null;
            String rankStandard=rankStandardCatalogListQueryReq.getRankStandard();
            String search=RankStandard.getIdByName(rankStandard);
               if(StringUtils.isEmpty(rankStandard)) {
                   sql = "select RSC.serialNo as serialNo,RSC.rankStandard as rankStandard,RSC.rankName as rankName,RSC.parentRankNo as parentRankNo,RSC.ability as ability,RSC.rankDescribe as rankDescribe,RSC.responeDescribe as responeDescribe,"
                       + "RSC.abilityDescribe as abilityDescribe,RSC.belongTeam as belongTeam,RSC.rankType as rankType,RSC.inputUserId as inputUserId,RSC.inputTime as inputTime,RSC.inputOrgId as inputOrgId,RSC.updateUserId as updateUserId,"
                       + "RSC.updateTime as updateTime,RSC.updateOrgId as updateOrgId" + " from RANK_STANDARD_CATALOG RSC"
                       + " where 1=1  and RSC.rankType=:rankType and RSC.belongTeam= :belongTeam and RSC.parentRankNo is null ";
          return queryProperties.assembleSql(sql, "belongTeam", rankStandardCatalogListQueryReq.getBelongTeam(), "rankType",
              rankStandardCatalogListQueryReq.getRankType());
               }else {
             sql = "select RSC.serialNo as serialNo,RSC.rankStandard as rankStandard,RSC.rankName as rankName,RSC.parentRankNo as parentRankNo,RSC.ability as ability,RSC.rankDescribe as rankDescribe,RSC.responeDescribe as responeDescribe,"
                         + "RSC.abilityDescribe as abilityDescribe,RSC.belongTeam as belongTeam,RSC.rankType as rankType,RSC.inputUserId as inputUserId,RSC.inputTime as inputTime,RSC.inputOrgId as inputOrgId,RSC.updateUserId as updateUserId,"
                         + "RSC.updateTime as updateTime,RSC.updateOrgId as updateOrgId" + " from RANK_STANDARD_CATALOG RSC"
                         + " where 1=1  and RSC.rankType=:rankType and RSC.belongTeam= :belongTeam and RSC.rankStandard like :search and RSC.parentRankNo is null ";
            return queryProperties.assembleSql(sql, "belongTeam", rankStandardCatalogListQueryReq.getBelongTeam(), "rankType",
                rankStandardCatalogListQueryReq.getRankType(),"search","%"+search+"%");
               }
        }
    }

    /**
     * 〈查询到的职等列表数据转换为响应实体〉
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
            // 查询到的数据转换为响应实体
            temp.setSerialNo(bo.getString("SerialNo"));
            temp.setRankStandard(RankStandard.getNameById(bo.getString("RankStandard")));
            temp.setRankName(RankName.getNameById(bo.getString("RankName")));
            temp.setParentRankNo(bo.getString("ParentRankNo"));
            temp.setRankDescribe(bo.getString("RankDescribe"));
            temp.setResponeDescribe(bo.getString("ResponeDescribe"));
            temp.setAbilityDescribe(bo.getString("AbilityDescribe"));
            temp.setBelongTeam(bo.getString("BelongTeam"));
            temp.setRankType(bo.getString("RankType"));
            temp.setInputUserId(bo.getString("InputUserId"));
            temp.setInputTime(bo.getString("InputTime"));
            temp.setInputOrgId(bo.getString("InputOrgId"));
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
        //为开发管理职等分别传入对应职等职级的枚举类
        //1.职等枚举类用于a.查询条件 b.新增职等控制对应的职等下拉框
        //2.职级的枚举类用于新增职等时反显对应的职级
        List<Map<String,String>> rankStadardList=new ArrayList<Map<String,String>>();
        List<Map<String,String>> rankNameList=new ArrayList<Map<String,String>>();
        List<Map<String,String>> manaRankStadardList=new ArrayList<Map<String,String>>();
        List<Map<String,String>> manaRankNameList=new ArrayList<Map<String,String>>();
        Map<String,String> map1=null;
        Map<String,String> map2=null;
        for (RankStandard rankStandard : RankStandard.values()) {
            map1=new HashMap<String, String>();
            map1.put("value", rankStandard.id);
            map1.put("text", rankStandard.name);
            rankStadardList.add(map1);
        }
        for (RankName rankName : RankName.values()) {
            map2=new HashMap<String, String>();
            map2.put("value", rankName.id);
            map2.put("text", rankName.name);
            rankNameList.add(map2);
        }
        for (ManaRankStandard rankStandard : ManaRankStandard.values()) {
            map1=new HashMap<String, String>();
            map1.put("value", rankStandard.id);
            map1.put("text", rankStandard.name);
            manaRankStadardList.add(map1);
        }
        for (ManaRankName rankName : ManaRankName.values()) {
            map2=new HashMap<String, String>();
            map2.put("value", rankName.id);
            map2.put("text", rankName.name);
            manaRankNameList.add(map2);
        }
        rankStandardCatalogListQueryRsp.setRankNameList(rankNameList);
        rankStandardCatalogListQueryRsp.setRankStandardList(rankStadardList);
        rankStandardCatalogListQueryRsp.setManaRankStandardList(manaRankStadardList);
        rankStandardCatalogListQueryRsp.setManaRankNameList(manaRankNameList);
        if (null != businessObjectList && !businessObjectList.isEmpty()) {
            List<RankStandardCatalogList> rankStandardCatalogLists = new ArrayList<RankStandardCatalogList>();
            for (BusinessObject bo : businessObjectList) {
                // 查询到的数据转换为响应实体
                rankStandardCatalogLists.add(convert.apply(bo));
            }
            rankStandardCatalogListQueryRsp.setRankStandardCatalogLists(rankStandardCatalogLists);
        }
        rankStandardCatalogListQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return rankStandardCatalogListQueryRsp;
    }

    /**
     * 
     * Description: 子职级标准列表多记录查询
     *
     * @param rankStandardCatalogSonQueryReq
     * 1.输入职等的serialNo,查询出职等及对应的子职级
     * @return response
     * @see
     */
    @Override
    @Transactional
    public RankStandardCatalogChildQueryRsq rankStandardCatalogChildQuery(@Valid RankStandardCatalogChildQueryReq rankStandardCatalogChildQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 1.获取职级List对象
        List<RankStandardCatalog> ranCatalogs = bomanager.loadBusinessObjects(RankStandardCatalog.class,
            "parentRankNo=:parentRankNo or serialNo=:parentRankNo order by parentRankNo", "parentRankNo",
            rankStandardCatalogChildQueryReq.getSerialNo());
        // 2.定义响应实体类对象
        RankStandardCatalogChildQueryRsq response = new RankStandardCatalogChildQueryRsq();
        // 2.获取职级列表的List对象
        List<RankStandardCatalogList> ranCatalogLists = new ArrayList<RankStandardCatalogList>();
        if (!CollectionUtils.isEmpty(ranCatalogs)) {
            // 3.获取单个职级列表对象
            RankStandardCatalogList rankresponse = null;
            // 4.循环遍历获取到的实体类List,插入response
            for (RankStandardCatalog rank : ranCatalogs) {
                rankresponse = new RankStandardCatalogList();
                rankresponse.setSerialNo(rank.getSerialNo());
                rankresponse.setRankStandard(RankStandard.getNameById(rank.getRankStandard()));
                rankresponse.setRankName(RankName.getNameById(rank.getRankName()));
                rankresponse.setChildRankNo(ChildRankNo.getNameById(rank.getChildRankNo()));
                rankresponse.setAbility(rank.getAbility());
                rankresponse.setRankDescribe(rank.getRankDescribe());
                rankresponse.setResponeDescribe(rank.getResponeDescribe());
                rankresponse.setAbilityDescribe(rank.getAbilityDescribe());
                rankresponse.setBelongTeam(rank.getBelongTeam());
                rankresponse.setRankType(rank.getRankType());
                rankresponse.setInputUserId(rank.getInputUserId());
                rankresponse.setInputTime(rank.getInputTime());
                rankresponse.setInputOrgId(rank.getInputOrgId());
                ranCatalogLists.add(rankresponse);

            }

        }
        response.setRankStandardCatalogLists(ranCatalogLists);
        response.setTotalCount(response.getRankStandardCatalogLists().size());
        return response;

    }

    /**
     * 
     * Description: 团队记录查询
     *
     * @param teamQueryRep
     * @return 
     * @see
     */
    @Override
    @Transactional
    public TeamQueryRsp rankStandardCatalogTeamQuery(@Valid TeamQueryReq teamQueryRep) {
        TeamQueryRsp response = new TeamQueryRsp();
        // 执行Helper类的服务调用方法
        List<TeamInfo> teamInfos = ParameterHelper.getTeamList();
        response.setTeamInfos(teamInfos);
        response.setTotalCount(response.getTeamInfos().size());
        return response;
    }
}
