package com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;

/**
 * 培训转移记录查询请求实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeTrainTransferInfoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
