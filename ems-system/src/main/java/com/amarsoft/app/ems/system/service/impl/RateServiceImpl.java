package com.amarsoft.app.ems.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.acct.constant.BaseRateType;
import com.amarsoft.aecd.acct.constant.RateUnit;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.aecd.common.constant.Status;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.system.cs.dto.efficientdatequery.EfficientDate;
import com.amarsoft.app.ems.system.cs.dto.efficientdatequery.EfficientDateQueryReq;
import com.amarsoft.app.ems.system.cs.dto.efficientdatequery.EfficientDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfoadd.RateInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfodelete.RateInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoefficientquery.RateInfoEfficientQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoefficientquery.RateInfoEfficientQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfoquery.RateInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoquery.RateInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfoupdate.RateInfoUpdateReq;
import com.amarsoft.app.ems.system.entity.RateInfo;
import com.amarsoft.app.ems.system.service.RateService;

/**
 * 利率服务的接口实现类
 * @author hzhang23
 *
 */
@Service
public class RateServiceImpl implements RateService {

    /**
     * 获取指定利率的信息
     * @param request
     * @return 利率信息
     */
    @Override
    public RateInfoQueryRsp getRateInfo(RateInfoQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RateInfo> rateInfoList = bomanager.loadBusinessObjects(RateInfo.class, "RateId=:RateId and Status=:Status", "RateId", request.getRateId(), "Status", Status.Valid.id);
        
        if(CollectionUtils.isEmpty(rateInfoList)) return null;
        RateInfo rateInfo = rateInfoList.get(0);
        RateInfoQueryRsp response = new RateInfoQueryRsp();
        
        //设置参数的值
        response.setRateId(rateInfo.getRateId());
        response.setRateType(rateInfo.getRateType());
        response.setCurrency(rateInfo.getCurrency());
        response.setEfficientDate(rateInfo.getEfficientDate());
        response.setTermUnit(rateInfo.getTermUnit());
        response.setTerm(rateInfo.getTerm().intValue());
        response.setRate(rateInfo.getRate().doubleValue());
        response.setStatus(rateInfo.getStatus());
        response.setRemark(rateInfo.getRemark());
        response.setRateUnit(rateInfo.getRateUnit());
                    
        return response;
    }

    /**
     * 根据生效日期取利率配置
     * 如果不上传参数，默认获取人民币5条利率
     * @param request
     * @return 利率信息
     */
    @Override
    public RateInfoEfficientQueryRsp getRateInfoEfficientDate(RateInfoEfficientQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager(); 
        List<RateInfo> rateInfoList = null;
        if(StringUtils.isEmpty(request.getRateType()) || StringUtils.isEmpty(request.getCurrency()) || StringUtils.isEmpty(request.getEfficientDate())) {//输入值不全的时候使用默认的利率展示
            rateInfoList = bomanager.loadBusinessObjects(RateInfo.class,0,5,"rateType = :rateType and currency = :currency and status =:status order by efficientDate desc",
                                                    "rateType",BaseRateType.HomeCurrency._010.id,"currency",Currency.CNY.id,"status",Status.Valid.id).getBusinessObjects();
        }else {
            rateInfoList = bomanager.loadBusinessObjects(RateInfo.class, 0, Integer.MAX_VALUE, "rateType = :rateType and currency = :currency and efficientDate =:efficientDate and status =:status order by rateid",
                                                    "rateType",request.getRateType(),"currency",request.getCurrency(),"efficientDate",request.getEfficientDate(),"status",request.getStatus())
                                                    .getBusinessObjects();
        }
        RateInfoEfficientQueryRsp response= new RateInfoEfficientQueryRsp();
        response.setRateInfos(new ArrayList<com.amarsoft.app.ems.system.cs.dto.rateinfoefficientquery.RateInfo>());
        if(!CollectionUtils.isEmpty(rateInfoList)) {
            for(RateInfo rateInfo : rateInfoList) {
                com.amarsoft.app.ems.system.cs.dto.rateinfoefficientquery.RateInfo rateResponse = new com.amarsoft.app.ems.system.cs.dto.rateinfoefficientquery.RateInfo();
                
                //设置参数的值
                rateResponse.setRateId(rateInfo.getRateId());
                rateResponse.setRateType(rateInfo.getRateType());
                rateResponse.setCurrency(rateInfo.getCurrency());
                rateResponse.setEfficientDate(rateInfo.getEfficientDate());
                rateResponse.setTermUnit(rateInfo.getTermUnit());
                rateResponse.setTerm(rateInfo.getTerm().intValue());
                rateResponse.setRate(rateInfo.getRate().doubleValue());
                rateResponse.setStatus(rateInfo.getStatus());
                rateResponse.setRemark(rateInfo.getRemark());
                rateResponse.setRateUnit(rateInfo.getRateUnit());
                response.getRateInfos().add(rateResponse);
            }
        }
        return response;
    }
    
