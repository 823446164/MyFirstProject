/*
 * 文件名：RankStandardCatalogInfoServiceImpl.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.parameter.constant.ChildRankNo;
import com.amarsoft.aecd.parameter.constant.ManaRankName;
import com.amarsoft.aecd.parameter.constant.RankName;
import com.amarsoft.aecd.parameter.constant.RankStandard;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeRankApplyInfoDtoClient;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateRsp;
import com.amarsoft.app.ems.parameter.entity.RankStandardCatalog;
import com.amarsoft.app.ems.parameter.entity.RankStandardItems;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfo;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 〈职级标准详情Service实现类〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
@Slf4j
@Service
public class RankStandardCatalogInfoServiceImpl implements RankStandardCatalogInfoService {
        
    @Autowired
    EmployeeRankApplyInfoDtoClient employeeRankApplyInfoDtoClient;
    /**
     * 
     * Description: 职级/子职级标准详情查询
     * @param rankStandardCatalogSonInfoQueryReq
     * @return　RankStandardCatalogSonInfoQueryRsq
     * @see
     */
    @Override
    @Transactional
    public RankStandardCatalogChildInfoQueryRsq rankStandardCatalogChildInfoQuery(@Valid RankStandardCatalogChildInfoQueryReq rankStandardCatalogChildInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        RankStandardCatalogChildInfoQueryRsq rankInfo = new RankStandardCatalogChildInfoQueryRsq();
        RankStandardCatalog rankStandardCatalog = bomanager.loadBusinessObject(RankStandardCatalog.class, "serialNo",
            rankStandardCatalogChildInfoQueryReq.getSerialNo());
        BusinessObjectAggregate<BusinessObject> count = bomanager.selectBusinessObjectsBySql(
            "select count(1) as cnt from RankStandardCatalog where parentRankNo=:serialNo", "serialNo",
            rankStandardCatalogChildInfoQueryReq.getSerialNo());
        int childCount = count.getBusinessObjects().get(0).getInt("cnt");
        RankStandardCatalogChildInfo rankStandardCatalogInfo = new RankStandardCatalogChildInfo();
        String rankStandard = rankStandardCatalogChildInfoQueryReq.getRankStandard();
        List<Map<String, String>> rankList = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        Map<String, String> map3 = new HashMap<String, String>();
        // TODO xphe 优化代码－改为code_library传list
        if (!StringUtils.isEmpty(rankStandard)) {
            if (RankStandard._1.name.equals(rankStandard)) {
                map1.put("value", ChildRankNo.B1_1.id);
                map1.put("text", ChildRankNo.B1_1.name);
                rankList.add(map1);
                map2.put("value", ChildRankNo.B1_2.id);
                map2.put("text", ChildRankNo.B1_2.name);
                rankList.add(map2);
                map3.put("value", ChildRankNo.B1_3.id);
                map3.put("text", ChildRankNo.B1_3.name);
                rankList.add(map3);
            }
            if (RankStandard._2.name.equals(rankStandard)) {
                map1.put("value", ChildRankNo.B2_1.id);
                map1.put("text", ChildRankNo.B2_1.name);
                rankList.add(map1);
                map2.put("value", ChildRankNo.B2_2.id);
                map2.put("text", ChildRankNo.B2_2.name);
                rankList.add(map2);
                map3.put("value", ChildRankNo.B2_3.id);
                map3.put("text", ChildRankNo.B2_3.name);
                rankList.add(map3);
            }
            if (RankStandard._3.name.equals(rankStandard)) {
                map1.put("value", ChildRankNo.B3_1.id);
                map1.put("text", ChildRankNo.B3_1.name);
                rankList.add(map1);
                map2.put("value", ChildRankNo.B3_2.id);
                map2.put("text", ChildRankNo.B3_2.name);
                rankList.add(map2);
                map3.put("value", ChildRankNo.B3_3.id);
                map3.put("text", ChildRankNo.B3_3.name);
                rankList.add(map3);
            }
            if (RankStandard._4.name.equals(rankStandard)) {
                map1.put("value", ChildRankNo.B4_1.id);
                map1.put("text", ChildRankNo.B4_1.name);
                rankList.add(map1);
                map2.put("value", ChildRankNo.B4_2.id);
                map2.put("text", ChildRankNo.B4_2.name);
                rankList.add(map2);
                map3.put("value", ChildRankNo.B4_3.id);
                map3.put("text", ChildRankNo.B4_3.name);
                rankList.add(map3);
            }
            if (RankStandard._5.name.equals(rankStandard)) {
                map1.put("value", ChildRankNo.B5_1.id);
                map1.put("text", ChildRankNo.B5_1.name);
                rankList.add(map1);
                map2.put("value", ChildRankNo.B5_2.id);
                map2.put("text", ChildRankNo.B5_2.name);
                rankList.add(map2);
                map3.put("value", ChildRankNo.B5_3.id);
                map3.put("text", ChildRankNo.B5_3.name);
                rankList.add(map3);
            }
        }
        if (rankStandardCatalog != null) {
            rankStandardCatalogInfo.setSerialNo(rankStandardCatalogChildInfoQueryReq.getSerialNo());
            rankStandardCatalogInfo.setBelongTeam(rankStandardCatalog.getBelongTeam());
            rankStandardCatalogInfo.setRankType(rankStandardCatalog.getRankType());
            rankStandardCatalogInfo.setRankStandard(RankStandard.getNameById(rankStandardCatalog.getRankStandard()));
            if ("1".equals(rankStandardCatalog.getRankType())) {
                rankStandardCatalogInfo.setRankName(RankName.getNameById(rankStandardCatalog.getRankName()));
            }
            else {
                rankStandardCatalogInfo.setRankName(ManaRankName.getNameById(rankStandardCatalog.getRankName()));
            }
            rankStandardCatalogInfo.setChildRankNo(rankStandardCatalog.getChildRankNo());
            rankStandardCatalogInfo.setRankDescribe(rankStandardCatalog.getRankDescribe());
            rankStandardCatalogInfo.setResponeDescribe(rankStandardCatalog.getResponeDescribe());
            rankStandardCatalogInfo.setAbilityDescribe(rankStandardCatalog.getAbilityDescribe());
            rankStandardCatalogInfo.setAbility(rankStandardCatalog.getAbility());
            rankInfo.setInfo(rankStandardCatalogInfo);
            rankInfo.setList(rankList);
            rankInfo.setChildCount(childCount);
            //如果是子职级，则需要传当前处于审批的个数
            if(!StringUtils.isEmpty(rankStandardCatalog.getParentRankNo())) {
                RankDeleteValidateReq rankDeleteValidateReq=new RankDeleteValidateReq();
                rankDeleteValidateReq.setRankNo(rankStandardCatalogChildInfoQueryReq.getSerialNo());
                RequestMessage<RankDeleteValidateReq> reqMsg = new RequestMessage<RankDeleteValidateReq>();
                reqMsg.setMessage(rankDeleteValidateReq);
                ResponseEntity<ResponseMessage<RankDeleteValidateRsp>> employeeRankApplyInfoExist = employeeRankApplyInfoDtoClient.employeeRankApplyInfoExist(reqMsg);
                RankDeleteValidateRsp message = employeeRankApplyInfoExist.getBody().getMessage();
                rankInfo.setFlowCount(message.getRecordCount());
            }
            return rankInfo;
        }
        else {
            rankInfo.setInfo(rankStandardCatalogInfo);
            rankInfo.setList(rankList);
            return rankInfo;
        }

    }

    /**
     * 
     * Description: 职级/子职级标准详情保存
     * @param rankStandardCatalogChildInfoSaveReq
     * @return　map
     * @see
     */
    @Override
    @Transactional
    public Map<String, String> rankStandardCatalogChildInfoSave(@Valid RankStandardCatalogChildInfoSaveReq rankStandardCatalogChildInfoSaveReq) {
        // 定义业务对象管理容器
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (rankStandardCatalogChildInfoSaveReq != null) {
            // 为界面传过来的职级枚举类转换id
            String rankNamaId = null;
            if (StringUtils.isEmpty(RankName.getIdByName(rankStandardCatalogChildInfoSaveReq.getRankName()))) {
                rankNamaId = ManaRankName.getIdByName(rankStandardCatalogChildInfoSaveReq.getRankName());
            }
            else {
                rankNamaId = RankName.getIdByName(rankStandardCatalogChildInfoSaveReq.getRankName());
            }
            // 根据主键获取实体类信息
            RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class,
                rankStandardCatalogChildInfoSaveReq.getSerialNo());
            // 空判断
            if (rankStandardCatalog == null) {
                // 新增职等/职级
                rankStandardCatalog = new RankStandardCatalog();
                rankStandardCatalog.generateKey();
                String childRankNo = rankStandardCatalogChildInfoSaveReq.getChildRankNo();
                String rankStandard = rankStandardCatalogChildInfoSaveReq.getRankStandard();
                String rankName = rankStandardCatalogChildInfoSaveReq.getRankName();
                if (!StringUtils.isEmpty(childRankNo)) {
                    // 子职级保存校验－重复性校验
                    if (childSaveValidate(childRankNo, RankStandard.getIdByName(rankStandard),
                        rankStandardCatalogChildInfoSaveReq.getParentRankNo()) == false) {
                        throw new ALSException("EMS2002", ChildRankNo.getNameById(childRankNo), rankStandard);
                    }
                    rankStandardCatalog.setRankStandard(RankStandard.getIdByName(rankStandardCatalogChildInfoSaveReq.getRankStandard()));
                }
                else {
                    // 职等保存校验－重复性校验
                    if (saveValidate(rankNamaId, rankStandard,
                        rankStandardCatalogChildInfoSaveReq.getBelongTeam(), rankStandardCatalogChildInfoSaveReq.getRankType()) == false) {
                        throw new ALSException("EMS2001", rankName, RankStandard.getNameById((rankStandard)));
                    }
                    rankStandardCatalog.setRankStandard(rankStandardCatalogChildInfoSaveReq.getRankStandard());
                }
                rankStandardCatalog.setInputOrgId(GlobalShareContextHolder.getOrgId());
                rankStandardCatalog.setParentRankNo(rankStandardCatalogChildInfoSaveReq.getParentRankNo());
                rankStandardCatalog.setRankName(rankNamaId);
                rankStandardCatalog.setRankDescribe(rankStandardCatalogChildInfoSaveReq.getRankDescribe());
                rankStandardCatalog.setAbilityDescribe(rankStandardCatalogChildInfoSaveReq.getAbilityDescribe());
                rankStandardCatalog.setResponeDescribe(rankStandardCatalogChildInfoSaveReq.getResponeDescribe());
                rankStandardCatalog.setChildRankNo(rankStandardCatalogChildInfoSaveReq.getChildRankNo());
                rankStandardCatalog.setAbility(rankStandardCatalogChildInfoSaveReq.getAbility());
                rankStandardCatalog.setBelongTeam(rankStandardCatalogChildInfoSaveReq.getBelongTeam());
                rankStandardCatalog.setRankType(rankStandardCatalogChildInfoSaveReq.getRankType());
            }
            else {
                // 更新下的职等/职级信息
                rankStandardCatalog.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                if (!StringUtils.isEmpty(rankStandardCatalogChildInfoSaveReq.getChildRankNo())) {
                    // 子职级保存校验－重复性校验
                    if (childSaveValidate(rankStandardCatalogChildInfoSaveReq.getChildRankNo(),
                        RankStandard.getIdByName(rankStandardCatalogChildInfoSaveReq.getRankStandard()),
                        rankStandardCatalogChildInfoSaveReq.getParentRankNo()) == false) {
                        throw new ALSException("EMS2002", ChildRankNo.getNameById(rankStandardCatalogChildInfoSaveReq.getChildRankNo()),
                            rankStandardCatalogChildInfoSaveReq.getRankStandard());
                    }
                    // 子职级下的更新
                    rankStandardCatalog.setRankStandard(RankStandard.getIdByName(rankStandardCatalogChildInfoSaveReq.getRankStandard()));
                }
                else {
                    // 职等保存校验－重复性校验
                    if (saveValidate(rankNamaId, rankStandardCatalogChildInfoSaveReq.getRankStandard(),
                        rankStandardCatalogChildInfoSaveReq.getBelongTeam(), rankStandardCatalogChildInfoSaveReq.getRankType()) == false) {
                        throw new ALSException("EMS2001", rankStandardCatalogChildInfoSaveReq.getRankName(),
                            RankStandard.getNameById(rankStandardCatalogChildInfoSaveReq.getRankStandard()));
                    }
                    rankStandardCatalog.setRankStandard(rankStandardCatalogChildInfoSaveReq.getRankStandard());
                }
                rankStandardCatalog.setRankName(rankNamaId);
                rankStandardCatalog.setRankDescribe(rankStandardCatalogChildInfoSaveReq.getRankDescribe());
                rankStandardCatalog.setAbilityDescribe(rankStandardCatalogChildInfoSaveReq.getAbilityDescribe());
                rankStandardCatalog.setResponeDescribe(rankStandardCatalogChildInfoSaveReq.getResponeDescribe());
                rankStandardCatalog.setChildRankNo(rankStandardCatalogChildInfoSaveReq.getChildRankNo());
                rankStandardCatalog.setAbility(rankStandardCatalogChildInfoSaveReq.getAbility());
            }
            bomanager.updateBusinessObject(rankStandardCatalog);
        }
        // 事务提交
        bomanager.updateDB();
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "Y");
        return map;

    }

    /**
     * 
     * Description: 校验职级＋职等是否在系统中已经存在
             * 1、存在即不可新增
             * 2、不存在即新增成功
     * @param rankName
     * @param rankStandard
     * @return　boolean
     * @see
     */
    public boolean saveValidate(String rankName, String rankStandard, String belongTeam, String rankType) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RankStandardCatalog> rankStandardCatalogs = new ArrayList<RankStandardCatalog>();
        rankStandardCatalogs = bomanager.loadBusinessObjects(RankStandardCatalog.class,
            "rankName=:rankName and rankStandard=:rankStandard and belongTeam=:belongTeam and rankType=:rankType", "rankName", rankName,
            "rankStandard", rankStandard, "belongTeam", belongTeam, "rankType", rankType);
        if (rankStandardCatalogs.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 
     * Description: 校验子职级是否在系统中已经存在
             * 1、存在即不可新增
             * 2、不存在即新增成功
     * @param rankName
     * @param rankStandard
     * @return　boolean
     * @see
     */
    public boolean childSaveValidate(String childRankNo, String rankStandard, String parentNo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RankStandardCatalog> rankStandardCatalogs = new ArrayList<RankStandardCatalog>();
        rankStandardCatalogs = bomanager.loadBusinessObjects(RankStandardCatalog.class,
            "childRankNo=:childRankNo and rankStandard=:rankStandard and parentRankNo=:parentNo", "childRankNo", childRankNo,
            "rankStandard", rankStandard, "parentNo", parentNo);
        if (rankStandardCatalogs.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 
     * Description: 职等/子职级详情单记录删除
     * @param rankName
     * @param rankStandard
     * @return　boolean
     * @see
     */
    @Override
    @Transactional
    public void rankStandardCatalogInfoDelete(@Valid RankStandardCatalogChildInfoQueryReq rankStandardCatalogChildInfoQueryReq) {
        // 获取主表id
        String serialNo = rankStandardCatalogChildInfoQueryReq.getSerialNo();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 根据主键匹配主表信息
        RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class, serialNo);
        String parentNo=rankStandardCatalog.getParentRankNo();
        int flowcount=0;
        if (null == rankStandardCatalog) {
            if(log.isErrorEnabled()) {
                log.error(serialNo + "此职级不存在!");
            }
            throw new ALSException("EMS2003");
        }
        if(!StringUtils.isEmpty(parentNo)) {
            RankDeleteValidateReq rankDeleteValidateReq=new RankDeleteValidateReq();
            rankDeleteValidateReq.setRankNo(rankStandardCatalogChildInfoQueryReq.getSerialNo());
            RequestMessage<RankDeleteValidateReq> reqMsg = new RequestMessage<RankDeleteValidateReq>();
            reqMsg.setMessage(rankDeleteValidateReq);
            ResponseEntity<ResponseMessage<RankDeleteValidateRsp>> employeeRankApplyInfoExist = employeeRankApplyInfoDtoClient.employeeRankApplyInfoExist(reqMsg);
            RankDeleteValidateRsp message = employeeRankApplyInfoExist.getBody().getMessage();
            flowcount=message.getRecordCount();
        }

        if(flowcount==0) {
            // 删除对应职等及子职级
            bomanager.deleteObjectBySql(RankStandardCatalog.class, "serialNo=:serialNo or parentRankNo=:serialNo", "serialNo", serialNo);
            // 删除对应职级指标
            bomanager.deleteObjectBySql(RankStandardItems.class, "rankNo=:serialNo", "serialNo", serialNo);
        }else {
            throw new ALSException("EMS2035", rankStandardCatalog.getChildRankNo());
        }
        bomanager.updateDB();
    }

}
