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
 * 整个系统进入日终，设置batchFlag为日终
 * @author sma4
 */
@Configuration
public class ToEODJobConfig {
    private final String stepName_ToEOD = "toEODStep";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLogExecutionListener jobLogExecutionListener;
    @Autowired
    private StepLogExecutionListener stepLogExecutionListener;
    @Autowired
    JobDateParametersValidator jobDateParametersValidator;
    @Autowired
    private SystemBatchService systemBatchService;
    @Autowired
    JobRegistry jobRegistry;
    
    @Bean
    public Job toEODJob() throws DuplicateJobException {
        Job job = jobBuilderFactory.get("toEODJob")
                .listener(jobLogExecutionListener)
                .validator(jobDateParametersValidator)
                .start(toEODStep())
                .build();
        jobRegistry.register(new ReferenceJobFactory(job));
        return job;
    }
    
    /**
    * 日切Step实例执行过程
    * */
    @Bean
    public Step toEODStep() {
        return stepBuilderFactory.get(stepName_ToEOD)
                .listener(stepLogExecutionListener)
                .tasklet(toEODTasklet())
                .build();
    }
    
    public Tasklet toEODTasklet() {
        return (contribution, chunkContext) -> {
            String batchDate = DateHelper.getBatchDate();
            systemBatchService.batchFlagUpdate(BatchFlag.EOD.id,batchDate);
            return RepeatStatus.FINISHED;
        };
    }
}
