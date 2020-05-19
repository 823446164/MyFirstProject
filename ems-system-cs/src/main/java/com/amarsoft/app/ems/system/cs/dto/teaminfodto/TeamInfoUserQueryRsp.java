package com.amarsoft.app.ems.system.cs.dto.teaminfodto;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.UserInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 团队员工信息查询响应实体类
 * @author hpli
 */

@Getter
@Setter
@ToString
public class TeamInfoUserQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("员工Id LIST")
    private List<UserInfo> userInfos; {
    }
}
