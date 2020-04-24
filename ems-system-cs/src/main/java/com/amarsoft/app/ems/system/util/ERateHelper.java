package com.amarsoft.app.ems.system.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectCache;
import com.amarsoft.app.ems.system.cs.client.ERateClient;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfo;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfoEfficientQueryReq;


/**
 * 取相对汇率
 * @author xjzhao
 *
 */
public class ERateHelper {
    private static ERateClient erateClient = SpringHelper.getBean(ERateClient.class);
    //汇率Cache
    private static BusinessObjectCache eRateCache=new BusinessObjectCache(100);
    
    public static void clear() 
    {
        eRateCache = new BusinessObjectCache(100);
    }
    
    /**
     * 取两个币种之间的相对汇率
     * @param fromCurrency
     * @param toCurrency
     * @return
     */
    public static double getERate(String fromCurrency,String toCurrency) {
        double dFromERateValue = 0.0;
        double dToERateValue = 0.0;
        double dCompareERate = 0.0;
        if(StringUtils.isEmpty(fromCurrency)) throw new ALSException("902010");
        if(StringUtils.isEmpty(toCurrency)) throw new ALSException("902011");
        if( fromCurrency.equals(toCurrency) ) return 1.00 ;
        //取需转换的币种汇率
        if(Currency.CNY.id.equals(fromCurrency)){
            dFromERateValue=1.0;
        }else{
            dFromERateValue=getExchangeValue(fromCurrency);
        }
        //取转换目标币种汇率
        if(Currency.CNY.id.equals(toCurrency)){
            dToERateValue=1.0;
        }else{
            dToERateValue=getExchangeValue(toCurrency);
        }
        //获取相对汇率
        dCompareERate=dFromERateValue/dToERateValue;
        return dCompareERate;
    }
    
    /**
     * 获取币种转成人民币最新汇率
     * @param currency
     * @return
     */
    public static double getConvertToCNYERate(String currency) {
        return getERate(currency,Currency.CNY.id);
    }
    
    /**
     * 获取币种换算成人民币后汇率
     * @param currency
     * @return
     */
    private static double getExchangeValue(String currency) {
        double dERateValue=0.0;
        String cacheKey="Currency="+currency;
        List<ErateInfo> eRateList = (List<ErateInfo>)eRateCache.getCacheObject(cacheKey);
        if (eRateList == null || eRateList.isEmpty()){
            ErateInfoEfficientQueryReq request = new ErateInfoEfficientQueryReq();
            request.setCurrency(currency);
            List<ErateInfo> erateList = erateClient.erateInfoEfficientQuery(new RequestMessage<ErateInfoEfficientQueryReq>(request)).getBody().getMessage().getErateInfos();
            //防止eRateList为空，则无法add新的对象
            eRateList = new ArrayList<ErateInfo>();
            
            //将ErateRes转换为BusinessObject后加入到缓存中
            for(ErateInfo erate : erateList) {    
                eRateList.add(erate);
            }
            if (eRateList == null || eRateList.isEmpty()) {
                throw new ALSException("902012", currency);
            }
            eRateCache.setCache(cacheKey, eRateList);
        }
        dERateValue = eRateList.get(0).getExchangeValue();       
        return dERateValue;
    }
}

