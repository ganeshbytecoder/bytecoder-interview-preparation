package com.gschoudhary.controller;

import com.gschoudhary.constant.RequestMessageConstants;
import com.gschoudhary.part1.fileprocessing.csvfine.CsvJobConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.repository.dao.ExecutionContextDao;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.batch.core.repository.dao.StepExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class JobRestController {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private JobRepository jobRepository;

    private JobInstanceDao jobInstanceDao;
    private JobExecutionDao jobExecutionDao;
    private StepExecutionDao stepExecutionDao;
    private ExecutionContextDao ecDao;

    @Autowired
    JobExplorer jobExplorer;

    @Autowired
    CsvJobConfig csvJobConfig;


    @ApiOperation(value = "getAllJobs api")
    @GetMapping(path = "api/jobs", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getJobs(@RequestParam(value = "jobName", required = false) String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", RequestMessageConstants.HEALTH_CHECK_MESSAGE.getMessage());
        map.put("Jobnames", jobExplorer.getJobNames().toString());
        if (jobName != null) {
            map.put("JonInstances", jobExplorer.findJobInstancesByJobName(jobName, 0, 10).toString());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "getJobExecutionSummary by executionId api")
    @GetMapping(path = "api/jobs/{jobInstanceId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getJob(@PathVariable("jobInstanceId") long jobInstanceId, @RequestParam(value = "executionId", required = false) Long executionId) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, NoSuchJobInstanceException, NoSuchJobExecutionException {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", RequestMessageConstants.HEALTH_CHECK_MESSAGE.getMessage());
        map.put("jobExecutionInstanceIds", jobOperator.getExecutions(jobInstanceId).toString());
        if (executionId != null) {
            map.put("jobExecution", jobOperator.getSummary(executionId).toString());
            JobExecution jobExecution = jobExplorer.getJobExecution(executionId);
            map.put("stepExecution", jobExecution.getStepExecutions().toString());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "createJobExecutionContext api")
    @PostMapping(path = "api/jobs/{job}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity createJob(@PathVariable("job") String job) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("create job execution");
        HashMap<String, String> map = new HashMap<>();
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addString("file", "/data/FailedBook.csv");
        builder.addDate("date", new Date());
        Job job1 = applicationContext.getBean(job, Job.class);
        jobLauncher.run(job1, builder.toJobParameters());
        map.put("status", RequestMessageConstants.HEALTH_CHECK_MESSAGE.getMessage());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "createJobExecutionContext api")
    @PostMapping(path = "api/restart-job/{jobInstanceId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity restartJob(@PathVariable("jobInstanceId") long jobInstanceId) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, NoSuchJobException, NoSuchJobExecutionException {
        System.out.println("restart job execution");

        HashMap<String, String> map = new HashMap<>();
        Job job1 = applicationContext.getBean("csvJob", Job.class);
        JobExecution jobExecution = jobExplorer.getJobExecution(jobInstanceId);
        assert jobExecution != null;
        JobParameters parameters = jobExecution.getJobParameters();

        System.out.println("restart id " + parameters);
        jobLauncher.run(job1, parameters);
//        jobOperator.restart(jobInstanceId);
//        JobExecution jobExecution = this.findExecutionById(executionId);
//        String jobName = jobExecution.getJobInstance().getJobName();
//        Job job = this.jobRegistry.getJob(jobName);
//        JobParameters parameters = jobExecution.getJobParameters();



//        final JobExecution restartExecution = jobExplorer.getJobExecution(restartId);
//        map.put("status", restartExecution.getStatus().toString());
//        this.logger.info("Checking status of job execution with id=" + executionId);
//        JobExecution jobExecution = this.findExecutionById(executionId);
//        String jobName = jobExecution.getJobInstance().getJobName();
//        Job job = this.jobRegistry.getJob(jobName);
//        JobParameters parameters = jobExecution.getJobParameters();
//        this.logger.info(String.format("Attempting to resume job with name=%s and parameters=%s", jobName, parameters));
//
//        try {
//            return this.jobLauncher.run(job, parameters).getId();
//        } catch (JobExecutionAlreadyRunningException var8) {
//            throw new UnexpectedJobExecutionException(String.format("Illegal state (only happens on a race condition): %s with name=%s and parameters=%s", "job execution already running", jobName, parameters), var8);
//        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "getJobExecutionContext api")
    @PostMapping(path = "api/jobs/{executionId}/execute", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity executeJob(@PathVariable("executionId") long executionId) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, NoSuchJobInstanceException, NoSuchJobExecutionException {
        HashMap<String, String> map = new HashMap<>();
        System.out.println("executing job " + executionId);
        startJob(executionId);
        map.put("status", RequestMessageConstants.HEALTH_CHECK_MESSAGE.getMessage());
        map.put("details", "Starting");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Async
    public void startJob(Long executionId) {
        System.out.println("starting job");
        JobExecution jobExecution = jobExplorer.getJobExecution(executionId);
        assert jobExecution != null;
        Job job = applicationContext.getBean(jobExecution.getJobInstance().getJobName(), Job.class);
        job.execute(jobExecution);
    }


}
