package com.amarsoft.app.ems.train;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.amarsoft.amps.arpe.annotation.EnableHistoryDataAutoTransfer;
 
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.amarsoft")
@ServletComponentScan("com.amarsoft")
@EnableScheduling
@EnableBatchProcessing
@EnableHistoryDataAutoTransfer
@EnableFeignClients("com.amarsoft")
@EnableJpaAuditing(dateTimeProviderRef="auditingDateTimeProvider")
@EnableCaching
public class TrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainApplication.class, args);
    }
}
