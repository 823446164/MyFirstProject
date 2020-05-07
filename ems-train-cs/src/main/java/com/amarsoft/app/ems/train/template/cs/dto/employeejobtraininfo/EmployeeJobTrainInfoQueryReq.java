package com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo;

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
 * 在职培训详情查询请求实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeJobTrainInfoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("培训编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TC.serialNo")
    private String serialNo;
}