    /**
     * 获取指定状态的所有利率的信息
     * @param request
     * @return 利率信息
     */
    @Override
    public RateInfoAllQueryRsp getRateAll(RateInfoAllQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RateInfo> rateInfoList = bomanager.loadBusinessObjects(RateInfo.class, 0, Integer.MAX_VALUE, "status=:status order by efficientDate desc, currency, rateType, termUnit desc, term desc", "status", request.getStatus()).getBusinessObjects();
        
        RateInfoAllQueryRsp response = new RateInfoAllQueryRsp();    
        List<com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfo> rateInfos = new ArrayList<com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfo>();
        if(!CollectionUtils.isEmpty(rateInfoList)) {
            for(RateInfo rateInfo : rateInfoList) {
                com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfo rateResponse = new com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfo();
                
                //设置参数的值
                rateResponse.setRateId(rateInfo.getRateId());
                rateResponse.setRateType(rateInfo.getRateType());
                rateResponse.setCurrency(rateInfo.getCurrency());
                rateResponse.setEfficientDate(rateInfo.getEfficientDate());
                rateResponse.setTermUnit(rateInfo.getTermUnit());
                rateResponse.setTerm(rateInfo.getTerm().intValue());
                rateResponse.setRate(rateInfo.getRate().doubleValue());
                rateResponse.setStatus(rateInfo.getStatus());
                rateResponse.setRemark(rateInfo.getRemark());
                rateResponse.setRateUnit(rateInfo.getRateUnit());    
                rateInfos.add(rateResponse);
            }
        }
        response.setRateInfos(rateInfos);
        return response;
    }

    /**
     * 修改利率的信息
     * @param request
     */
    @Override
    public void setRateInfo(RateInfoUpdateReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RateInfo> rateInfos = bomanager.loadBusinessObjects(RateInfo.class, "rateType = :rateType and currency = :currency and efficientDate = :efficientDate", 
                "rateType", request.getRateType(),"currency",request.getCurrency(),"efficientDate",request.getEfficientDate());

        if(CollectionUtils.isEmpty(rateInfos)) 
            throw new ALSException("900407");
        for (int i = 0; i < rateInfos.size(); i++) {
            com.amarsoft.app.ems.system.cs.dto.rateinfoupdate.RateInfo rate = request.getRateInfos().get(i);
            RateInfo rateInfo = rateInfos.get(i);
            rateInfo.setTermUnit(StringUtils.isEmpty(rate.getTermUnit())?rateInfo.getTermUnit():rate.getTermUnit());
            rateInfo.setTerm(rate.getTerm() == null || rate.getTerm() == 0?rateInfo.getTerm():rate.getTerm());
            rateInfo.setRateUnit(RateUnit.Year.id);
            rateInfo.setRate(new BigDecimal(rate.getRate()));
            rateInfo.setStatus(StringUtils.isEmpty(rate.getStatus())?rateInfo.getStatus():rate.getStatus());
            rateInfo.setRemark(StringUtils.isEmpty(rate.getRemark())?rateInfo.getRemark():rate.getRemark());
            
            bomanager.updateBusinessObject(rateInfo);
        }
        //更新到数据库
        bomanager.updateDB();
    }

    /**
     * 新增利率的信息
     * @param request
     */
    @Override
    public void addRateInfo(RateInfoAddReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(CollectionUtils.isEmpty(req.getRateInfos()))
            throw new ALSException("900403");
        List<RateInfo> rateInfos = new ArrayList<RateInfo>();
        req.getRateInfos().forEach(rate ->{
            RateInfo rateInfo = new RateInfo();
            rateInfo.generateKey();
            rateInfo.setRateType(rate.getRateType());
            rateInfo.setCurrency(rate.getCurrency());
            rateInfo.setEfficientDate(rate.getEfficientDate());
            rateInfo.setTermUnit(rate.getTermUnit());
            rateInfo.setTerm(rate.getTerm());
            rateInfo.setRateUnit(rate.getRateUnit());
            rateInfo.setRate(new BigDecimal(rate.getRate()));
            rateInfo.setStatus(StringUtils.isEmpty(rate.getStatus())?Status.Valid.id:rate.getStatus());
            rateInfos.add(rateInfo);
        });
        bomanager.updateBusinessObjects(rateInfos);
        bomanager.updateDB();
    }

    /**
     * 删除利率的信息
     * @param request
     */
    @Override
    public void deleteRateInfo(RateInfoDeleteReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        List<RateInfo> rateInfoList = bomanager.loadBusinessObjects(RateInfo.class, "rateType= :rateType and currency =:currency and efficientDate = :efficientDate", "rateType", request.getRateType(),"currency",request.getCurrency(),"efficientDate",request.getEfficientDate());
                
        if(CollectionUtils.isEmpty(rateInfoList))
            throw new ALSException("900406");
        bomanager.deleteBusinessObjects(rateInfoList);
        bomanager.updateDB();
    }

    @Override
    public EfficientDateQueryRsp efficientDateQeury(EfficientDateQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<BusinessObject> rateList  = bomanager.selectBusinessObjectsBySql("select distinct efficientDate from RateInfo where rateType = :rateType and currency = :currency","rateType",req.getRateType(),"currency",req.getCurrency()).getBusinessObjects();
        EfficientDateQueryRsp rsp = new EfficientDateQueryRsp();
        rsp.setEfficientDates(new ArrayList<EfficientDate>());
        for (BusinessObject rateInfo : rateList) {
            EfficientDate date = new EfficientDate();
            date.setEfficientDate(rateInfo.getString("EfficientDate"));
            rsp.getEfficientDates().add(date);
        }
        return rsp;
    }
    
}
