package com.amarsoft.app.ems.system.job;

import java.util.concurrent.TimeUnit;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amarsoft.amps.abbc.listener.JobLogExecutionListener;
import com.amarsoft.amps.abbc.listener.StepLogExecutionListener;
import com.amarsoft.amps.abbc.validator.JobDateParametersValidator;
import com.amarsoft.amps.mscr.refresh.RefreshCacheAll;

import lombok.extern.slf4j.Slf4j;

/**
 * 缓存刷新 
 * 
 * @author sma4 
 */
@Slf4j
@Configuration
public class CacheRefreshJobConfig {
    private final String stepName_CacheRefresh = "cacheRefreshStep";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobRegistry jobRegistry;
    @Autowired
    private RefreshCacheAll refreshCacheAll;
    @Autowired
    JobLogExecutionListener jobLogExecutionListener; 
    @Autowired
    private StepLogExecutionListener stepLogExecutionListener;
    @Autowired
    JobDateParametersValidator jobDateParametersValidator;
    
    @Bean
    public Job cacheRefreshJob() throws DuplicateJobException {
        Job job = jobBuilderFactory.get("cacheRefreshJob")
                .listener(jobLogExecutionListener)
                .validator(jobDateParametersValidator)
                .start(cacheRefreshStep())
                .build();
        jobRegistry.register(new ReferenceJobFactory(job));
        return job;
    }

    @Bean
    public Step cacheRefreshStep() {
        return stepBuilderFactory.get(stepName_CacheRefresh)
                .allowStartIfComplete(true)
                .listener(stepLogExecutionListener)
                .tasklet(cacheRefreshTasklet())
                .build();
    }
    
    public Tasklet cacheRefreshTasklet() {
        return (contribution, chunkContext) -> {
            refreshCacheAll.refreshCache();
            TimeUnit.SECONDS.sleep(60);//等待60秒，其他服务刷新缓存
            return RepeatStatus.FINISHED;
        };
    }
}
