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
import com.amarsoft.aecd.acct.constant.TermUnit;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.service.SystemBatchService;

/**
 * 结束整个批量，将BatchDate加一天
 * @author sma4
 */
@Configuration
public class EndBatchJobConfig {
    private final String stepName_endBatch = "endBatchStep";
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
    public Job endBatchJob() throws DuplicateJobException {
        Job job = jobBuilderFactory.get("endBatchJob")
                .listener(jobLogExecutionListener)
                .validator(jobDateParametersValidator)
                .start(endBatchStep())
                .build();
        jobRegistry.register(new ReferenceJobFactory(job));
        return job;
    }

    @Bean
    public Step endBatchStep() {
        return stepBuilderFactory.get(stepName_endBatch)
                .listener(stepLogExecutionListener)
                .tasklet(endBatchTasklet())
                .build();
    }
    
    public Tasklet endBatchTasklet() {
        return (contribution, chunkContext) -> {
            String batchDate = DateHelper.getBatchDate();
            String newBatchDate = DateHelper.getRelativeDate(batchDate, TermUnit.Day.id, 1);
            systemBatchService.batchDateUpdate(newBatchDate);
            return RepeatStatus.FINISHED;
        };
    }
}
