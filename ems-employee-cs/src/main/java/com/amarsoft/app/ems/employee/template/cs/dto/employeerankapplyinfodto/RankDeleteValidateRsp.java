/*
 * 文件名：RankDeleteValidateRsp.java
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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈子职级删除/指标选择的校验响应实体类〉
 * @author xphe
 * @version 2020年5月21日
 * @see RankDeleteValidateRsp
 * @since
 */
@Getter
@Setter
@ToString
public class RankDeleteValidateRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    //记录个数
    private int recordCount;
}
