/**
 * 更新用户导出记录
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.exportquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Digits;
import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@ToString
public class ExportQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("结果总数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer totalCount;
    @Description("导出结果数组")
    @Valid
    @NotEmpty
    private List<Export> exports;
}