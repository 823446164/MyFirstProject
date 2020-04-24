package com.amarsoft.app.ems.system.date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import com.amarsoft.amps.arpe.date.DateQuery;
import com.amarsoft.app.ems.system.cs.client.SystemClient;


@Component
@ConditionalOnClass(DateQuery.class)
public class DefaultDateQuery implements DateQuery {

    @Autowired
    private SystemClient systemClient;
    
    
    @Override
    public String getBusinessDate() {
        return systemClient.businessDateQuery().getBody().getMessage().getBusinessDate();
    }

    @Override
    public String getProductDate() {
        return systemClient.productDateQuery().getBody().getMessage().getProductDate();
    }

    @Override
    public String getBatchDate() {
        return systemClient.batchDateQuery().getBody().getMessage().getBatchDate();
    }

    @Override
    public String getBatchFlag() {
        return systemClient.batchFlagQuery().getBody().getMessage().getBatchFlag();
    }

}
