package com.amarsoft.app.ems.system.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.aecd.system.constant.ExportStatus;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.cs.dto.addexport.AddExportReq;
import com.amarsoft.app.ems.system.cs.dto.exportquery.Export;
import com.amarsoft.app.ems.system.cs.dto.exportquery.ExportQueryReq;
import com.amarsoft.app.ems.system.cs.dto.exportquery.ExportQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.finishexport.FinishExportReq;
import com.amarsoft.app.ems.system.cs.dto.getexportserialno.GetExportSerialNoRsp;
import com.amarsoft.app.ems.system.entity.UserExport;
import com.amarsoft.app.ems.system.entity.UserExportBase;
import com.amarsoft.app.ems.system.service.UserExportService;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户导出的处理类
 * 
 * @author hzhang23
 */
@Slf4j
@Service
public class UserExportServiceImpl implements UserExportService {

    @Override
    public ResponseMessage<Object> addExport(RequestMessage<AddExportReq> reqMsg) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserExport userExport = BusinessObject.createBusinessObject(UserExport.class);

        userExport.setKey(reqMsg.getMessage().getSerialNo());
        ;
        userExport.setBeginTime(DateHelper.getBusinessLocalDateTime());
        userExport.setStatus(ExportStatus.Processing.id);

        userExport.setUserId(reqMsg.getMessage().getUserId());
        userExport.setFileName(reqMsg.getMessage().getFileName());

        bomanager.updateBusinessObject(userExport);
        bomanager.updateDB();
        return new ResponseMessage<Object>();
    }

    @Override
    public ResponseMessage<Object> finishExport(RequestMessage<FinishExportReq> reqMsg) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserExport userExport = bomanager.keyLoadBusinessObject(UserExport.class, reqMsg.getMessage().getSerialNo());
        userExport.setStatus(reqMsg.getMessage().getStatus());
        userExport.setLog(reqMsg.getMessage().getLog());
        userExport.setUri(reqMsg.getMessage().getUri());
        userExport.setFinishTime(DateHelper.getBusinessLocalDateTime());
        bomanager.updateBusinessObject(userExport);
        bomanager.updateDB();
        return new ResponseMessage<Object>();
    }

    @Override
    public ResponseMessage<GetExportSerialNoRsp> getExportSerialNo() {
        UserExport userExport = new UserExport();
        userExport.generateKey();
        ResponseMessage<GetExportSerialNoRsp> rsp = new ResponseMessage<GetExportSerialNoRsp>();
        GetExportSerialNoRsp addExportRsp = new GetExportSerialNoRsp();
        addExportRsp.setSerialNo(userExport.getKeyString());
        rsp.setMessage(addExportRsp);
        return rsp;
    }

    @Override
    public ExportQueryRsp exportQuery(ExportQueryReq req) {
        ExportQueryRsp rsp = new ExportQueryRsp();
        rsp.setExports(new ArrayList<Export>());
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<UserExportBase> exports = bomanager.loadBusinessObjectsContainHistory(UserExport.class,
                req.getBegin(), req.getPageSize(), "userId = :userId", "userId", GlobalShareContextHolder.getUserId());
        for (UserExportBase export : exports.getBusinessObjects()) {
            Export e = new Export();
            e.setSerialNo(export.getSerialNo());
            e.setBeginTime(DateHelper.format(export.getBeginTime(), FormatType.DateTimeLongFormat.format));
            e.setFinishTime(DateHelper.format(export.getFinishTime(), FormatType.DateTimeLongFormat.format));
            e.setFileName(export.getFileName());
            e.setLog(export.getLog());
            e.setStatus(export.getStatus());
            e.setUri(export.getUri());
            rsp.getExports().add(e);
        }

        rsp.setTotalCount(exports.getAggregate("count(userId) as cnt ").getInt("cnt"));
        return rsp;
    }

    @Override
    public void downloadByUri(HttpServletRequest request, HttpServletResponse response) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String serialNo = request.getParameter("serialNo");
        if (StringUtils.isEmpty(serialNo)) {
            throw new ALSException("900968");
        }
        UserExport userExport = bomanager.keyLoadBusinessObject(UserExport.class, serialNo);

         //设置response
        response.setContentType("application/octet-stream");
        response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");
        
        File file = new File(userExport.getUri());
        if (file != null && file.exists()) {
            byte[] b = new byte[1024];
            try (FileInputStream fis = new FileInputStream(file);OutputStream os = response.getOutputStream();BufferedInputStream bis = new BufferedInputStream(fis);){
                String[] filePaths = userExport.getUri().split(File.separator);
                response.addHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filePaths[filePaths.length - 1], "UTF-8"));
                int i = 0;
                while ((i= bis.read(b)) != -1) {
                    os.write(b, 0, i);
                }
            } catch (IOException e) {
                if (log.isErrorEnabled()) {
                    log.error("模板导出出错：",e);
                }
                throw new ALSException("900966", e);
            }
        }else {
            throw new ALSException("900967");
        }
    }
}