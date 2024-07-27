package com.gschoudhary.part1.fileprocessing.stringline;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("stringFileProcessor")
public class StringFileProcessor implements ItemProcessor<String,String> {

    @Override
    public String process(String str) throws Exception {
        Thread.sleep(1000);
        return "Processor " +str;
    }
}
