package com.amarsoft.app.ems.system.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.aecd.acct.constant.RateFloatType;
import com.amarsoft.aecd.acct.constant.RateUnit;
import com.amarsoft.aecd.acct.constant.TermUnit;
import com.amarsoft.aecd.common.constant.ScaleType;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectCache;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.amps.arpe.util.NumberHelper;
import com.amarsoft.app.ems.system.cs.client.RateClient;
import com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfo;
import com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfoAllQueryReq;

/**
 * 该类为利率获取匹配类
 * @author xjzhao
 *
 */
public class RateHelper{
    private static RateClient rateClient = SpringHelper.getBean(RateClient.class);
    
    private static BusinessObjectCache rateCache=new BusinessObjectCache(1000);
    
    /**
     * 清理缓存
     */
    public static void clear() 
    {
        rateCache = new BusinessObjectCache(1000);
    }
    
    /**
     * 判断系统存在基于基础利率类型、币种、生效日期有效的利率
     * @param baseRateType 
     * @param currency
     * @param effectDate
     * @return true 存在，false 不存在
     */
    public static boolean exists(String baseRateType,String currency,String effectDate) {
        if(StringUtils.isEmpty(baseRateType)) return false;
        
        List<RateInfo> baseRateList =  getBaseRateList(baseRateType,currency,effectDate);
        if(baseRateList.isEmpty()) return false;
        else return true;
    }
    
    /**
     * 将一种利率单位的利率转换为另一种利率单位的利率，如年利率转换为日利率，日利率转换为年利率等
     * @param yearDays 年基准天数
     * @param rateUnit 原利率单位
     * @param rate 原利率
     * @param newRateUnit 新利率单位
     * @return 转换后的利率值
     *  
     */
    public static double getRate(int yearDays, String rateUnit, double rate, String newRateUnit) {
        if (rateUnit.equals(newRateUnit)){
            return rate;
        }

        if (RateUnit.Year.id.equals(rateUnit) && RateUnit.Month.id.equals(newRateUnit)) {
            return rate / 100 / 12 * 1000;// 月利率，千分比
        } else if (RateUnit.Year.id.equals(rateUnit) && RateUnit.Day.id.equals(newRateUnit)) {
            return rate / 100 / yearDays * 10000;// 日利率，万分比
        } else if (RateUnit.Month.id.equals(rateUnit) && RateUnit.Year.id.equals(newRateUnit)) {
            return rate / 1000 * 12 * 100;// 年利率，百分比
        } else if (RateUnit.Month.id.equals(rateUnit) && RateUnit.Day.id.equals(newRateUnit)) {
            return rate / 1000 * 12 / yearDays * 10000;// 日利率，万分比
        } else if (RateUnit.Day.id.equals(rateUnit) && RateUnit.Month.id.equals(newRateUnit)) {
            return rate / 10000 * yearDays / 12 * 1000;// 月利率，千分比
        } else if (RateUnit.Day.id.equals(rateUnit) && RateUnit.Year.id.equals(newRateUnit)) {
            return rate / 10000 * yearDays * 100;// 年利率，百分比
        } else {
            throw new ALSException("902024",RateUnit.Year.id+"\\"+RateUnit.Month.id+"\\"+RateUnit.Day.id,rateUnit);
        }
    }
    
    /**
     * 根据币种、基准利率类型、期限类型、期限、生效日期获取利率档次
     * @param Currency 币种 参见代码Currency
     * @param baseRateType 利率类型：参见代码BaseRateType
     * @param termUnit
     * @param term
     * @param effectDate
     * @return 基准利率档次
     */
    public static String getBaseRateGrade(String currency, String baseRateType, String termUnit, int term, String effectDate) {

        List<RateInfo> rateList = getBaseRateList(baseRateType, currency, effectDate);
        if (CollectionUtils.isEmpty(rateList)) {
            throw new ALSException("902025",baseRateType);
        }

        // 获取基准利率档次
        int size = rateList.size();
        for (int i = 0; i < size; i++) {
            RateInfo rateInfo = rateList.get(i);
            if (TermUnit.Year.id.equals(termUnit)) {
                term = term * 12;
                termUnit = TermUnit.Month.id;
            }

            int configTerm = rateInfo.getTerm();
            String configTermUnit = rateInfo.getTermUnit();
            if (term <= configTerm && termUnit.equals(configTermUnit)) {
                if (i < size - 1 && term > rateList.get(i + 1).getTerm()) {
                    return configTerm + "@" + configTermUnit;
                } else if (i == rateList.size() - 1) {
                    return configTerm + "@" + configTermUnit;
                }
            }
        }
        return "@";
    }
    
