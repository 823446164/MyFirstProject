package com.amarsoft.app.ems.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.cs.dto.erateinfoadd.ErateInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfodelete.ErateInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfoEfficientQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfoEfficientQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfoquery.ErateInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoquery.ErateInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfoupdate.ErateInfoUpdateReq;
import com.amarsoft.app.ems.system.entity.ErateInfo;
import com.amarsoft.app.ems.system.service.ErateService;

/**
 * 汇率服务的处理类
 * @author xxu1
 */
@Service
public class ErateServiceImpl  implements ErateService{
    @Override
    public ErateInfoQueryRsp getErateInfo(ErateInfoQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        ErateInfoQueryRsp response = new ErateInfoQueryRsp();
        List<ErateInfo> erateInfoList = bomanager.loadBusinessObjects(ErateInfo.class, "EfficientDate=:EfficientDate and Currency=:Currency", "EfficientDate", request.getEfficientDate(), "Currency", request.getCurrency());
        
        if(!CollectionUtils.isEmpty(erateInfoList)) {
            ErateInfo erateInfo = erateInfoList.get(0);
            
            //设置参数的值
            response.setExchangeValue(erateInfo.getExchangeValue().doubleValue());
            response.setCurrency(erateInfo.getCurrency());
            response.setEfficientDate(erateInfo.getEfficientDate());
            response.setPrice(erateInfo.getPrice().doubleValue());
            response.setRemark(erateInfo.getRemark());
            response.setUnit(erateInfo.getUnit());
        }
        
        return response;
    }

    @Override
    public ErateInfoAllQueryRsp getErateAll(ErateInfoAllQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<ErateInfo> erateInfoList = null;
        BusinessObjectAggregate<ErateInfo> erateInfoAggregate = null;
        ErateInfoAllQueryRsp rsp = new ErateInfoAllQueryRsp();
        rsp.setErateInfos(new ArrayList<com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfo>()); 
        if(StringUtils.isEmpty(request.getCurrency()) || StringUtils.isEmpty(request.getEfficientDate())) {//请求为空则查询所有
            erateInfoAggregate = bomanager.loadBusinessObjects(ErateInfo.class, 0, Integer.MAX_VALUE, "1 = 1 order by efficientDate desc");
            erateInfoList = erateInfoAggregate.getBusinessObjects();
        }else {//不为空则按条件查询
            erateInfoAggregate = bomanager.loadBusinessObjects(ErateInfo.class, 0, Integer.MAX_VALUE,
                                "currency = :currency and efficientDate = :efficientDate order by efficientDate desc", "currency",request.getCurrency(),"efficientDate",request.getEfficientDate());
            erateInfoList = erateInfoAggregate.getBusinessObjects();
        }
        if(!CollectionUtils.isEmpty(erateInfoList)) {
            erateInfoList.forEach(erateInfo ->{
                com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfo e = new com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfo();
                
                //设置参数的值
                e.setCurrency(erateInfo.getCurrency());
                e.setEfficientDate(erateInfo.getEfficientDate());
                e.setPrice(erateInfo.getPrice().doubleValue());
                e.setRemark(erateInfo.getRemark());
                e.setUnit(erateInfo.getUnit());
                e.setExchangeValue(erateInfo.getExchangeValue().doubleValue());
                if (rsp.getErateInfos().stream().anyMatch(r -> r.getCurrency().equals(erateInfo.getCurrency()))) {
                    return;
                }
                rsp.getErateInfos().add(e);
            });
        }
        rsp.setTotalCount(rsp.getErateInfos().size());
        return rsp;
    }
    
