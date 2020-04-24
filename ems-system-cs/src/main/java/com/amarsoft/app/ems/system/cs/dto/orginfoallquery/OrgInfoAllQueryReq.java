/**
 * 查询所有机构的信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.orginfoallquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.aecd.common.constant.YesNo;

@Getter
@Setter
@ToString
public class OrgInfoAllQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status = "1";
    @Description("是否查询所有")
    @Length(max=1)
    @Enum(YesNo.class)
    private String allFlag = "0";
}