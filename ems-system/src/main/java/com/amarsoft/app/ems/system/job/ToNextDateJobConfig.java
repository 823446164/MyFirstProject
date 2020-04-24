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
 * 日期切换到下一天，修改BusinessDate为下一天
 * 
 * @author sma4
 */
@Configuration
public class ToNextDateJobConfig {
    private final String stepName_ToNextDate = "toNextDateStep";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLogExecutionListener iobLogExecutionListener;
    @Autowired
    private StepLogExecutionListener stepLogExecutionListener;
    @Autowired
    JobDateParametersValidator jobDateParametersValidator;
    @Autowired
    private SystemBatchService systemBatchService;
    @Autowired
    JobRegistry jobRegistry;
    
    @Bean
    public Job toNextDateJob() throws DuplicateJobException {
        Job job = jobBuilderFactory.get("toNextDateJob")
                .listener(iobLogExecutionListener)
                .validator(jobDateParametersValidator)
                .start(toNextDateStep())
                .build();
        jobRegistry.register(new ReferenceJobFactory(job));
        return job;
    }
    
    /**
    * 日切Step实例执行过程
    * */
    @Bean
    public Step toNextDateStep() {
        return stepBuilderFactory.get(stepName_ToNextDate)
                .listener(stepLogExecutionListener)
                .tasklet(toNextDateTasklet())
                .build();
    }
    
    public Tasklet toNextDateTasklet() {
        return (contribution, chunkContext) -> {
            String businessDate = DateHelper.getBusinessDate();
            String batchDate = DateHelper.getBatchDate();
            String newBusinessDate = DateHelper.getRelativeDate(businessDate, TermUnit.Day.id, 1);
            systemBatchService.businessDateUpdate(newBusinessDate,batchDate);
            return RepeatStatus.FINISHED;
        };
    }
}
