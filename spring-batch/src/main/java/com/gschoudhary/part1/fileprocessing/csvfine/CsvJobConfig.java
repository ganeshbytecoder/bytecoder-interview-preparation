package com.gschoudhary.part1.fileprocessing.csvfine;

import com.gschoudhary.domain.BookRepository;
import com.gschoudhary.dto.BookRecord;
import com.gschoudhary.service.BookServiceImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class CsvJobConfig {
    public static String[] TOKENS = {"bookname", "bookauthor", "bookformat", "isbn", "publishyear"};

    public static String DELIMITER = ",";

    public static int SKIPPED_LINES = 1;

    @Autowired
    CsvLineMapper csvLineMapper;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Bean(name = "csvReader")
    @StepScope
    public FlatFileItemReader<BookRecord> csvItemReader(@Value("#{jobParameters['file']}") String filename) throws IOException {
        FlatFileItemReaderBuilder<BookRecord> builder = new FlatFileItemReaderBuilder<>();
        System.out.println("filename" + filename);
        String input = "/data/Book.csv";
        input= filename;
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        Resource resource = new ClassPathResource(input);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getFile()));
        String line = bufferedReader.readLine();
        TOKENS = line.split(DELIMITER);
        SKIPPED_LINES = 1;

        return builder
                .name("bookRecordItemReader")
                .resource(new ClassPathResource(input))
                .linesToSkip(SKIPPED_LINES)
                .delimited()
                .names(TOKENS)
                .lineMapper(csvLineMapper)
                .build();
        // @formatter:on
    }

    @Bean
    @StepScope
    public CsvItemProcessor bookItemProcessor() {
        return new CsvItemProcessor();
    }

    @Bean
    @StepScope
    public CsvItemWriter bookItemWriter() {
        return new CsvItemWriter();
    }

    @Bean(name = "csvStep")
    public Step csvStep(@Qualifier("csvReader") FlatFileItemReader<BookRecord> csvItemReader) {
        return stepBuilderFactory.get("csvStep")
                .<BookRecord, BookRecord>chunk(10)
                .reader(csvItemReader)
                .processor(bookItemProcessor())
                .writer(bookItemWriter())
//                .faultTolerant()
//                .skipLimit(2)
                .build();
    }

    @Bean(name = "nextStep")
    public Step nextStep() {
        return stepBuilderFactory.get("nextTaskletStep").tasklet(new CustomTasklet()).build();
    }

    @Bean(name = "csvJob")
    @Lazy
    public Job getCsvJob(@Qualifier("csvStep") Step csvStep) {
        return jobBuilderFactory.get("csvJob")
                .listener(new JobListener())
                .start(csvStep)
                .next(nextStep())
                .build();
    }


}
