/**
 * 新增用户导出记录
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.addexport;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class AddExportReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("导出编号")
    @NotEmpty
    @Length(max=40)
    private String serialNo;
    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
    @Description("文件名称")
    @NotEmpty
    @Length(max=100)
    private String fileName;
}