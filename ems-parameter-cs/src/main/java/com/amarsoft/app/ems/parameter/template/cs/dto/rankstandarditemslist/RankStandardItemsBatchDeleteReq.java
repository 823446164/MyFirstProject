/*
 * 文件名：RankStandardItemsBatchDeleteReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈批量删除的请求实体类〉
 * @author xphe
 * @version 2020年5月22日
 * @see RankStandardItemsBatchDeleteReq
 * @since
 */
@Getter
@Setter
@ToString
public class RankStandardItemsBatchDeleteReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> serialNolists;
}
