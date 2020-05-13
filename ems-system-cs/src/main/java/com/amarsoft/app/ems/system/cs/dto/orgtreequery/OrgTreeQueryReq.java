/**
 * 机构树图展示
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.orgtreequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import javax.validation.Valid;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class OrgTreeQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("树图查询等级参数")
    @Length(max=40)
    private String param;
}