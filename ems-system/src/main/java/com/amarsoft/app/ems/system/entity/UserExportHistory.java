package com.amarsoft.app.ems.system.entity;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;


import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
/**
 * 用户导出文件和下载文件记录历史表
 */
@Description("用户导出文件和下载文件记录历史表")
@Entity
@Table(name = "SYS_USER_EXPORT_HIS", indexes = {
        @Index(name = "IDX_USER_EXPORT_HIS_01", columnList = "userId") 
})

@GeneratedKey(autoGenerateKey=false)
public class UserExportHistory extends UserExportBase {
    private static final long serialVersionUID = 1L;
    
    
}

