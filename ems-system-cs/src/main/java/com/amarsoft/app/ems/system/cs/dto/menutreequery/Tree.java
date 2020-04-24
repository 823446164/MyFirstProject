/**
 * 查询菜单树图
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.menutreequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@ToString
public class Tree implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("名称")
    @NotEmpty
    @Length(max=40)
    private String title;
    @Description("存储值")
    @NotEmpty
    @Length(max=40)
    private String key;
    @Description("是否展开")
    @NotEmpty
    private boolean expanded;
    @Description("图标")
    @NotEmpty
    @Length(max=50)
    private String icon;
    @Description("是否叶子节点")
    private boolean leaf;
    @Description("是否禁用")
    private boolean disable;
    @Description("排序号")
    @Length(max=40)
    private String sortNo;
    @Description("子菜单树图信息组")
    @Valid
    private List<Tree> children;

    public boolean getExpanded(){
        return this.expanded;
    }

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }

    public boolean getLeaf(){
        return this.leaf;
    }

    public void setLeaf(boolean leaf){
        this.leaf = leaf;
    }

    public boolean getDisable(){
        return this.disable;
    }

    public void setDisable(boolean disable){
        this.disable = disable;
    }
}