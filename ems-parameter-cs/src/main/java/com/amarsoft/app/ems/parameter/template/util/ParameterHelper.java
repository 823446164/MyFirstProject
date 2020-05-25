/*
 * 文件名：ParameterHelper.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：amarsoft
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.util;


import java.util.List;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.app.ems.system.cs.client.TeamClient;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author amarsoft
 * @version 2020年5月9日
 * @see ParameterHelper
 * @since
 */

public class ParameterHelper {
    private static TeamClient teamClient;
    public static List<TeamInfo> getTeamList(){
        if(teamClient==null) {
            teamClient=SpringHelper.getBean(TeamClient.class);
        }
        TeamQueryReq teamQueryReq= new TeamQueryReq();
        ResponseMessage<TeamQueryRsp> response = teamClient.teamQuery(new RequestMessage<>(teamQueryReq)).getBody();
        if (response == null || response.getMessage() == null || response.getMessage().getTeamInfos() == null) {
            return null;
        }
        List<TeamInfo> teams=response.getMessage().getTeamInfos();
        return teams;
            
    }
    

}
