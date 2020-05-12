/*
 * 文件名：DeleteTeamRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：hpli
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.deleteteam;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteTeamRsp {
    private static final long serialVersionUID = 1L;
    @Description("信息")
    private String  Meassage;
    
    @Description("是否存在")
    private Boolean  isExit;
    

}

