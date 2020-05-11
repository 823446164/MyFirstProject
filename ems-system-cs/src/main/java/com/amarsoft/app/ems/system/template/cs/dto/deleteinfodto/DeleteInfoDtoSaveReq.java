package com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto;

import java.io.Serializable;

import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 删除部门Info保存请求实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
public class DeleteInfoDtoSaveReq extends DeleteInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
