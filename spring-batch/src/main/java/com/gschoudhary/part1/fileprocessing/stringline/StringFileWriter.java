package com.gschoudhary.part1.fileprocessing.stringline;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("stringFileWriter")
public class StringFileWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> list) throws Exception {
        for (String s:list) {
            System.out.println("writer " +s);
        }
    }
}
