package com.amarsoft.app.ems.system.entity;



import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;


import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;

/**
 * 用户事件信息
 */

@Description("用户事件信息(历史记录)")
@Entity
@Table(name = "SYS_USER_EVENT_HIS",
    indexes = {
            @Index(name="IDX_USER_EVENT_HIS_01",columnList="usereventtype")
        })
@GeneratedKey(autoGenerateKey=false)
public class UserEventHistory extends UserEventBase {
    private static final long serialVersionUID = 1L;
    
}

