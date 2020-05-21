package com.amarsoft.app.ems.system.template.cs.dto.roleinfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色信息Info保存请求实体类
 * @author cmhuang
 */
@Getter
@Setter
@ToString
public class RoleInfoDtoSaveReq extends RoleInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    //判断是否新增
    private Boolean add;
}
