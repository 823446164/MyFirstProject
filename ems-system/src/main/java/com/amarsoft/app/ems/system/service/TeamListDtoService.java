package com.amarsoft.app.ems.system.service;
import javax.validation.Valid;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;
/**
 * 团队信息Service接口
 * 
 * @author hpli
 */
public interface TeamListDtoService {
    /**
     * 团队信息查询
     * 
     * @param request
     * @return rsp
     */
    public TeamListDtoQueryRsp teamListDtoQuery(@Valid TeamListDtoQueryReq teamListDtoQueryReq);

    /**
     * 团队信息删除
     * 
     * @param request
     * @return
     */
    public void teamListDtoDelete(@Valid DeleteInfoDtoQueryReq req);

    /**
     * 根据部门查询团队信息
     * 
     * @param request
     * @return rsp
     */
    TeamListDtoQueryRsp teamQueryById(TeamListDtoQueryReq request);
    /**
     * 团队员工信息
     * 
     * @param request
     * @return rsp
     */
    public EmployeeQueryRsp employeeQuery(@Valid EmployeeQueryReq employeeQueryReq);
    /**
     * 
     * 按条件查询团队信息
     * @param request
     * @return rsp
     */
	public TeamListDtoQueryRsp teamSearch(TeamListDtoQueryReq request);


}
