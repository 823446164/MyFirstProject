/*
 * 文件名：DeptManagerUserQueryRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：zcluo
 * 修改时间：2020年5月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.cs.dto.orguserquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

@Getter
@Setter
@ToString
public class DeptManagerUserQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("员工Id LIST")
    private List<UserInfo> userInfos;
}