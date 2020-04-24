/**
 * 检查用户对URL的权限
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userauth;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class UserAuthReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
    @Description("机构编号")
    @NotEmpty
    @Length(max=40)
    private String orgId;
    @Description("需要访问的url")
    @NotEmpty
    @Length(max=80)
    private String url;
}