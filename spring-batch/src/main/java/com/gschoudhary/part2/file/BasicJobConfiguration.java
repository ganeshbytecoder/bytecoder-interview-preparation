//package com.batch.dashboard.application.part2.file;
//
//import com.batch.dashboard.application.part1.fileprocessing.csvfine.JobListener;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableBatchProcessing
//public class BasicJobConfiguration {
//    @Autowired
//    private JobBuilderFactory jobs;
//
//    @Autowired
//    private StepBuilderFactory steps;
//
//    @Bean(name = "basicJob")
//    public Job job(@Qualifier("firstStep") Step step1, @Qualifier("secondStep") Step step2, JobListener jobListener) {
//
//        return jobs.get("basicJob")
//                .listener(jobListener)
//
//                .start(step1)
//
//                .next(step2)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FileReader fileReader() {
//        return new FileReader();
//    }
//
//    @Bean
//    @StepScope
//    public FileProcessor fileProcessor() {
//        return new FileProcessor();
//    }
//
//    @Bean
//    @StepScope
//    public FileWriter fileWriter() {
//        return new FileWriter();
//    }
//
//    @Bean
//    protected Step firstStep(FileReader fileReader, FileProcessor fileProcessor, FileWriter fileWriter) {
//        return steps.get("firstStep")
//                .<String, String>chunk(10)
//                .reader(fileReader)
//                .processor(fileProcessor)
//                .writer(fileWriter)
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    @Bean
//    protected Step secondStep(Tasklet tasklet) {
//        return steps.get("secondStep")
//                .tasklet(tasklet)
//                .build();
//    }
//
//    @Bean
//    public Tasklet tasklet() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                for (int i = 0; i < 100; i++) {
//                    System.out.println(i);
//                }
//                throw new RuntimeException("Running wrong step");
//            }
//        };
//    }
//}
