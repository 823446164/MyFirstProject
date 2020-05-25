/*
 * 文件名：PowerToLableQueryRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.powertolabel;

import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.arem.annotation.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 生成响应体
 * @author yrong
 * @version 2020年5月22日
 * @see PowerToLableQueryRsp
 * @since
 */

@Getter
@Setter
@ToString
public class PowerToLableQueryRsp {
    @Description("是否有维护权限")
    @QueryBegin
    private boolean power;//true代表有权限，false代表无权限
}
