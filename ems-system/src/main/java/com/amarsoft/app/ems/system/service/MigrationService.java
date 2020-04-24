package com.amarsoft.app.ems.system.service;

import org.springframework.http.HttpHeaders;

import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.executemigration.ExecuteMigrationReq;
import com.amarsoft.app.ems.system.cs.dto.migrationcheckquery.MigrationCheckQueryReq;
import com.amarsoft.app.ems.system.cs.dto.migrationcheckquery.MigrationCheckQueryRsp;

/**
 * 用户迁移服务的处理接口
 * @author hzhang23
 */
public interface MigrationService {
    /**
     * 查询用户迁移状态
     * @param user from http request
     * @param contentType
     * @param message
     * @return
     */
    ResponseMessage<MigrationCheckQueryRsp> migrationCheckQuery(String user, String contentType, MigrationCheckQueryReq req);
    /**
     * 用户迁移
     * @param user from http request
     * @param contentType
     * @param message
     * @return
     */
    ResponseMessage<Object> executeMigration(String user, String contentType, ExecuteMigrationReq message,HttpHeaders header);
    
}