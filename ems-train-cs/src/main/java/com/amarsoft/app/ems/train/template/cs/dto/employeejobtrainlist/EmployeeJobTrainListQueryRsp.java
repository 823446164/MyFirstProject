package com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 在职培训列表查询响应实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
public class EmployeeJobTrainListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("在职培训列表")
    private List<EmployeeJobTrainList> employeeJobTrainLists;
}
