package com.amarsoft.app.ems.system.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.avts.export.client.ExportClient;
import com.amarsoft.app.ems.system.cs.client.UserExportClient;
import com.amarsoft.app.ems.system.cs.dto.addexport.AddExportReq;
import com.amarsoft.app.ems.system.cs.dto.finishexport.FinishExportReq;

@Component
@ConditionalOnClass(ExportClient.class)
public class DefaultExportClient implements ExportClient {

    @Autowired
    private UserExportClient userExportClient;
    
    @Override
    public String getExportNo() {
        //获取文件全局唯一标示，需要调用用户相关服务
        return userExportClient.getExportSerialNo().getBody().getMessage().getSerialNo();
    }

    @Override
    public void addExport(String exportNo, String userId, String fileName) {
        //调用ASMS用户相关服务存储导出文件记录
        AddExportReq addExportReq = new AddExportReq();
        addExportReq.setSerialNo(exportNo);
        addExportReq.setUserId(userId);
        addExportReq.setFileName(fileName);
        userExportClient.addExport(new RequestMessage<AddExportReq>(addExportReq));
    }

    @Override
    public void finishExport(String exportNo, String status, String uri, String log) {
        FinishExportReq finishExportReq = new FinishExportReq();
        finishExportReq.setSerialNo(exportNo);
        finishExportReq.setStatus(status);
        finishExportReq.setUri(uri);
        finishExportReq.setLog(log);
        userExportClient.finishExport(new RequestMessage<FinishExportReq>(finishExportReq));
    }

}
