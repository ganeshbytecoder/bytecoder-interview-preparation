package com.gschoudhary.part1.fileprocessing.csvfine;

//import com.google.common.collect.Lists;

import com.gschoudhary.utils.BasicConfiguration;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

@Component
@Configuration
@EnableSwagger2
@EnableBatchProcessing
public class BeanConfiguration {

    private final BasicConfiguration basicConfiguration;

    @Value("org/springframework/batch/core/schema-drop-mysql.sql")
    private Resource dropReopsitoryTables;

    @Value("org/springframework/batch/core/schema-mysql.sql")
    private Resource dataReopsitorySchema;


    @Autowired
    public BeanConfiguration(BasicConfiguration basicConfiguration) {
        this.basicConfiguration = basicConfiguration;
    }



    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(basicConfiguration.getDATABASE_URL())
                .username(basicConfiguration.getDATABASE_USERNAME())
                .password(basicConfiguration.getDATABASE_PASSWORD())
                .build();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) throws MalformedURLException, FileNotFoundException {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        Resource resource = new ClassPathResource("/data/initData.sql");
//        databasePopulator.addScript(resource);
//        databasePopulator.addScript(dropReopsitoryTables);
//        databasePopulator.addScript(dataReopsitorySchema);
        databasePopulator.setIgnoreFailedDrops(true);
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator);
        return initializer;
    }


    @Bean
    public BatchConfigurer batchConfigurer(DataSource dataSource) {

        return new DefaultBatchConfigurer(dataSource) {
            @Override
            public JobLauncher getJobLauncher() {
                SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                jobLauncher.setJobRepository(this.getJobRepository());
                jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
                try {
                    jobLauncher.afterPropertiesSet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jobLauncher;
            }



            @Override
            public JobRepository createJobRepository() throws Exception {
                JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
                factory.setDataSource(dataSource);
                factory.setTransactionManager(getTransactionManager());
                factory.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE");
                factory.setTablePrefix("BATCH_");
                factory.setMaxVarCharLength(1000);
                return factory.getObject();
            }
        };




    }


}
