package com.amarsoft.app.ems.system.job;

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
import com.amarsoft.aecd.acct.constant.BatchFlag;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.service.SystemBatchService;

/**
 * 系统进入日间，设置batchFlag为日间
 * @author sma4
 */
@Configuration
public class ToDailyJobConfig {
    private final String stepName_ToDaily = "toDailyStep";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobRegistry jobRegistry;
    @Autowired
    private SystemBatchService systemBatchService;
    @Autowired
    private JobLogExecutionListener jobLogExecutionListener; 
    @Autowired
    private StepLogExecutionListener stepLogExecutionListener;
    @Autowired
    JobDateParametersValidator jobDateParametersValidator;
    
    @Bean
    public Job toDailyJob() throws DuplicateJobException {
        Job job = jobBuilderFactory.get("toDailyJob")
                .listener(jobLogExecutionListener)
                .validator(jobDateParametersValidator)
                .start(toDailyStep())
                .build();
        jobRegistry.register(new ReferenceJobFactory(job));
        return job;
    }

    @Bean
    public Step toDailyStep() {
        return stepBuilderFactory.get(stepName_ToDaily)
                .listener(stepLogExecutionListener)
                .tasklet(toDailyTasklet())
                .build();
    }
    
    public Tasklet toDailyTasklet() {
        return (contribution, chunkContext) -> {
            String batchDate = DateHelper.getBatchDate();
            systemBatchService.batchFlagUpdate(BatchFlag.DAY.id,batchDate);
            return RepeatStatus.FINISHED;
        };
    }
}
