package com.gschoudhary.part2.file;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;


public class FileReader implements ItemReader<String> {
    public static int value =0;
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(FileReader.value ==10){
            return null;
        }
        FileReader.value++;
        System.out.println("reader");
        return "reader";
    }
}
