//package com.batch.dashboard.application.part1.fileprocessing.stringline;
//
//
//import com.batch.dashboard.application.part1.fileprocessing.csvfine.JobListener;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//
//@Component
//@EnableBatchProcessing
//public class StringFileJobConfiguration {
//    @Autowired
//    private JobBuilderFactory jobs;
//
//    @Autowired
//    private StepBuilderFactory steps;
//
//    @Bean("stringFileJob")
//    public Job job(@Qualifier("stringFileStep") Step step1, @Qualifier("taskletStep") Step step2, JobListener jobListener) {
//
//        return jobs.get("stringFileJob2")
//                .listener(jobListener)
////                .preventRestart()
//                .start(step1)
//                .next(step2)
//                .build();
//    }
//
//
//    @Bean(name = "stringFileStep")
//    protected Step fileStep(@Qualifier("stringFileReader") StringFileReader fileReader, @Qualifier("stringFileProcessor") StringFileProcessor fileProcessor, @Qualifier("stringFileWriter") StringFileWriter fileWriter) {
//        return steps.get("stringFileStep")
//                .<String, String>chunk(10)
//                .reader(fileReader)
//                .processor(fileProcessor)
//                .writer(fileWriter)
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    @Bean(name = "taskletStep")
//    protected Step taskletStep(@Qualifier("simpleTasklet") Tasklet tasklet) {
//        return steps.get("taskletStep")
//                .tasklet(tasklet)
//                .build();
//    }
//
//    @Bean(name = "simpleTasklet")
//    public Tasklet tasklet() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                for (int i = 0; i < 100; i++) {
//                    System.out.println(i);
//                }
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
//}
