/*
 * 文件名：RankStandardItemsInfoDeleteReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈职级指标删除请求体〉
 * @author xphe
 * @version 2020年5月18日
 * @see RankStandardItemsInfoDeleteReq
 * @since
 */
@Getter
@Setter
@ToString
public class RankStandardItemsInfoDeleteReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("指标编号")
    @Length(max=40)
    @ActualColumn("RSI.serialNo")
    private String serialNo;
}
