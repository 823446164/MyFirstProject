/**
 * 添加团队成员
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.addteamuser;

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
public class UserTeam implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("团队编号")
    @NotEmpty
    @Length(max=40)
    private String teamId;
    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
}