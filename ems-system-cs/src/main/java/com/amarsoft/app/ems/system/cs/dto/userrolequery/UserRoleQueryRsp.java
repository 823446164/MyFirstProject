/*
 * 文件名：UserRoleQueryRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：amarsoft
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.userrolequery;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;

import com.amarsoft.amps.acsc.annotation.Digits;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRoleQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Description("结果总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer totalCount;
    @Description("用户角色数组")
    @Valid
    @NotEmpty
    private List<UserAndRole> userRoles;
}
