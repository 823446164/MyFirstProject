package com.amarsoft.app.ems.system.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 通告栏
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Description("通告信息表")
@EntityRelationShip(foreignEntity = BoardRole.class, columns = {"boardId"}, foreignColumns = {"boardId"})
@Table(name = "SYS_BOARD_LIST")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class BoardList extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("通告编号")
    @Id
    @Column(name="BOARDID",unique=true, nullable=false, length=40)
    private String boardId;
    
    @Description("通知")
    @Column(name = "BOARDNAME",length=80)
    private String boardName;
    
    @Description("通知标题")
    @Column(name = "BOARDTITLE",length=80)
    private String boardTitle;
    
    @Description("通告描述")
    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name = "BOARDDESC")
    private byte[] boardDesc;
    
    @Description("是否发布")
    @Enum(YesNo.class)
    @Column(name = "LAUCHFLAG",length=1)
    private String lauchFlag;
    
    @Description("是否最新")
    @Enum(YesNo.class)
    @Column(name = "LATESTFLAG",length=1)
    private String latestFlag;
    
    @Description("是否弹出")
    @Enum(YesNo.class)
    @Column(name = "POPUPFLAG",length=1)
    private String popupFlag;
    
    @Description("是否所有人查看")
    @Enum(YesNo.class)
    @Column(name = "ALLOWFLAG",length=1)
    private String allowFlag;
    
    @Description("结束日期")
    @Column(name = "ENDDATE")
    private LocalDate endDate;
    
    @Description("状态")
    @Enum(SystemStatus.class)
    @Column(name = "STATUS",length=1)
    private String status;
    
    @Description("登记人")
    @Column(name = "INPUTUSERID",length=40)
    @CreatedBy
    private String inputUserId;
    
    @Description("登记时间 yyyy/MM/dd HH:mm:ss.SSS")
    @Column(name = "INPUTTIME")
    @CreatedDate
    private LocalDateTime inputTime;
    
    @Description("更新人")
    @Column(name = "UPDATEUSERID",length=40)
    @LastModifiedBy
    private String updateUserId;
    
    @Description("更新时间 yyyy/MM/dd HH:mm:ss.SSS")
    @Column(name = "UPDATETIME")
    @LastModifiedDate
    private LocalDateTime updateTime;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public byte[] getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(byte[] boardDesc) {
        this.boardDesc = boardDesc;
    }

    public String getLauchFlag() {
        return lauchFlag;
    }

    public void setLauchFlag(String lauchFlag) {
        this.lauchFlag = lauchFlag;
    }

    public String getLatestFlag() {
        return latestFlag;
    }

    public void setLatestFlag(String latestFlag) {
        this.latestFlag = latestFlag;
    }

    public String getPopupFlag() {
        return popupFlag;
    }

    public void setPopupFlag(String popupFlag) {
        this.popupFlag = popupFlag;
    }

    public String getAllowFlag() {
        return allowFlag;
    }

    public void setAllowFlag(String allowFlag) {
        this.allowFlag = allowFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getInputUserId() {
        return inputUserId;
    }

    public void setInputUserId(String inputUserId) {
        this.inputUserId = inputUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public LocalDateTime getInputTime() {
        return inputTime;
    }

    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}

