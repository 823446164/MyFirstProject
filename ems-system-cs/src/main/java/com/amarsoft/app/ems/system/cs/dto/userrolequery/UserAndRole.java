/*
 * 文件名：UserAndRole.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：用户角色
 * 修改人：amarsoft
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.userrolequery;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserAndRole implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("角色编号")
    @Length(max=40)
    private String roleId;
    @Description("用户编号")
    @Length(max=40)
    private String userId;
    @Description("机构编号")
    @Length(max=40)
    private String orgId;
}
