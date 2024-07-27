package com.gschoudhary.part1.fileprocessing.csvfine;

import com.gschoudhary.dto.BookRecord;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class CsvLineMapper implements LineMapper<BookRecord> {
    @Autowired
    FileLineTokenizer fileLineTokenizer;

    @Autowired
    BookRecordFieldSetMapper bookRecordFieldSetMapper;

    @Override
    public BookRecord mapLine(String s, int i) throws Exception {
        System.out.println("line no " + i + " str " + s);
        return bookRecordFieldSetMapper.mapFieldSet(fileLineTokenizer.tokenize(s));
    }

    public BookRecord getBookRecord(Row xlsRow, int line) throws BindException {
        System.out.println("line no " + line);

        String[] values = new String[CsvJobConfig.TOKENS.length];
        for (int i=0;i<values.length; i++) {
            xlsRow.getCell(i).setCellType(CellType.STRING);
            values[i] = xlsRow.getCell(i).getStringCellValue();
        }
        DefaultFieldSetFactory defaultFieldSetFactory = new DefaultFieldSetFactory();
        return bookRecordFieldSetMapper.mapFieldSet(defaultFieldSetFactory.create(values, CsvJobConfig.TOKENS));
    }
}
