package com.bytecoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bytecoder.App","com.gschoudhary.open2api"})
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "bytecoder apis!");
        SpringApplication.run(App.class, args);

    }
}
