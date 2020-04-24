package com.amarsoft.app.ems.system.service;

import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchReq;
import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchRsp;
import com.amarsoft.app.ems.system.cs.dto.dbkeysingle.DbKeySingleReq;
import com.amarsoft.app.ems.system.cs.dto.dbkeysingle.DbKeySingleRsp;

/**
 * 获取流水的处理接口
 * @author cyji
 */
public interface DBKeyService {
   /**
    * 获取单个流水号
    */
    DbKeySingleRsp getSingleSerialNo(DbKeySingleReq request);
    
    /**
     *获取批次流水号
     */ 
    DbKeyBatchRsp getBatchSerialNos(DbKeyBatchReq request);
}