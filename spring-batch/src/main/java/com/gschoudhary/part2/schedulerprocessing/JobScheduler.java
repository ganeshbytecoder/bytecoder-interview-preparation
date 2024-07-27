//package com.batch.dashboard.application.part2.schedulerprocessing;
//
//import com.batch.dashboard.application.domain.JobSchedulerEntity;
//import com.batch.dashboard.application.domain.JobSchedulerRepository;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.explore.JobExplorer;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.JobOperator;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.List;
//
//@Component
//@EnableScheduling
//public class JobScheduler {
//
//    @Autowired
//    JobSchedulerRepository jobSchedulerRepository;
//    @Autowired
//    JobLauncher jobLauncher;
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    private JobOperator jobOperator;
//
//    @Autowired
//    private JobRepository jobRepository;
//
//    @Autowired
//    JobExplorer jobExplorer;
//
////    @Scheduled(fixedRate = 20000)
//    public void runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//
//        List<JobSchedulerEntity> jobSchedulerEntityList = jobSchedulerRepository.findAll();
//        for (JobSchedulerEntity jobSchedulerEntity : jobSchedulerEntityList) {
//            JobParametersBuilder builder = new JobParametersBuilder();
//            builder.addString("file", "/data/Book.csv");
//            builder.addDate("date", new Date());
//            Job job1 = applicationContext.getBean(jobSchedulerEntity.getJobName(), Job.class);
//            jobLauncher.run(job1, builder.toJobParameters());
//        }
//        System.out.println("running the job");
//    }
//}
