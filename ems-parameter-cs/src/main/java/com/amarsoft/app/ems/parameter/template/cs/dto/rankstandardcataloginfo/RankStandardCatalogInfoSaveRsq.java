/*
 * 文件名：RankStandardCatalogInfoSaveRsq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo;

import java.io.Serializable;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xphe
 * @version 2020年5月8日
 * @see RankStandardCatalogInfoSaveRsq
 * @since
 */
@Getter
@Setter
@ToString
public class RankStandardCatalogInfoSaveRsq implements Serializable{
    private static final long serialVersionUID = 1L;
    @Description("所属团队")
    @Length(max=40)
    @ActualColumn("RSC.belongTeam")
    private String belongTeam;
        
    @Description("职级编号")
    @Length(max=40)
    @ActualColumn("RSC.serialNo")
    private String serialNo;
}
