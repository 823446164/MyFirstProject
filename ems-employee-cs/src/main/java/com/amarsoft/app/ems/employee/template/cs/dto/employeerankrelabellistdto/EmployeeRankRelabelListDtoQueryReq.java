/*文件名：EmployeeRankRelabelListDtoQueryReq
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工职级标签查询请求实体类
 * 修改人：dxiao
 * 修改时间：2020/05/19
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;

/**
 * 员工职级标签查询请求实体类
 * @author dxiao
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeRankRelabelListDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("项目编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("ERR.serialNo")
    private String serialNo;
}
