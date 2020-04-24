/**
 * 查询生效日
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.efficientdatequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.DatePattern;

@Getter
@Setter
@ToString
public class EfficientDate implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("生效日期")
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String efficientDate;
}