//package com.batch.dashboard.application.part1.tasklet;
//
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
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableBatchProcessing
//public class CustomTaskletConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean(name = "taskletJob")
//    public Job job(@Qualifier("taskletStep2") Step step1, @Qualifier("notifyStep") Step step2) {
//
//        return jobBuilderFactory.get("taskletJob")
//                .start(step1)
//                .next(step2)
//                .build();
//    }
//
//    @Bean(name = "taskletStep2")
//    protected Step taskletStep(@Qualifier("tasklet") Tasklet tasklet) {
//        return stepBuilderFactory.get("taskletStep")
//                .tasklet(tasklet)
//                .build();
//    }
//
//    @Bean(name = "notifyStep")
//    protected Step notifyStep(@Qualifier("notifyTasklet") Tasklet tasklet) {
//        return stepBuilderFactory.get("notifyStep")
//                .tasklet(tasklet)
//                .build();
//    }
//
//    @Bean(name = "tasklet")
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
//
//    @Bean(name = "notifyTasklet")
//    public Tasklet notifyTasklet() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("Notifying after finishing task");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
//}