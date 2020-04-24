/**
 * 查询通告
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.boardquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class UploadFile implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("文件编号")
    @NotEmpty
    @Length(max=40)
    private String uid;
    @Description("文件名")
    @NotEmpty
    @Length(max=100)
    private String name;
    @Description("文件状态")
    @NotEmpty
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
    @Description("文件大小")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer size;
    @Description("下载地址")
    @NotEmpty
    @Length(max=100)
    private String url;
}