    /**
     * 计算基准利率
     * @param loan
     * @param rateSegment
     * @return 基准利率
     */
    public static double getBaseRate(BusinessObject loan, BusinessObject rateSegment) {
        double baseRate = 0d;
        String baseRateType = rateSegment.getString("BaseRateType");
        if (StringUtils.isEmpty(baseRateType)) return 0d;
        String rateUnit = rateSegment.getString("RateUnit");
        String putoutDate = loan.getString("PutoutDate");
        String MaturityDate = loan.getString("MaturityDate");
        String baseRateGrade = rateSegment.getString("BaseRateGrade");
        if (baseRateGrade == null || "".equals(baseRateGrade) || baseRateGrade.split("@").length < 2) {
            baseRateGrade = RateHelper.getBaseRateGrade(loan.getString("Currency"), baseRateType, putoutDate,
                    MaturityDate, loan.getString("BusinessDate"));
            rateSegment.setAttributeValue("BaseRateGrade", baseRateGrade);
        }

        int term = Integer.valueOf(baseRateGrade.split("@")[0]);
        String termUnit = baseRateGrade.split("@")[1];
        baseRate = RateHelper.getBaseRate(loan.getString("Currency"), rateSegment.getInt("YearBaseDay"), baseRateType,
                rateUnit, termUnit, term, loan.getString("BusinessDate"));
        return baseRate;
    }
    
    /**
     * 获取贷款利率
     * @param loan
     * @param rateSegment
     * @return 贷款利率
     * 
     */
    public static double getBusinessRate(BusinessObject loan, BusinessObject rateSegment) {
        // 如果是固定模式,则执行利率不变
        // String rateMode = rateSegment.getString("RateMode");
        String rateFloatType = rateSegment.getString("RateFloatType");
        double baseRate = rateSegment.getDouble("BaseRate");
        double rateFloat = rateSegment.getDouble("rateFloat");
        double businessRate = rateSegment.getDouble("BusinessRate");

        if (baseRate == 0d) {
            return businessRate;
        } else {
            if (RateFloatType.Ratio.id.equals(rateFloatType)) {
                businessRate = baseRate + baseRate * rateFloat * 0.01;
            }
            // 当利率浮动类型为基点时,执行利率 = 基准利率+浮动幅度
            else if (RateFloatType.Point.id.equals(rateFloatType)) {
                businessRate = baseRate + rateFloat / 100.0;
            } else {
                throw new ALSException("902026",rateFloatType);
            }
            if (businessRate < 0d) {
                throw new ALSException("902027",String.valueOf(businessRate));
            }
            return NumberHelper.round(businessRate, ScaleType.Rate.scale);
        }
    }
    
    /**
     * @param Currency
     *            币种 参见代码Currency
     * @param baseRateType
     *            利率类型：参见代码BaseRateType
     * @param putOutDate
     *            发放日期
     * @param maturityDate
     *            到期日
     * @param effectDate
     *                   生效日期
     * @return 利率档次
     */
    public static String getBaseRateGrade(String currency, String baseRateType,
            String putoutDate, String maturityDate, String effectDate){
        int term = (int)Math.ceil(DateHelper.getMonths(putoutDate, maturityDate));// 向上取整
        return RateHelper.getBaseRateGrade(currency, baseRateType,
                TermUnit.Month.id, term, effectDate);
    }
    
    /**
     * @param Currency
     *            币种：参见代码Currency
     * @param YearDays
     *            年基准天数 英式币种 365 其他币种 360，这里传入主要是解决存量贷款的特殊要求
     * @param BaseRateType
     *            利率类型：参见代码BaseRateType
     * @param RateUnit
     *            利率单位：参见代码RateUnit
     * @param putOutDate
     *            发放日期
     * @param maturityDate
     *            到期日
     * @param effectDate
     *                    生效日期
     * @return 返回对应利率单位的利率值
     */
    public static double getBaseRate(String currency, int yearDays,
            String baseRateType, String RateUnit, String putoutDate,
            String maturityDate, String effectDate) {
        int term = (int)Math.ceil(DateHelper.getMonths(putoutDate, maturityDate));//向上取整
        return RateHelper.getBaseRate(currency, yearDays, baseRateType,
                RateUnit, TermUnit.Month.id, term, effectDate);
    }

