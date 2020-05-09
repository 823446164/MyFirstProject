/*
 * 文件名：RankStandardCatalogListControllerImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import com.amarsoft.app.ems.parameter.template.controller.RankStandardCatalogListController;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.parameter.template.service.impl.RankStandardCatalogListServiceImpl;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogSonQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogSonQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListDeleteReq;

/**
 * 〈职级标准列表Controller实现类〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
@Slf4j
@RestController
public class RankStandardCatalogListControllerImpl implements RankStandardCatalogListController {
   
    /**
     * 引入service实现层
     */
    @Autowired
    RankStandardCatalogListService rankStandardCatalogListServiceImpl;

    /**
     * 
     * Description: 职级标准列表查询
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    @TemplateExport(name = "职级标准列表", query = RankStandardCatalogListServiceImpl.RankStandardCatalogListReqQuery.class, convert = RankStandardCatalogListServiceImpl.RankStandardCatalogListRspConvert.class)
    public ResponseEntity<ResponseMessage<RankStandardCatalogListQueryRsp>> rankStandardCatalogListQuery(@RequestBody @Valid RequestMessage<RankStandardCatalogListQueryReq> reqMsg) {
        ResponseMessage<RankStandardCatalogListQueryRsp> rspMsg = null;
        try {
            RankStandardCatalogListQueryReq request = reqMsg.getMessage();

            RankStandardCatalogListQueryRsp response = rankStandardCatalogListServiceImpl.rankStandardCatalogListQuery(request);
            rspMsg = new ResponseMessage<RankStandardCatalogListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<RankStandardCatalogListQueryRsp>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("职级标准列表查询：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
            return new ResponseEntity<ResponseMessage<RankStandardCatalogListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 
     * Description: 职级标准列表保存
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogListSave(@RequestBody @Valid RequestMessage<RankStandardCatalogListSaveReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
            RankStandardCatalogListSaveReq request = reqMsg.getMessage();

            rankStandardCatalogListServiceImpl.rankStandardCatalogListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("职级标准列表保存：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 
     * Description: 职级标准列表删除
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogListDelete(@RequestBody @Valid RequestMessage<RankStandardCatalogListDeleteReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
            RankStandardCatalogListDeleteReq request = reqMsg.getMessage();

            rankStandardCatalogListServiceImpl.rankStandardCatalogListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("职级标准列表删除：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 
     * Description: 查询子职级列表
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<RankStandardCatalogSonQueryRsq>> rankStandardCatalogSonQuery(@RequestBody @Valid RequestMessage<RankStandardCatalogSonQueryReq> reqMsg) {
        ResponseMessage<RankStandardCatalogSonQueryRsq> rspMsg = null;
        try {
            RankStandardCatalogSonQueryReq request = reqMsg.getMessage();
            RankStandardCatalogSonQueryRsq response = rankStandardCatalogListServiceImpl.rankStandardCatalogSonQuery(request);
            rspMsg = new ResponseMessage<RankStandardCatalogSonQueryRsq>(response);

            return new ResponseEntity<ResponseMessage<RankStandardCatalogSonQueryRsq>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("职级标准列表查询：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
            return new ResponseEntity<ResponseMessage<RankStandardCatalogSonQueryRsq>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 
     * Description: 查询管理开发职级列表
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<RankStandardCatalogListQueryRsp>> ranStandardCatalogManagerQuery(@RequestBody @Valid RequestMessage<RankStandardCatalogListQueryReq> reqMsg) {
        ResponseMessage<RankStandardCatalogListQueryRsp> rspMsg = null;
        try {
            RankStandardCatalogListQueryReq request = reqMsg.getMessage();
            RankStandardCatalogListQueryRsp response = rankStandardCatalogListServiceImpl.ranStandardCatalogManagerQuery(request);
            rspMsg = new ResponseMessage<RankStandardCatalogListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<RankStandardCatalogListQueryRsp>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("职级标准列表查询：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
            return new ResponseEntity<ResponseMessage<RankStandardCatalogListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 
     * Description: 展示团队列表
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<TeamQueryRsp>> teamQuery(@RequestBody @Valid RequestMessage<TeamQueryReq> reqMsg){
        ResponseMessage<TeamQueryRsp> rspMsg = null;
        try {
            TeamQueryReq request = reqMsg.getMessage();
            TeamQueryRsp response = rankStandardCatalogListServiceImpl.rankStandardCatalogTeamQuery(request);
            rspMsg = new ResponseMessage<TeamQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<TeamQueryRsp>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("展示团队列表：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
            return new ResponseEntity<ResponseMessage<TeamQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
