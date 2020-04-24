package com.amarsoft.app.ems.system.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.aecd.acct.constant.TermUnit;
import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.aecd.common.constant.Status;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.arpe.util.CleanPathUtil;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.cs.dto.addboard.AddBoardReq;
import com.amarsoft.app.ems.system.cs.dto.boardquery.Board;
import com.amarsoft.app.ems.system.cs.dto.boardquery.BoardQueryReq;
import com.amarsoft.app.ems.system.cs.dto.boardquery.BoardQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.boardquery.UploadFile;
import com.amarsoft.app.ems.system.cs.dto.deleteboard.DeleteBoardReq;
import com.amarsoft.app.ems.system.cs.dto.getboardid.GetBoardIdRsp;
import com.amarsoft.app.ems.system.cs.dto.groupallquery.GroupAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.updateboard.UpdateBoardReq;
import com.amarsoft.app.ems.system.cs.dto.usergroup.Group;
import com.amarsoft.app.ems.system.cs.dto.userquery.User;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userrole.Role;
import com.amarsoft.app.ems.system.entity.BoardGroup;
import com.amarsoft.app.ems.system.entity.BoardList;
import com.amarsoft.app.ems.system.entity.BoardRole;
import com.amarsoft.app.ems.system.entity.FileRecord;
import com.amarsoft.app.ems.system.entity.GroupInfo;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.RoleInfo;
import com.amarsoft.app.ems.system.service.BoardService;
import com.amarsoft.app.ems.system.service.GroupService;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.RoleService;
import com.amarsoft.app.ems.system.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 通知栏服务的处理类
 * @author hzhang23
 */
