package com.amarsoft.app.ems.system.service;

import com.amarsoft.app.ems.system.cs.dto.erateinfoadd.ErateInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfodelete.ErateInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfoEfficientQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfoEfficientQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfoquery.ErateInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoquery.ErateInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfoupdate.ErateInfoUpdateReq;

/**
 * 汇率服务的处理类
 * @author xxu1
 */
public interface ErateService {
    
    /**
     * 获取指定的汇率的信息
     * @param bomanager
     * @param request
     * @return
     */
    ErateInfoQueryRsp getErateInfo(ErateInfoQueryReq request);

    /**
     * 获取所有汇率的信息
     * @param request 
     * @param bomanager
     * @return
     */
    ErateInfoAllQueryRsp getErateAll(ErateInfoAllQueryReq request);
    
    /**
     * 获取指定币种有效的汇率信息
     * @param bomanager
     * @param request
     * @return
     */
    ErateInfoEfficientQueryRsp getErateEfficient(ErateInfoEfficientQueryReq request);
    
    /**
     * 修改汇率的信息
     * @param bomanager
     * @param request
     */
    void setErateInfo(ErateInfoUpdateReq request);

    /**
     * 新增汇率的信息
     * @param bomanager
     * @param request
     * @return
     */
    void addErateInfo(ErateInfoAddReq request);
    

    /**
     * 删除汇率的信息
     * @param bomanager
     * @param request
     * @return
     */
    void deleteErateInfo(ErateInfoDeleteReq request);
}
