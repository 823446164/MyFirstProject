/*
 * 文件名：RankDeleteValidateReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈子职级删除/指标选择的校验请求实体类〉
 * @author xphe
 * @version 2020年5月21日
 * @see RankDeleteValidateReq
 * @since
 */
@Setter
@Getter
@ToString
@QueryRule(groupBy = {})
public class RankDeleteValidateReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("职等编号")
    @Length(max=40)
    private String rankStandard;
    
    @Description("子职级编号")
    @Length(max=40)
    private String rankNo;
}
