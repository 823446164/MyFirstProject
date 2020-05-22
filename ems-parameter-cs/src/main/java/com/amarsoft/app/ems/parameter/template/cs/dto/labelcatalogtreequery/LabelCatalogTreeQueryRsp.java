/*
 * 文件名：LabelCatalogTreeQueryRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签目录树图模板请求体
 * 修改人：yrong
 * 修改时间：2020年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.cs.dto.labelcatalogtreequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import javax.validation.Valid;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class LabelCatalogTreeQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("树图信息组")
    @Valid
    @NotEmpty
    private List<Tree> trees;
    
    @Description("用户权限")
    @NotEmpty
    @Valid
    private boolean power;
}