    @Override
    public ErateInfoEfficientQueryRsp getErateEfficient(ErateInfoEfficientQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        String currency = request.getCurrency();
        List<ErateInfo> erateInfoList = bomanager.loadBusinessObjects(ErateInfo.class, 0, Integer.MAX_VALUE, " Currency = :Currency "
                + " and EfficientDate = (select max(RI.efficientDate) from " + ClassUtils.getShortName(ErateInfo.class) + " RI where RI.currency = :Currency and RI.efficientDate <= :efficientDate)", "Currency", currency, "efficientDate", DateHelper.getBusinessDate()).getBusinessObjects();
        ErateInfoEfficientQueryRsp response = new ErateInfoEfficientQueryRsp();
        response.setErateInfos(new ArrayList<>());
        if(!CollectionUtils.isEmpty(erateInfoList)) {
            for(ErateInfo erateInfo : erateInfoList) {
                com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfo erateResponse = new com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfo();
                
                //设置参数的值 
                erateResponse.setExchangeValue(erateInfo.getExchangeValue().doubleValue());
                erateResponse.setCurrency(erateInfo.getCurrency());
                erateResponse.setEfficientDate(erateInfo.getEfficientDate());
                erateResponse.setPrice(erateInfo.getPrice().doubleValue());
                erateResponse.setRemark(erateInfo.getRemark());
                erateResponse.setUnit(erateInfo.getUnit());
                response.getErateInfos().add(erateResponse);
            }
        }
        return response;
    }

    @Override
    public void setErateInfo(ErateInfoUpdateReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String efficientDate = request.getEfficientDate();
        String currency = request.getCurrency();
        List<ErateInfo> erateInfoList = bomanager.loadBusinessObjects(ErateInfo.class, "EfficientDate=:EfficientDate and Currency=:Currency", "EfficientDate", efficientDate, "Currency", currency);
        
        if(CollectionUtils.isEmpty(erateInfoList)) 
            throw new ALSException("900607");
        ErateInfo erateInfo = erateInfoList.get(0);
        
        //设置参数的值    
        erateInfo.setExchangeValue(new BigDecimal(request.getExchangeValue()));
        erateInfo.setCurrency(request.getCurrency());
        erateInfo.setEfficientDate(request.getEfficientDate());
        erateInfo.setPrice(new BigDecimal(request.getPrice().doubleValue()));
        erateInfo.setRemark(request.getRemark());
        erateInfo.setUnit(request.getUnit());
        
        //更新到数据库
        bomanager.updateBusinessObject(erateInfo);
        bomanager.updateDB();
    }

    @Override
    public void addErateInfo(ErateInfoAddReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String efficientDate = request.getEfficientDate();
        String currency = request.getCurrency();
        List<ErateInfo> erateInfoList = bomanager.loadBusinessObjects(ErateInfo.class, "EfficientDate=:EfficientDate and Currency=:Currency", "EfficientDate", efficientDate, "Currency", currency);
        
        if(!CollectionUtils.isEmpty(erateInfoList)) 
            throw new ALSException("900604");
        ErateInfo erateInfo = new ErateInfo();
        //设置参数的值
        erateInfo.setExchangeValue(new BigDecimal(request.getExchangeValue()));
        erateInfo.setCurrency(request.getCurrency());
        erateInfo.setEfficientDate(request.getEfficientDate());
        erateInfo.setPrice(new BigDecimal(request.getPrice()));
        erateInfo.setRemark(StringUtils.isEmpty(request.getRemark()) ? "" : request.getRemark());
        erateInfo.setUnit(request.getUnit());
        
        //更新到数据库
        bomanager.updateBusinessObject(erateInfo);
        bomanager.updateDB();
    }

    @Override
    public void deleteErateInfo(ErateInfoDeleteReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String efficientDate = request.getEfficientDate();
        String currency = request.getCurrency();
        List<ErateInfo> erateInfoList = bomanager.loadBusinessObjects(ErateInfo.class, "EfficientDate=:EfficientDate and Currency=:Currency", "EfficientDate", efficientDate, "Currency", currency);
                
        if(CollectionUtils.isEmpty(erateInfoList)) 
            throw new ALSException("900606");
        ErateInfo erateInfo = erateInfoList.get(0);
        bomanager.deleteBusinessObject(erateInfo);
        bomanager.updateDB();
    }
}
