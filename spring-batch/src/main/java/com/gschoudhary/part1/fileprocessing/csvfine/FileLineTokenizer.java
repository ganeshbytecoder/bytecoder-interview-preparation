package com.gschoudhary.part1.fileprocessing.csvfine;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.stereotype.Component;

@Component
public class FileLineTokenizer implements LineTokenizer {


    @Override
    public FieldSet tokenize(String line) {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(CsvJobConfig.DELIMITER);
        delimitedLineTokenizer.setNames(CsvJobConfig.TOKENS);
        return delimitedLineTokenizer.tokenize(line);
    }
}
