package com.gschoudhary.controller;

import com.gschoudhary.constant.RequestMessageConstants;
import com.gschoudhary.domain.JobSchedulerRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
//@Api(value = "HealthCheck")
public class FileRestController {

    private final Logger logger = LoggerFactory.getLogger(HealthCheckRestController.class);
    ;
    JobLauncher jobLauncher;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private JobOperator jobOperator;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    JobExplorer jobExplorer;

    @Autowired
    JobSchedulerRepository jobSchedulerRepository;

    Job job;
    int value = 1;

//
//    /**
//     * Api to check health of application
//     *
//     * @return response body
//     */
//    @ApiOperation(value = "Health check api")
//    @GetMapping(path = ApiConstants.JOB_LAUNCHER, produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity checkHealth() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        logger.info("Health checking.");
//        HashMap<String, String> map = new HashMap<>();
//        JobParametersBuilder builder = new JobParametersBuilder();
//        builder.addDate("date", new Date());
//
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("sourceDir", "C://inputLocation")
//                .addString("destinationDir", "C://outputLocation" + value + 1).toJobParameters();
//        job = applicationContext.getBean("basicJob", Job.class);
//        JobExecution jobExecution = jobLauncher.run(job, builder.toJobParameters());
//
////        JobParametersBuilder paramsBuilder = new JobParametersBuilder();
////        jobLauncher.run(job, paramsBuilder.toJobParameters());
//
//        map.put("status", RequestMessageConstants.HEALTH_CHECK_MESSAGE.getMessage());
////        map.put("details", jobExecution.getStatus().name());
////        map.put("string", jobOperator.getJobNames().toString());
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Health check api")
//    @GetMapping(path = ApiConstants.JOB_OPERATOR, produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity getAllJobs(@PathVariable("id") long id) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, NoSuchJobExecutionException {
//        logger.info("Health checking.");
//        HashMap<String, String> map = new HashMap<>();
//        JobParameters jobParameters = new JobParameters();
//        job = applicationContext.getBean("basicJob", Job.class);
//        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
//        map.put("status", RequestMessageConstants.HEALTH_CHECK_MESSAGE.getMessage());
//        map.put("details", jobOperator.getSummary(id));
//        map.put("string", jobOperator.getJobNames().toString());
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }
//

    @ApiOperation(value = "scheduleJobExecutionContext api")
    @PostMapping(path = "api/jobs/schedule/{job}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity scheduleJob(@PathVariable("job") String job) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("schedule job execution");
        HashMap<String, String> map = new HashMap<>();
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addString("file", "/data/Book.csv");
        builder.addDate("schedule_date", new Date());
        Job job1 = applicationContext.getBean(job, Job.class);
        map.put("status", RequestMessageConstants.HEALTH_CHECK_MESSAGE.getMessage());
//
//        JobSchedulerEntity jobSchedulerEntity = JobSchedulerEntity.builder()
//                .jobName(job)
//                .status(JobStatus.SUBMITTED)
//                .build();
//        jobSchedulerRepository.saveAndFlush(jobSchedulerEntity);
//        JobInstance jobInstance = jobRepository.createJobInstance(job, builder.toJobParameters());
        JobExecution jobExecution = jobRepository.createJobExecution(job, builder.toJobParameters());
//        jobExecution.setStartTime(new Date(2022, 12,02));
//        jobRepository.updateExecutionContext(jobExecution);
        map.put("jobExecution", jobExecution.toString());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
