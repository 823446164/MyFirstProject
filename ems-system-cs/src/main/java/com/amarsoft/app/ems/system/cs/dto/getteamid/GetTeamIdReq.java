/*
 * 文件名：GetTeamIdReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：amarsoft
 * 修改时间：2020年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.getteamid;

import java.io.Serializable;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author amarsoft
 * @version 2020年5月12日
 * @see GetTeamIdReq
 * @since
 */

@Getter
@Setter
@ToString
public class GetTeamIdReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @Description("团队编号")
    private String teamId;
     @Description("部门编号")
    private String BelongOrgId;
}
