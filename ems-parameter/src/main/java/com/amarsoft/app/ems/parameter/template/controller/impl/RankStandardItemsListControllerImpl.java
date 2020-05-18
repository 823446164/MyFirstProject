/*
 * 文件名：RankStandardItemsListControllerImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月13日
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
import com.amarsoft.app.ems.parameter.template.controller.RankStandardItemsListController;
import com.amarsoft.app.ems.parameter.template.service.RankStandardItemsListService;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsInfoDeleteReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListSaveReq;

/**
 * 〈职级指标Controller实现类〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
@Slf4j
@RestController
public class RankStandardItemsListControllerImpl implements RankStandardItemsListController {
    @Autowired
    RankStandardItemsListService rankStandardItemsInfoServiceImpl;
    
    /**
     * 
     * Description: 职级指标查询
     *
     * @param reqMsg
     * @return 
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<RankStandardItemsListQueryRsp>> rankStandardItemsInfoQuery(@RequestBody @Valid RequestMessage<RankStandardItemsListQueryReq> reqMsg){
        ResponseMessage<RankStandardItemsListQueryRsp> rspMsg = null;
        try {
            RankStandardItemsListQueryReq request = reqMsg.getMessage();
            
            RankStandardItemsListQueryRsp response = rankStandardItemsInfoServiceImpl.rankStandardItemsListQuery(request);
            rspMsg = new ResponseMessage<RankStandardItemsListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<RankStandardItemsListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("职级指标查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xphe  //补充异常代码。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<RankStandardItemsListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 
     * Description: 职级指标保存
     *
     * @param reqMsg
     * @return 
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rankStandardItemsListSave(@RequestBody @Valid RequestMessage<RankStandardItemsListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            RankStandardItemsListSaveReq request = reqMsg.getMessage();
            rankStandardItemsInfoServiceImpl.rankStandardItemsListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("职级指标保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xphe  //补充异常代码。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 
     * Description: 职级指标list界面的更新保存
     *
     * @param reqMsg
     * @return 
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<TreeLabelQueryRsp>> rankStandardItemsInfoListQuery(@RequestBody @Valid RequestMessage<TreeLabelQueryReq> reqMsg){
        ResponseMessage<TreeLabelQueryRsp> rspMsg = null;
        try {
            TreeLabelQueryReq request = reqMsg.getMessage();
            
            TreeLabelQueryRsp response = rankStandardItemsInfoServiceImpl.treeLabelListQuery(request);
            rspMsg = new ResponseMessage<TreeLabelQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<TreeLabelQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
         // TODO　xphe //标签查询设置异常码
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<TreeLabelQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 
     * Description: 职级指标选择界面的保存
     *
     * @param reqMsg
     * @return 
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rankStandardItemsInfoLabelSave(@RequestBody @Valid RequestMessage<TreeLabelSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            TreeLabelSaveReq request = reqMsg.getMessage();
            
            rankStandardItemsInfoServiceImpl.rankLabelTreeSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("职级指标保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
         // TODO　xphe //标签保存设置异常码
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 
     * Description: 职级指标页面的删除
     *
     * @param reqMsg
     * @return 
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rankStandardItemsInfoDelete(@RequestBody @Valid RequestMessage<RankStandardItemsInfoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            RankStandardItemsInfoDeleteReq request = reqMsg.getMessage();
            rankStandardItemsInfoServiceImpl.rankStandardDelete(request);
            rspMsg = new ResponseMessage<Object>();
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("职级指标删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
         // TODO　xphe //标签删除设置异常码
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
