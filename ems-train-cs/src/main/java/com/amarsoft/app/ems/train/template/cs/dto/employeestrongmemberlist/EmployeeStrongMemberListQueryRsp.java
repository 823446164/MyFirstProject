package com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 培训项目参与人员列表查询响应实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
public class EmployeeStrongMemberListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("培训项目参与人员列表")
    private List<EmployeeStrongMemberList> employeeStrongMemberLists;
}
