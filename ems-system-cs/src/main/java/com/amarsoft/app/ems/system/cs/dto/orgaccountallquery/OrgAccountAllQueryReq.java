/**
 * 查询所有机构内部账户的信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.orgaccountallquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;

@Getter
@Setter
@ToString
public class OrgAccountAllQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("状态")
    @Length(max=10)
    @Enum(SystemStatus.class)
    private String status = "1";
}