/*
 * 文件名：TeamListDtoQueryRoleRsp.java 
 * 版权：Copyright by www.amarsoft.com 
 * 描述：团队负责人查询
 *  修改人：hpli
 * 修改时间：2020年5月13日 
 * 跟踪单号： 修改单号： 
 * 修改内容：团队负责人查询
 */


package com.amarsoft.app.ems.system.cs.dto.teamlistdto;


import java.util.List;

import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 〈
 * 
 * @author hpli
 * @version 2020年5月13日
 * @see TeamListDtoQueryRoleRsp 角色查询
 * @since
 */
@Getter
@Setter
@ToString
public class TeamListDtoQueryRoleRsp {
    private static final long serialVersionUID = 1L;

    @Description("角色A")
    @NotEmpty
    private List<String> teamListRoleA;

    @Description("角色B")
    @NotEmpty
    private List<String> teamListRoleB;

    @Description("角色C")
    @NotEmpty
    private List<String> teamListRoleC;

}
