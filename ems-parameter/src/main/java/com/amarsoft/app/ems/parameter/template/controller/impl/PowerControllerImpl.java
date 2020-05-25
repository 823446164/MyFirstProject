/*
 * 文件名：PowerToLabelControllerImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.parameter.template.controller.PowerController;
import com.amarsoft.app.ems.parameter.template.cs.dto.powertolabel.PowerToLableQueryRsp;
import com.amarsoft.app.ems.parameter.template.service.PowerControlService;

/**
 * 判断当前用户是否有权限对标签进行维护的实现层
 * @author yrong
 * @version 2020年5月22日
 * @see PowerControllerImpl
 * @since
 */
@RestController
public class PowerControllerImpl implements PowerController {
    @Autowired
    PowerControlService powerControlService;
    
    @Override
    public ResponseEntity<ResponseMessage<PowerToLableQueryRsp>>  powerToLabel() {
        ResponseMessage<PowerToLableQueryRsp> rspMsg = null;
        PowerToLableQueryRsp rsp = powerControlService.powerToLabel();
            rspMsg = new ResponseMessage<PowerToLableQueryRsp>(rsp);
            return new ResponseEntity<ResponseMessage<PowerToLableQueryRsp>>(rspMsg , HttpStatus.OK);
    }  
}
