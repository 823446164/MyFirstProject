/*文件名：EmployeeRankRelabelListDtoQueryRsp
 * 版权：Copyright by www.amarsoft.com
 * 描述：查询职级标签的响应体
 * 修改人：dxiao
 * 修改时间：2020/05/19
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工职级标签查询响应实体类
 * @author dxiao
 */
@Getter
@Setter
@ToString
public class EmployeeRankRelabelListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;
    
    @Description("职级标签List")
    private List<EmployeeRankRelabelListDto> employeeRankRelabelListDto;
}
