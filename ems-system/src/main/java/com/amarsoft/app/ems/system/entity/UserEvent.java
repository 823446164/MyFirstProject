package com.amarsoft.app.ems.system.entity;



import java.util.function.Supplier;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.aecd.acct.constant.TermUnit;
import com.amarsoft.aecd.common.constant.DataTransferMode;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.annotation.EntityRelationShipHistory;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.entity.UserEvent.LastWeekDate;

/**
 * 用户事件信息
 */

@Description("用户事件信息表")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_USER_EVENT",
    indexes = {
            @Index(name="IDX_USER_EVENT_01",columnList="usereventtype")
        })
@EntityRelationShip(foreignEntity = UserInfo.class, columns = {"userId"}, foreignColumns = {"userId"})
@EntityRelationShip(foreignEntity = OrgInfo.class, columns = {"orgId"}, foreignColumns = {"orgId"})
//历史数据清理定义
@EntityRelationShipHistory(targetEntity = UserEventHistory.class, 
                         mode = DataTransferMode.INDEPENDENT, 
                         condition = " eventTime <= :eventTime ",
                         parameters = LastWeekDate.class)

@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class UserEvent extends UserEventBase {
    private static final long serialVersionUID = 1L;
    
    public static final class LastWeekDate implements Supplier<Object[]> {
        @Override
        public Object[] get() {
            return new Object[] {"eventTime", DateHelper.getRelativeDate(DateHelper.getBusinessLocalDateTime(), TermUnit.Day.id, -7)};
        }
    }
}

