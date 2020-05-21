package com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.app.ems.system.cs.dto.roleallquery.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 菜单详情Info保存请求实体类
 * @author jcli2
 */
@Getter
@Setter
@ToString
public class SysMenuInfoDtoSaveReq extends SysMenuInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Role> existlist;
}
