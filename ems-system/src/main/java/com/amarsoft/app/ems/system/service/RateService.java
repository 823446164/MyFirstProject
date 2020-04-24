package com.amarsoft.app.ems.system.service;

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

/**
 * 利率服务的接口
 * @author xxu1
 */
public interface RateService {
    
    /**
     * 获取指定的利率的信息
     * @param request
     * @return
     */
    RateInfoQueryRsp getRateInfo(RateInfoQueryReq request);

    /**
     * 根据生效日期取利率配置
     * @param request
     * @return
     */
    RateInfoEfficientQueryRsp getRateInfoEfficientDate(RateInfoEfficientQueryReq request);
    
    /**
     * 获取所有利率的信息
     * @param request
     * @return
     */
    RateInfoAllQueryRsp getRateAll(RateInfoAllQueryReq request);
    
    /**
     * 修改利率的信息
     * @param request
     */
    void setRateInfo(RateInfoUpdateReq request);

    /**
     * 新增利率的信息
     * @param request
     * @return
     */
    void addRateInfo(RateInfoAddReq request);
    

    /**
     * 删除利率的信息
     * @param request
     * @return
     */
    void deleteRateInfo(RateInfoDeleteReq request);
    
    /**
     * 查询生效日
     * @param request
     * @return
     */
    EfficientDateQueryRsp efficientDateQeury(EfficientDateQueryReq efficientDateQueryReq);
}
