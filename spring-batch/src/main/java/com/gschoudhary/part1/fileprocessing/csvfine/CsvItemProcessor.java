package com.gschoudhary.part1.fileprocessing.csvfine;

import com.gschoudhary.dto.BookRecord;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Scope(value = "step")
public class CsvItemProcessor implements ItemProcessor<BookRecord, BookRecord> {

    StepExecution stepExecution;

    JobExecution jobExecution;

//    @BeforeStep
//    public void saveStepExecution(StepExecution stepExecution, JobExecution jobExecution) {
//        this.stepExecution = stepExecution;
//        this.jobExecution = jobExecution;
//    }

    @Value("#{jobParameters['date']}")
    private String date;

    @Override
    public BookRecord process(BookRecord bookRecord) throws Exception {
        System.out.println("params " + date + " record " + bookRecord);
//        System.out.println(stepExecution.getExecutionContext().toString());
//        System.out.println(" JobExecution jobExecution)" + jobExecution.getExecutionContext().toString());
        return bookRecord;
    }
}
