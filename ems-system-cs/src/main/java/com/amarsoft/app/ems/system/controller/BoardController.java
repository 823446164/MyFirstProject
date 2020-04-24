/**
 * 新增通告
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.addboard.AddBoardReq;
import com.amarsoft.app.ems.system.cs.dto.boardquery.BoardQueryReq;
import com.amarsoft.app.ems.system.cs.dto.boardquery.BoardQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.deleteboard.DeleteBoardReq;
import com.amarsoft.app.ems.system.cs.dto.getboardid.GetBoardIdRsp;
import com.amarsoft.app.ems.system.cs.dto.updateboard.UpdateBoardReq;

public interface BoardController {
    @PostMapping(value = "/board/addboard", name="新增通告", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addBoard(@RequestBody @Valid RequestMessage<AddBoardReq> reqMsg);
    @PostMapping(value = "/board/boardquery", name="查询通告", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<BoardQueryRsp>> boardQuery(@RequestBody @Valid RequestMessage<BoardQueryReq> reqMsg);
    @PostMapping(value = "/board/updateboard", name="更新通告", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> updateBoard(@RequestBody @Valid RequestMessage<UpdateBoardReq> reqMsg);
    @PostMapping(value = "/board/deleteboard", name="删除通告", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteBoard(@RequestBody @Valid RequestMessage<DeleteBoardReq> reqMsg);
    @PostMapping(value = "/board/getboardid", name="删除通告", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<GetBoardIdRsp>> getBoardId();
    @RequestMapping(value = "/board/fileload", name="通告文件上传下载", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> fileload(HttpServletRequest request, HttpServletResponse response, MultipartFile srouce);
}
