package com.amarsoft.app.ems.system.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.addexport.AddExportReq;
import com.amarsoft.app.ems.system.cs.dto.exportquery.ExportQueryReq;
import com.amarsoft.app.ems.system.cs.dto.exportquery.ExportQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.finishexport.FinishExportReq;
import com.amarsoft.app.ems.system.cs.dto.getexportserialno.GetExportSerialNoRsp;


/**
 * 用户导出服务
 * @author hzhang23
 */
public interface UserExportService {
    /**
     * 新增用户导出记录
     * @param req
     */
    ResponseMessage<Object> addExport(RequestMessage<AddExportReq> reqMsg);
    /**
     * 更新用户导出记录
     * @param req
     */
    ResponseMessage<Object> finishExport(RequestMessage<FinishExportReq> reqMsg);
    /**
     * 获取用户导出编号
     * @param req
     */
    ResponseMessage<GetExportSerialNoRsp> getExportSerialNo();
    
    /**
     * 查询导出记录
     * @param req
     */
    ExportQueryRsp exportQuery(ExportQueryReq req);
    
    /**
     * 根据导出记录的uri下载
     * @param request
     * @param response
     */
    void downloadByUri(HttpServletRequest request, HttpServletResponse response);
    
}
