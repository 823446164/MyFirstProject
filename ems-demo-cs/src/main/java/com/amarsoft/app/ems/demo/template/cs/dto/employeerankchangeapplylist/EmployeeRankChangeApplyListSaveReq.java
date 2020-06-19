package com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 员工职级调整申请List保存请求实体类
 * @author jfan5
 */
@Getter
@Setter
@ToString
public class EmployeeRankChangeApplyListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工职级调整申请List")
    @NotEmpty
    private List<EmployeeRankChangeApplyList> employeeRankChangeApplyLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
