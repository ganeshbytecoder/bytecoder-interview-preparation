package com.gschoudhary.part1.fileprocessing.stringline;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;

@Component("stringFileReader")
public class StringFileReader implements ItemReader<String> {
    public static int value = 0;
    BufferedReader inputStream = null;


    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        Resource resource = new ClassPathResource("/data/initData.sql");
        File file = resource.getFile();
        if (inputStream == null) {
            inputStream = new BufferedReader(new java.io.FileReader(file));
        }
        return inputStream.readLine();
    }
}
