package com.amarsoft.app.ems.system.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.addboard.AddBoardReq;
import com.amarsoft.app.ems.system.cs.dto.boardquery.BoardQueryReq;
import com.amarsoft.app.ems.system.cs.dto.boardquery.BoardQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.deleteboard.DeleteBoardReq;
import com.amarsoft.app.ems.system.cs.dto.getboardid.GetBoardIdRsp;
import com.amarsoft.app.ems.system.cs.dto.updateboard.UpdateBoardReq;

/**
 * 通告栏服务的处理接口
 * @author hzhang23
 */
public interface BoardService {
    /**
     * 新增通知栏富文本信息
     * @param bomanager
     * @param request
     * @return
     */
    void addBoard(AddBoardReq req);
    
    /**
     * 通知栏查询
     * @param bomanager
     * @param request
     * @return
     */
    BoardQueryRsp boardQuery(HttpHeaders header,BoardQueryReq req, OrgService orgService,UserService userService,RoleService roleService, GroupService groupService);
    /**
     * 更新通知栏富文本信息
     * @param bomanager
     * @param request
     * @return
     */
    void updateBoard(UpdateBoardReq req, OrgService orgService);

    /**
     * 删除通知栏富文本信息
     * @param bomanager
     * @param request
     * @return
     */
    void deleteBoard(DeleteBoardReq message);
    /**
     * 下载通知关联文件
     * @param boardId
     * @param response
     */
    void downloadDoc(String boardId, String fileIndex, HttpServletResponse response);

    /**
     * 上传关联文件
     * @param file
     * @param response
     * @return 
     */
    ResponseMessage<Object> uploadDoc(String boardId, MultipartFile file, HttpServletResponse response);
    
    /**
     * 删除下载关联文件
     * @param file
     * @param response
     */
    void deleteFileRecored(String boardId, HttpServletResponse response);

    /**
     * 获取通知编号
     * @param file
     * @param response
     */
    GetBoardIdRsp getBoardId();

    /**
     * 保存上传图片
     * @param file
     * @return uuid 保存后唯一流水
     * @throws IOException
     */
    String saveImage(MultipartFile file);

    /**
     * 下载图片
     * @param uuid
     * @param response
     */
    void downloadImage(String uuid, HttpServletResponse response);
}