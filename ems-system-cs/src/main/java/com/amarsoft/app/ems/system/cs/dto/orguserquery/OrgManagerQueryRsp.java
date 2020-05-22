/**
 * 查询机构用户
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.orguserquery;

import java.io.Serializable;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrgManagerQueryRsp extends OneLevelDeptDto implements Serializable {
    private static final long serialVersionUID = 1L;

}