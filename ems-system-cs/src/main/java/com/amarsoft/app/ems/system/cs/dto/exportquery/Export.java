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
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.ExportStatus;

@Getter
@Setter
@ToString
public class Export implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("导出编号")
    @Length(max=40)
    private String serialNo;
    @Description("导出文件名")
    @Length(max=100)
    private String fileName;
    @Description("导出开始时间")
    @Length(max=23)
    private String beginTime;
    @Description("导出结束时间")
    @Length(max=23)
    private String finishTime;
    @Description("导出状态")
    @Length(max=1)
    @Enum(ExportStatus.class)
    private String status;
    @Description("日志记录")
    @Length(max=400)
    private String log;
    @Description("下载记录")
    @Length(max=100)
    private String uri;
}