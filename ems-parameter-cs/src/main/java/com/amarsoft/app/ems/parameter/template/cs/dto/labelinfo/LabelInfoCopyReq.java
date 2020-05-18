/*
 * 文件名：LabelInfoCopyReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签复制的请求体
 * 修改人：yrong
 * 修改时间：2020年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;

/**
 * 标签复制实体类
 * @author yrong
 * @version 2020年5月15日
 * @see LabelInfoCopyReq
 * @since
 */
@Getter
@Setter
public class LabelInfoCopyReq implements Serializable{
    private static final long serialVersionUID = 1L;
    @Description("流水号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("LD.serialNo")
    private String serialNo;
    
    @Description("标签名称")
    @Length(max=80)
    @ActualColumn("LD.labelName")
    private String labelName;
    
    @Description("标签码值")
    @Length(max=80)
    @ActualColumn("LD.codeNo")
    private String codeNo;

}
