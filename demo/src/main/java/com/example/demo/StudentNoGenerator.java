package com.example.demo;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StudentNoGenerator implements IKeyGenerator {

    @Value("${server.worker-id}")
    private Integer workerId;

    @Value("${server.data-center-id}")
    private Integer dataCenterId;

    @Override
    public String executeSql(String incrementerName) {
        int i = workerId + dataCenterId;
        return i+"";
    }
}
