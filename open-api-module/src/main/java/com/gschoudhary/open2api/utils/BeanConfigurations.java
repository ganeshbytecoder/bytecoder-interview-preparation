package com.gschoudhary.open2api.utils;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Configuration
public class BeanConfigurations {


    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new RequestInterceptor())
                .build();
    }

//    @Bean
//    @Primary
////    @Profile({"local", "prod", "dev", "default"})
//    public DataSource dataSource() {
//        return DataSourceBuilder.create()
//                .url("jdbc:mysql://172.18.0.3:3306/testdb")
//                .username("root")
//                .password("root")
//                .build();
//    }
}
