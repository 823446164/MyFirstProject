package com.amarsoft.app.ems.system.function;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.app.ems.system.util.OrgHelper;
import com.amarsoft.aecd.function.AbstractFunctionCode;
import com.amarsoft.aecd.function.properties.Item;

/**
 * 获取当前用户所属机构项下机构的代码信息
 * @author xjzhao
 *
 */
@Service
public class CurrentOrgIdFunctionCode extends AbstractFunctionCode{
    
    @Override
    public List<Item> getItems(Map<String, String> paramMap) {
        List<Item> items = new LinkedList<Item>();
        String orgLevelTmp = null;
        String orgTypeTmp = null;
        if(paramMap!=null) orgLevelTmp = paramMap.get("orgLevel");//机构级别，多个逗号分隔
        if(paramMap!=null) orgTypeTmp = paramMap.get("orgType");//机构类型，多个逗号分隔
        
        String orgLevel = orgLevelTmp;
        String orgType = orgTypeTmp;
        OrgHelper.getOrgs(GlobalShareContextHolder.getOrgId()).forEach(org -> {
            if(!StringUtils.isEmpty(orgLevel)) {
                boolean flag = false;
                for(String level : orgLevel.split(",")) {
                    if(level.equals(org.getOrgLevel())) {
                        flag = true;
                    }
                }
                if(!flag)//匹配不上就不处理
                    return;
            }
            
            if(!StringUtils.isEmpty(orgType)) {
                boolean flag = false;
                for(String type : orgType.split(",")) {
                    if(type.equals(org.getOrgType())) {
                        flag = true;
                    }
                }
                if(!flag)//匹配不上就不处理
                    return;
            }
            
            Item item = new Item();
            item.setId(org.getOrgId());
            item.setName(org.getOrgName());
            item.setParent(org.getParentOrgId());
            items.add(item);
        });
        return items;
    }
}