@Slf4j
@Service
@RefreshScope
public class BoardServiceImpl implements BoardService{
    private static final String[] IMAGE_TYPES = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};
    
    @Value("${global.fileoperations.doc.save-path}")//文档保存路径
    private String docSavePath;
    
    @Value("${global.fileoperations.image.save-path}")
    private String imageSavePath;
    
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    private final static String KEY_FRE = "board:";
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Override
    public void addBoard(AddBoardReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BoardList board = new BoardList();
        board.setBoardId(req.getBoardId());
        board.setBoardName(req.getBoardName());
        board.setBoardTitle(req.getBoardTitle());
        board.setBoardDesc(req.getBoardDesc().getBytes());
        board.setLauchFlag(req.getLauchFlag());
        board.setLatestFlag(req.getLatestFlag());
        board.setPopupFlag(req.getPopupFlag());
        board.setAllowFlag(req.getAllowFlag());
        board.setEndDate(DateHelper.getDate(req.getEndDate()));
        board.setStatus(req.getStatus());
        role(bomanager,board.getBoardId(),req.getRoles(), null);
        group(bomanager,board.getBoardId(),req.getGroups(), null);
        bomanager.updateBusinessObject(board);
        bomanager.updateDB();
        
        cleanCache();//清理缓存
    }

    @Override
    public BoardQueryRsp boardQuery(HttpHeaders header,BoardQueryReq req,OrgService orgService,UserService userService,RoleService roleService,GroupService groupService) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        RoleAllQueryRsp allRoles = roleService.roleAllQuery(new RoleAllQueryReq());
        GroupAllQueryRsp allGroups = groupService.groupAllQuery();
        
        List<BoardList> boardLists = null;
        if (StringUtils.isEmpty(req.getBegin()) && StringUtils.isEmpty(req.getPageSize())) {//通知维护界面详情查询
            if (!StringUtils.isEmpty(req.getBoardId())) { //查询详情
                BoardQueryRsp rsp = new BoardQueryRsp();
                rsp.setBoards(new ArrayList<Board>());
                boardLists = bomanager.loadBusinessObjects(BoardList.class, "boardId = :boardId and status =:status", "boardId", req.getBoardId(), "status", Status.Valid.id);
                boardLists.forEach(boardList -> {
                    rsp.getBoards().add(setBoardInfo(boardList, bomanager, allRoles,allGroups));
                });
                rsp.setTotcalCount(1);
                return rsp;
            }else {
                throw new ALSException("901204");
            }
        }else {//页面分页
            ArrayList<Board> boards = new ArrayList<Board>();//Board结果
            if(req.getAuthFlag().equals(YesNo.No.id)) {//通知维护界面列表查询
                BoardQueryRsp rsp = new BoardQueryRsp();
                rsp.setBoards(new ArrayList<Board>());
                BusinessObjectAggregate<BoardList> boardListAggregate = bomanager.loadBusinessObjects(BoardList.class,req.getBegin(),req.getPageSize(), "status =:status","status",Status.Valid.id);
                boardLists = boardListAggregate.getBusinessObjects();
                for (BoardList boardList : boardLists) {
                    rsp.getBoards().add(setBoardInfo(boardList,bomanager, allRoles,allGroups));
                }
                rsp.setTotcalCount(boardListAggregate.getAggregate("count(BOARDID) as cnt").getInt("cnt"));
                return rsp;
            }else {//用户登录首页通知展示查询
                String headerUserId = GlobalShareContextHolder.getUserId();
                BoardQueryRsp rsp = (BoardQueryRsp)redisTemplate.opsForValue().get(applicationName + KEY_FRE + headerUserId);
                if(rsp != null) {
                    return rsp;//如果缓存有就从缓存里取，否则才重新查询
                }else {
                    rsp = new BoardQueryRsp();
                    rsp.setBoards(new ArrayList<Board>());
                    List<BoardList> boardResult = bomanager.loadBusinessObjects(BoardList.class,req.getBegin(),req.getPageSize(),"lauchFlag = :lauchFlag and endDate >= :dateEndDate and status =:status order by latestFlag desc",
                            "dateEndDate",getDateEndDate(),"lauchFlag",YesNo.Yes.id,"status",Status.Valid.id).getBusinessObjects();
                    
                    UserQueryReq userQueryReq = new UserQueryReq();
                    userQueryReq.setUserId(headerUserId);
                    UserQueryRsp userRsp = userService.getUsers(userQueryReq, false);
                    User loginUser = userRsp.getUsers().get(0);
                    
                    boardResult.forEach(board -> {
                        if (YesNo.Yes.id.equals(board.getAllowFlag())) { // 全部可见直接返回
                            boards.add(setBoardInfo(board,bomanager, allRoles,allGroups));
                            return;
                        }
                        List<BoardRole> boardRole = bomanager.loadBusinessObjects(BoardRole.class, 0, Integer.MAX_VALUE, "boardId =:boardId","boardId",board.getBoardId()).getBusinessObjects();
                        List<BoardGroup> boardGroup = bomanager.loadBusinessObjects(BoardGroup.class, 0, Integer.MAX_VALUE, "boardId =:boardId","boardId",board.getBoardId()).getBusinessObjects();
                        
                        //判断通知角色
                        loginUser.getRoles()
                        .forEach(role -> {
                            if (boardRole.stream().anyMatch(br -> br.getRoleId().equals(role.getRoleId()))) {
                                boards.add(setBoardInfo(board,bomanager, allRoles,allGroups));
                                return;
                            }
                        });
                        
                        //判断通知分组
                        loginUser.getGroups()
                        .forEach(group -> {
                            if (boardGroup.stream().anyMatch(bp -> bp.getGroupId().equals(group.getGroupId()))) {
                                boards.add(setBoardInfo(board,bomanager, allRoles,allGroups));
                                return;
                            }
                        });
                    });
                    
                    for (Board board : boards) {//去除重复记录
                        if (rsp.getBoards().stream().anyMatch(b -> b.getBoardId().equals(board.getBoardId()))) {
                            continue;
                        }
                        rsp.getBoards().add(board);
                    }
                    rsp.setTotcalCount(rsp.getBoards().size());
                    
                    //记录缓存信息
                    redisTemplate.opsForValue().set(applicationName + KEY_FRE+ headerUserId, rsp, 1, TimeUnit.DAYS);
                    return rsp;
                }
            }
        }
    }
    
    /**
     * 清理Board缓存
     */
    public void cleanCache() {
        Set<String> keys = redisTemplate.keys(applicationName + KEY_FRE+ "*");
        if(!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 根据通知实体对象、角色、角色组生成通知DTO对象
     * @param boardList 通知实体对象
     * @param bomanager 数据实体管理对象
     * @param allRoles 所有角色
     * @param allGroups 所有角色组
     * @return 通知DTO对象
     */
    private Board setBoardInfo(BoardList boardList,BusinessObjectManager bomanager,RoleAllQueryRsp allRoles,GroupAllQueryRsp allGroups) {
        Board board = new Board();
        board.setBoardId(boardList.getBoardId());
        board.setBoardName(boardList.getBoardName());
        board.setBoardTitle(boardList.getBoardTitle());
        board.setBoardDesc(new String(boardList.getBoardDesc()));
        board.setLauchFlag(boardList.getLauchFlag());
        board.setLatestFlag(boardList.getLatestFlag());
        board.setPopupFlag(boardList.getPopupFlag());
        board.setAllowFlag(boardList.getAllowFlag());
        board.setAuthor(boardList.getInputUserId());
        board.setLauchTime(boardList.getInputTime().format(DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format)));
        board.setFilePath(new ArrayList<UploadFile>());
        board.setEndDate(boardList.getEndDate() == null ? "":boardList.getEndDate().format(DateTimeFormatter.ofPattern(FormatType.DateFormat.format)));
        board.setRoles(new ArrayList<Role>());
        board.setGroups(new ArrayList<Group>());
        
        List<BoardRole> roles = bomanager.loadBusinessObjects(BoardRole.class, 0, Integer.MAX_VALUE, "boardId =:boardId","boardId",boardList.getBoardId()).getBusinessObjects();
        roles.forEach(role -> {
            Role r = new Role();
            r.setRoleId(role.getRoleId());
            allRoles.getRoles().stream()//搜索名字
            .filter(redisRole -> role.getRoleId().equals(redisRole.getRoleId()))
            .forEach(redisRole -> {
                r.setRoleName(redisRole.getRoleName());
                r.setBelongOrgLevel(redisRole.getBelongOrgLevel());
                r.setBelongOrgType(redisRole.getBelongOrgType());
                r.setBelongRootOrg(redisRole.getBelongRootOrg());
            });
            r.setOrgId(role.getOrgId());
            board.getRoles().add(r);
        });
        
        List<BoardGroup> groups = bomanager.loadBusinessObjects(BoardGroup.class, 0, Integer.MAX_VALUE, "boardId =:boardId","boardId",boardList.getBoardId()).getBusinessObjects();
        groups.forEach(group -> {
            Group g= new Group();
            g.setGroupId(group.getGroupId());
            allGroups.getGroups().stream()//搜索名字
            .filter(redisGroup -> group.getGroupId().equals(redisGroup.getGroupId()))
            .forEach(redisGroup -> {
                g.setGroupName(redisGroup.getGroupName());
                g.setBelongOrgLevel(redisGroup.getBelongOrgLevel());
                g.setBelongOrgType(redisGroup.getBelongOrgType());
                g.setBelongRootOrg(redisGroup.getBelongRootOrg());
            });
            g.setOrgId(group.getOrgId());
            board.getGroups().add(g);
        });
        List<FileRecord> files = bomanager.loadBusinessObjects(FileRecord.class, 0, Integer.MAX_VALUE, "objectNo =:objectNo and objectType =:objectType","objectNo",boardList.getBoardId(),"objectType",ClassUtils.getShortName(BoardList.class)).getBusinessObjects();
        files.forEach(file -> {
            UploadFile uploadFile = new UploadFile();
            String[] filePath = file.getFilePath().split(File.separator);
            uploadFile.setName(filePath[filePath.length - 1]);
            uploadFile.setStatus(file.getStatus());
            uploadFile.setUid(file.getSerialNo());
            uploadFile.setSize(file.getSize().intValue());
            uploadFile.setUrl(file.getFilePath());
            board.getFilePath().add(uploadFile);
        });
        return board;
    }
    
    @Override
    public void updateBoard(UpdateBoardReq req, OrgService orgService) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BoardList board = bomanager.keyLoadBusinessObject(BoardList.class, req.getBoardId());
        board.setBoardName(req.getBoardName());
        board.setBoardTitle(req.getBoardTitle());
        board.setBoardDesc(req.getBoardDesc().getBytes());
        board.setLauchFlag(req.getLauchFlag());
        board.setLatestFlag(req.getLatestFlag());
        board.setPopupFlag(req.getPopupFlag());
        board.setAllowFlag(req.getAllowFlag());
        board.setEndDate(DateHelper.getDate(req.getEndDate()));
        board.setStatus(req.getStatus());
        
        role(bomanager, board.getBoardId(), req.getRoles(), orgService);
        group(bomanager, board.getBoardId(), req.getGroups(), orgService);
        
        bomanager.updateDB();
        
        cleanCache();//清理缓存
    }

    @Override
    public void deleteBoard(DeleteBoardReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BoardList board = bomanager.keyLoadBusinessObject(BoardList.class, req.getBoardId());
        if (board == null) {
            throw new ALSException("901204");
        }
        bomanager.deleteBusinessObject(board);
        bomanager.updateDB();
        
        bomanager.deleteObjectBySql(BoardRole.class, "boardId=:boardId", "boardId", req.getBoardId());
        bomanager.deleteObjectBySql(BoardGroup.class, "boardId=:boardId", "boardId", req.getBoardId());
        
        cleanCache();//清理缓存
    }

    @Override
    public void downloadDoc(String boardId, String fileIndex, HttpServletResponse response) {
        //设置response
        response.setContentType("application/octet-stream");
        response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");
        
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        FileRecord record = bomanager.keyLoadBusinessObject(FileRecord.class,fileIndex);
        if (record == null) {
            throw new ALSException("903004");
        }
        
        File file = new File(record.getFilePath());
        if (file != null && file.exists()) {
            byte[] b = new byte[1024];
            try (FileInputStream fis = new FileInputStream(file);OutputStream os = response.getOutputStream();BufferedInputStream bis = new BufferedInputStream(fis);){
                String[] filePaths = record.getFilePath().split(File.separator);
                response.addHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filePaths[filePaths.length - 1], "UTF-8"));
                int i = 0;
                while ((i= bis.read(b)) != -1) {
                    os.write(b, 0, i);
                }
                    
            } catch (IOException e) {
                if (log.isErrorEnabled()) {
                    log.error("通知管理下载附件出错：",e);
                }
                throw new ALSException("901212", e);
            }
        }else {
            throw new ALSException("901212");
        }
    }

    @Override
    public ResponseMessage<Object> uploadDoc(String boardId, MultipartFile file, HttpServletResponse response) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        FileRecord record = null;
        String filePath = this.docSavePath + File.separator + "board" + File.separator + boardId + File.separator+ file.getOriginalFilename();
        File doc = new File(CleanPathUtil.pathCharWhite(filePath));
        try {
            if (!doc.exists()) {
                doc.getParentFile().mkdirs();//创建父级文件夹
            }
            file.transferTo(doc);
            record = new FileRecord();
            record.generateKey();
            record.setObjectNo(boardId);
            record.setObjectType(ClassUtils.getShortName(BoardList.class));
            record.setFilePath(filePath);
            record.setSize(new BigDecimal(file.getSize()));
            record.setStatus(Status.Valid.id);
        } catch (IllegalStateException e) {
            if (log.isErrorEnabled()) {
                log.error("通知管理上传附件出错：",e);
            } 
            throw new ALSException("901206",e);
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("通知管理上传附件出错：",e);
            }
            throw new ALSException("901207",e);
        }
        
        bomanager.updateBusinessObject(record);
        bomanager.updateDB();
        ResponseMessage<Object> hrb = new ResponseMessage<Object>();
        hrb.setMessage(record.getKeyString());
        return hrb;
    }
    
    /**
     * 通知角色操作
     * @param bomanager 本方法内部不做updateDB
     * @param boardId 通知信息
     * @param roles 通知关联角色
     * @return 是否有权限变更
     */
    private boolean role(BusinessObjectManager bomanager, String boardId, List<Role> roles, OrgService orgService) {
        if(StringUtils.isEmpty(boardId)) return false;//允许新增通知、更新通知不设置角色
        List<BoardRole> brs = bomanager.loadBusinessObjects(BoardRole.class, 0, Integer.MAX_VALUE, "boardId=:boardId", 
                                                                             "boardId", boardId).getBusinessObjects();
        
        
        boolean flag = false; //权限是否有过变更
        
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(BoardRole ur : brs) {
            
            if (orgService != null) {
                String loginRootOrg = orgService.getRootOrgId(GlobalShareContextHolder.getOrgId());
                if (!ur.getOrgId().equals(loginRootOrg)) { //如果登陆机构的法人与权限配置的法人不同则跳过修改
                    continue;
                }
            }
            
            boolean exist = false;
            for(Role role : roles) {
                if(role.getRoleId().equals(ur.getRoleId())) {
                    exist = true;
                    break;
                }
            }
            
            if(!exist) {
                flag = true;
                bomanager.deleteBusinessObject(ur);//本次没有传入的角色，全部删除
            }
        }
        
        //再区分本次提交记录存在的，但是数据库记录不存在的
        for(Role role : roles) {
            boolean exist = false;
            for(BoardRole ur : brs) {
                if(role.getRoleId().equals(ur.getRoleId())) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                RoleInfo ri = bomanager.keyLoadBusinessObject(RoleInfo.class, role.getRoleId());
                if(ri == null) //如果没有这个角色就跳过
                    continue;
                OrgInfo oi = bomanager.keyLoadBusinessObject(OrgInfo.class, role.getOrgId());
                if(oi == null) //如果没有这个机构就跳过
                    continue;
                flag = true;
                BoardRole br = new BoardRole();
                br.setBoardId(boardId);
                br.setRoleId(role.getRoleId());
                br.setOrgId(role.getOrgId());
                bomanager.updateBusinessObject(br);
            }
        }
        
        if(log.isInfoEnabled() && flag) {
            log.info("通知："+boardId+"的角色从："+brs.toString()+" 变更为："+roles.toString());
        }
        
        return flag;
    }
    /**
     * 通知角色操作
     * @param bomanager 本方法内部不做updateDB
     * @param boardId 通知信息
     * @param groups 通知关联角色组
     * @return 是否变更过权限
     */
    private boolean group(BusinessObjectManager bomanager, String boardId, List<Group> groups, OrgService orgService) {
        if(StringUtils.isEmpty(boardId)) return false;//允许新增通知、更新通知不设置角色
        List<BoardGroup> bgs = bomanager.loadBusinessObjects(BoardGroup.class, 0, Integer.MAX_VALUE, "boardId=:boardId", 
                                                                             "boardId", boardId).getBusinessObjects();
        
        boolean flag = false; //权限是否有过变更
        
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(BoardGroup ug : bgs) {
            
            if (orgService != null) {
                String loginRootOrg = orgService.getRootOrgId(GlobalShareContextHolder.getOrgId());
                if (!ug.getOrgId().equals(loginRootOrg)) { //如果登陆机构的法人与权限配置的法人不同则跳过修改
                    continue;
                }
            }
            
            boolean exist = false;
            for(Group group : groups) {
                if(group.getGroupId().equals(ug.getGroupId())) {
                    exist = true;
                    break;
                }
            }
            
            if(!exist) {
                flag = true;
                bomanager.deleteBusinessObject(ug);//本次没有传入的角色，全部删除
            }
        }
        
        //再区分本次提交记录存在的，但是数据库记录不存在的
        for(Group group : groups) {
            boolean exist = false;
            for(BoardGroup ug : bgs) {
                if(group.getGroupId().equals(ug.getGroupId())) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                GroupInfo gi = bomanager.keyLoadBusinessObject(GroupInfo.class, group.getGroupId());
                if(gi == null) //如果没有这个角色就跳过
                    continue;
                OrgInfo oi = bomanager.keyLoadBusinessObject(OrgInfo.class, group.getOrgId());
                if(oi == null) //如果没有这个机构就跳过
                    continue;
                
                flag = true;
                BoardGroup bg = new BoardGroup();
                bg.setBoardId(boardId);
                bg.setGroupId(group.getGroupId());
                bg.setOrgId(group.getOrgId());
                bomanager.updateBusinessObject(bg);
            }
        }
        
        if(log.isInfoEnabled() && flag) {
            log.info("通知："+boardId+"的组从："+bgs.toString()+" 变更为："+groups.toString());
        }
        
        return flag;
    }

    @Override
    public void deleteFileRecored(String boardId, HttpServletResponse response) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        FileRecord records = bomanager.keyLoadBusinessObject(FileRecord.class, boardId);
        bomanager.deleteBusinessObject(records);
        bomanager.updateDB();
    }

    @Override
    public GetBoardIdRsp getBoardId() {
        GetBoardIdRsp rsp = new GetBoardIdRsp();
        BoardList boardList = new BoardList();
        boardList.generateKey();
        rsp.setBoardId(boardList.getKeyString());
        return rsp;
    }
    
    @Override
    public String saveImage(MultipartFile file) {
        if (isImage(file)) {
            String uuid = LocalDateTime.now().getNano() + UUID.randomUUID().toString().replaceAll("-", "");
            String prefix = this.imageSavePath + File.separator + uuid + File.separator;
            String filePath = prefix + CleanPathUtil.pathCharWhite(file.getOriginalFilename());
            File image = new File(filePath);
            if (!image.exists()) {
                image.getParentFile().mkdirs();//创建父级文件夹
            }
            try {
                file.transferTo(image);
            }catch (IOException e) {
                throw new ALSException("903003", e, e.getMessage());
            }
            return uuid;
        }else {
            throw new ALSException("901301", file.getOriginalFilename().split(".")[1]);
        }
    }

    @Override
    public void downloadImage(String uuid, HttpServletResponse response) {
        String folderName = this.imageSavePath + File.separator + uuid + File.separator;
        File folder = new File(folderName);
        byte[] b = new byte[1024];
        try (FileInputStream fis = new FileInputStream(folder.listFiles()[0]);OutputStream os = response.getOutputStream();BufferedInputStream bis = new BufferedInputStream(fis);){
            if (folder != null) {
                if (folder.exists()) {
                    int i = 0;
                    while ((i= bis.read(b)) != -1) {
                        os.write(b, 0, i);
                    }
                }
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("图片下载出错：",e);
            }
            throw new ALSException("903001", e);
        }
        response.setContentType("image/png");
        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        InputStream bufferIn = new ByteArrayInputStream(b);
        try {
            ImageIO.write(ImageIO.read(bufferIn),"png",response.getOutputStream());
        } catch (IOException e) {
            throw new ALSException("903001", e);
        }
    }
    
    protected static boolean isImage(MultipartFile file) {
        return Stream.of(IMAGE_TYPES).anyMatch(type -> CleanPathUtil.pathCharWhite(file.getOriginalFilename().toLowerCase(Locale.ENGLISH)).endsWith(type));
    }
    
    private LocalDate getDateEndDate() {
        return LocalDate.parse(DateHelper.getRelativeDate(DateHelper.getBusinessDate(), TermUnit.Day.id,1), DateTimeFormatter.ofPattern(FormatType.DateFormat.format));
    }
}
