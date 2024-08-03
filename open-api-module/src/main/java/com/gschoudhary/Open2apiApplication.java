package com.gschoudhary;

import com.gschoudhary.Bytecoder.RolesService;
import com.gschoudhary.open2api.domain.ApiConfigEntity;
import com.gschoudhary.open2api.domain.AuthConfigEntity;
import com.gschoudhary.open2api.enums.AuthType;
import com.gschoudhary.open2api.enums.MethodType;
import com.gschoudhary.open2api.repository.ApiConfigRepository;
import com.gschoudhary.open2api.repository.AuthConfigRepository;
import com.gschoudhary.open2api.restcontroller.router.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Open2apiApplication {
    @Autowired
    private ApiConfigRepository apiConfigRepository;

    @Autowired
    private AuthConfigRepository authConfigRepository;


    @Autowired
    private static RolesService rolesService;


    public static void addRoutes() {
        Router.POST("/api/v1/codec", (c) -> "codec " + c);

        Router.POST("/api/v1/codea", (c) -> rolesService.addRole());

        Router.POST("/api/v1/codeb", (c) -> rolesService.getRoles());
    }


    private static final Logger log = LoggerFactory.getLogger(Open2apiApplication.class);

    public static void main(String[] args) {
        System.out.println("starting application");
        SpringApplication.run(Open2apiApplication.class, args);
        addRoutes();
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            // save a couple of apiConfigEntity1
            ApiConfigEntity apiConfigEntity1 = ApiConfigEntity.builder()
                    .id(1L)
                    .authApiCode("1010")
                    .apiUniqueCode("101")
                    .mediaType("application/json")
                    .apiUrl("http://localhost:8080/app/test/one-api")
                    .jsonObject("")
                    .methodType(MethodType.POST)
                    .authType(AuthType.OAUTH2)
                    .build();
            apiConfigRepository.save(apiConfigEntity1);

            apiConfigEntity1 = ApiConfigEntity.builder()
                    .id(2L)
                    .authApiCode("1020")
                    .apiUniqueCode("102")
                    .mediaType("application/json")
                    .apiUrl("http://localhost:8080/app/test/one-api")
                    .jsonObject("")
                    .methodType(MethodType.POST)
                    .authType(AuthType.BASIC_AUTH)
                    .build();
            apiConfigRepository.save(apiConfigEntity1);
            apiConfigEntity1 = ApiConfigEntity.builder()
                    .id(3L)
                    .authApiCode("1030")
                    .apiUniqueCode("103")
                    .mediaType("application/json")
                    .apiUrl("http://localhost:8080/app/test/one-api")
                    .jsonObject("")
                    .methodType(MethodType.POST)
                    .authType(AuthType.NO_AUTH)
                    .build();
            apiConfigRepository.save(apiConfigEntity1);

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (ApiConfigEntity customer : apiConfigRepository.findAll()) {
                log.info(customer.toString());
            }

            // save a couple of authConfig
            AuthConfigEntity authConfig = AuthConfigEntity.builder()
                    .id(1L)
                    .mediaType("application/x-www-form-urlencoded")
                    .methodType(MethodType.POST)
                    .authType(AuthType.OAUTH2)
                    .jsonObject("grant_type=client_credentials&scope=read write&client_secret=090e0205ee913508d65f54fc2103dd5e&client_id=partner-samavesh")
                    .apiCode("1010")
                    .apiUrl("https://auth.uat1.kalfin.in/oauth/token")
                    .build();
            authConfigRepository.save(authConfig);

            authConfig = AuthConfigEntity.builder()
                    .id(2L)
                    .mediaType("application/x-www-form-urlencoded")
                    .methodType(MethodType.POST)
                    .authType(AuthType.BASIC_AUTH)
                    .jsonObject("username" + ":" + "password")
                    .apiCode("1020")
                    .apiUrl("https://auth.uat1.kalfin.in/oauth/token")
                    .build();
            authConfigRepository.save(authConfig);
            authConfig = AuthConfigEntity.builder()
                    .id(3L)
                    .mediaType("application/x-www-form-urlencoded")
                    .methodType(MethodType.POST)
                    .authType(AuthType.NO_AUTH)
                    .jsonObject("grant_type=client_credentials&scope=read write&client_secret=090e0205ee913508d65f54fc2103dd5e&client_id=partner-samavesh")
                    .apiCode("1030")
                    .apiUrl("https://auth.uat1.kalfin.in/oauth/token")
                    .build();
            authConfigRepository.save(authConfig);

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (AuthConfigEntity authConfig1 : authConfigRepository.findAll()) {
                log.info(authConfig1.toString());
            }


        };

    }
}
