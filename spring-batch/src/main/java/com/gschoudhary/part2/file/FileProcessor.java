package com.gschoudhary.part2.file;

import org.springframework.batch.item.ItemProcessor;

public class FileProcessor implements ItemProcessor<String,String> {



    @Override
    public String process(String o) throws Exception {
        System.out.println(o);
        return o;
    }
}
