/**
 * 查询机构用户
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.orguserquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class OrgUserQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("角色Id")
    @Length(max=80)
    private String roleId;
    @Description("查询id")
    @Length(max=80)
    private String id;

}