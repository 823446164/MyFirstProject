/*
 * 文件名：LabelInfoRepeatRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo;

import java.io.Serializable;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LabelInfoRepeatRsp implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Description("是否重复")
    private boolean isRepeat ;
}