    /**
     * @param Currency
     *            币种：参见代码Currency
     * @param YearDays
     *            年基准天数 英式币种 365 其他币种 360，这里传入主要是解决存量贷款的特殊要求
     * @param BaseRateType
     *            利率类型：参见代码BaseRateType
     * @param RateUnit
     *            利率单位：参见代码RateUnit
     * @param TermUnit
     *            期限单位：参见代码TermUnit
     * @param Term
     *            期限
     * @param effectDate
     *                    生效日期
     * @return 返回对应利率单位的利率值
     * 
     */
    public static double getBaseRate(String currency, int yearDays,
            String baseRateType, String RateUnit, String termUnit, int term,
            String effectDate) {
        double baseRate = 0.0;
        List<RateInfo> rateList = getBaseRateList(baseRateType, currency,
                effectDate);
        if (CollectionUtils.isEmpty(rateList)) {
            throw new ALSException("902028",baseRateType);
        }
        // 计算基准利率
        for (int i = 0; i < rateList.size(); i++) {
            RateInfo rateInfo = rateList.get(i);
            if (TermUnit.Year.id.equals(termUnit)) {        //转换年份周期单位为月份
                term = term * 12;
                termUnit = TermUnit.Month.id;
            }
            // 判断期限是否在利率参数内，从大到小取最接近老期限范围的利率信息
            if (term <= rateInfo.getTerm() && termUnit.equals(rateInfo.getTermUnit())) {
                if (i < rateList.size() - 1) {
                    if (term == rateInfo.getTerm()
                            || term > rateList.get(i + 1).getTerm()) {
                        baseRate = rateInfo.getRate();
                        // 进行利率单位转换
                        baseRate = RateHelper.getRate(yearDays,
                                rateInfo.getRateUnit(), baseRate,
                                RateUnit);
                        if (baseRate > 0d) {
                            break;
                        }
                    }
                } else if (i == rateList.size() - 1) {
                    baseRate = rateInfo.getRate();
                    // 进行利率单位转换
                    baseRate = RateHelper.getRate(yearDays,
                            rateInfo.getRateUnit(), baseRate,
                            RateUnit);
                    if (baseRate > 0d) {
                        break;
                    }
                }
            }
        }
        if (baseRate <= 0) {
            throw new ALSException("902028",baseRateType);
        }
        return NumberHelper.round(baseRate,ScaleType.Rate.scale);
    }
    
    /**
     * 根据生效日期取利率配置
     * @param baseRateType
     * @param currency
     * @param effectDate
     * @return 利率信息
     */
    private static List<RateInfo> getBaseRateList(String baseRateType, String currency, String effectDate) {
        
        String cacheKey="RateType="+baseRateType+",Currency="+currency+",effectDate="+effectDate;
        List<RateInfo> rateListCache = (List<RateInfo>)rateCache.getCacheObject(cacheKey);
        if (CollectionUtils.isEmpty(rateListCache)){
            RateInfoAllQueryReq request = new RateInfoAllQueryReq();
            request.setStatus(SystemStatus.Normal.id);//有效的利率
            List<RateInfo> rateResList = rateClient.rateInfoAllQuery(new RequestMessage<RateInfoAllQueryReq>(request)).getBody().getMessage().getRateInfos();//服务端已经做好排序
            if(!CollectionUtils.isEmpty(rateResList)) {
                List<RateInfo> rateList = new ArrayList<RateInfo>();
                for (RateInfo rate : rateResList) {
                    if (rate.getTermUnit().equals(TermUnit.Year.id))        //将年份转换为月份
                    {
                        rate.setTerm(rate.getTerm() * 12);
                        rate.setTermUnit(TermUnit.Month.id);
                    }
                    
                    if(rate.getRateType().equals(baseRateType)
                            && rate.getCurrency().equals(currency)) {//先取满足利率类型和币种两个条件的
                        rateList.add(rate);
                    }
                }
                
                String maxEfficientDate = "";
                for(RateInfo rate : rateList) {//获取最大日期，小于等于传入的日期
                    if(maxEfficientDate.compareTo(rate.getEfficientDate()) < 0 
                            && rate.getEfficientDate().compareTo(effectDate) <= 0){
                        maxEfficientDate = rate.getEfficientDate();
                    }
                }
                
                rateListCache = new ArrayList<RateInfo>();
                //根据最大日期进行获取
                for(RateInfo rate : rateList) {//获取最大日期
                    if(maxEfficientDate.equals(rate.getEfficientDate())){
                        rateListCache.add(rate);
                    }
                }
                rateCache.setCache(cacheKey, rateListCache);
            }
        }
        return rateListCache;
    }
}

