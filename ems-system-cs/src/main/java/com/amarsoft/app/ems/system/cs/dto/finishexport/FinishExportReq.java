/**
 * 更新用户导出记录
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.finishexport;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.ExportStatus;

@Getter
@Setter
@ToString
public class FinishExportReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("导出编号")
    @NotEmpty
    @Length(max=40)
    private String serialNo;
    @Description("导出状态")
    @NotEmpty
    @Length(max=1)
    @Enum(ExportStatus.class)
    private String status;
    @Description("导出地址")
    @NotEmpty
    @Length(max=100)
    private String uri;
    @Description("导出异常日志")
    @Length(max=400)
    private String log;
}