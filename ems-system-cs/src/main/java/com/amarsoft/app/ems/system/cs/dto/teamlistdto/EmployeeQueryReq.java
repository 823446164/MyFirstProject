/*
 * 文件名：EmployeeQueryRep.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：EmployeeQueryRep.java
 * 修改人：hpli
 * 修改时间：2020年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.cs.dto.teamlistdto;
import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 员工请求体
 * @author hpli
 * @version 2020年5月15日
 * @see EmployeeQueryReq
 * @since
 */
@Getter
@Setter
@ToString
public class EmployeeQueryReq  implements Serializable{
    private static final long serialVersionUID = 1L;
    @Description("团队编号")
    @Length(max=40)
    private String teamId;
}
