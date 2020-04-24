/**
 * 新增通告
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller.impl;

import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.app.ems.system.controller.BoardController;
import com.amarsoft.app.ems.system.cs.dto.addboard.AddBoardReq;
import com.amarsoft.app.ems.system.cs.dto.boardquery.BoardQueryReq;
import com.amarsoft.app.ems.system.cs.dto.boardquery.BoardQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.deleteboard.DeleteBoardReq;
import com.amarsoft.app.ems.system.cs.dto.getboardid.GetBoardIdRsp;
import com.amarsoft.app.ems.system.cs.dto.updateboard.UpdateBoardReq;
import com.amarsoft.app.ems.system.service.BoardService;
import com.amarsoft.app.ems.system.service.GroupService;
import com.amarsoft.app.ems.system.service.MenuService;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.RoleService;
import com.amarsoft.app.ems.system.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardControllerImpl implements BoardController {

    @Autowired
    BoardService boardService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    GroupService groupService;
    
    @Autowired
    OrgService orgService;
    
    @Autowired
    MenuService menuService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addBoard(@RequestBody @Valid RequestMessage<AddBoardReq> reqMsg){
        try {
            boardService.addBoard(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("新增通告请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "901201", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    @CodeQuery
    public ResponseEntity<ResponseMessage<BoardQueryRsp>> boardQuery(@RequestBody @Valid RequestMessage<BoardQueryReq> reqMsg){
        ResponseMessage<BoardQueryRsp> rspMsg = null;
        try {
            HttpHeaders header = new HttpHeaders();
            BoardQueryRsp rsp = boardService.boardQuery(header,reqMsg.getMessage(),orgService,userService,roleService,groupService);
            return new ResponseEntity<ResponseMessage<BoardQueryRsp>>(new ResponseMessage<BoardQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询通告请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901202",e.getMessage());
            return new ResponseEntity<ResponseMessage<BoardQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> updateBoard(@RequestBody @Valid RequestMessage<UpdateBoardReq> reqMsg){
        try {
            boardService.updateBoard(reqMsg.getMessage(), orgService);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("更新通告请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "901203", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteBoard(@RequestBody @Valid RequestMessage<DeleteBoardReq> reqMsg) {
        try {
            boardService.deleteBoard(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("删除通告请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "901204", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/board/fileload", name="通告文件上传下载", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> fileload(HttpServletRequest request, HttpServletResponse response, MultipartFile srouce) {
        try {
            MultipartFile file = null;
            String action = request.getParameter("action");
            String boardId = request.getParameter("boardId");
            String fileIndex = request.getParameter("fileIndex");
            String[] allActions = {"imageUpload","imageDownload","upload","download","deleteFile"};//请求类型
        
            if ((StringUtils.isEmpty(fileIndex) && StringUtils.isEmpty(boardId)) && !Stream.of(allActions).anyMatch(a -> a.equals(action))) throw new ALSException("901205");
            
            String userId = request.getHeader("User-Id");
            if (!action.equals("imageDownload")) {
                CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getServletContext());
                if (resolver.isMultipart(request) && request.getMethod().equals(HttpMethod.POST.toString())) { //Post  Multipart类型的请求
                    MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest) request;
                    file =fileRequest.getFiles("file").get(0);
                }
            }
            ResponseMessage<Object> hrb = new ResponseMessage<Object>();
            if (action.equals("imageUpload")) {
                if (StringUtils.isEmpty(userId) || file == null) {
                    if (StringUtils.isEmpty(userId)) {
                        hrb = ResponseMessage.getResponseMessageFromException(null, "901209");
                    }
                    if (file == null) {
                        hrb = ResponseMessage.getResponseMessageFromException(null, "901205");
                    }
                    return new ResponseEntity<ResponseMessage<Object>>(hrb, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                String uuid = boardService.saveImage(file);
                String url = "/asms/board/fileload?action=imageDownload&id="+uuid;
                hrb.setMessage(url);
                return new ResponseEntity<ResponseMessage<Object>>(hrb, HttpStatus.OK);
            }else if (action.equals("imageDownload")) {
                String uuid = request.getParameter("id");
                boardService.downloadImage(uuid,response);
            }else if (action.equals("download")) {
                boardService.downloadDoc(boardId,fileIndex,response);
            }else if (action.equals("upload")) {
                file = ((MultipartHttpServletRequest) request).getFiles("file").get(0);
                hrb = boardService.uploadDoc(boardId,file,response);
            }else if (action.equals("deleteFile")) {
                boardService.deleteFileRecored(fileIndex,response);
            }
            return new ResponseEntity<ResponseMessage<Object>>(hrb, HttpStatus.OK);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("通知文件操作出错：",e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "901211");
            return new ResponseEntity<ResponseMessage<Object>>(hrb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<GetBoardIdRsp>> getBoardId() {
        ResponseMessage<GetBoardIdRsp> rspMsg = null;
        try {
            GetBoardIdRsp rsp = boardService.getBoardId();
            return new ResponseEntity<ResponseMessage<GetBoardIdRsp>>(new ResponseMessage<GetBoardIdRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询通告编号出错：", e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901202",e.getMessage());
            return new ResponseEntity<ResponseMessage<GetBoardIdRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}