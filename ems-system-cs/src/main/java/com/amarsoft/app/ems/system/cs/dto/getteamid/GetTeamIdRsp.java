/**
 * 获取团队编号
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.getteamid;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class GetTeamIdRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("团队编号")
    @Length(max=40)
    private String teamId;
}