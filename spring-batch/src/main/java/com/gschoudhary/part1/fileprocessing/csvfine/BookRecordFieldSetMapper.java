package com.gschoudhary.part1.fileprocessing.csvfine;

import com.gschoudhary.dto.BookRecord;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class BookRecordFieldSetMapper implements FieldSetMapper<BookRecord> {
    Gson gson = new Gson();

    public JSONObject getGson() throws IOException, ParseException {
        String input = "/data/FileMapper.json";
        JSONParser parser = new JSONParser();

        Resource resource = new ClassPathResource(input);
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(resource.getFile()));
        JSONObject jsonObjects = (JSONObject) jsonObject.get("excelMappings");
        Map<String, String> map = new HashMap<>();
        for (String s : CsvJobConfig.TOKENS) {
            String column = (String) jsonObjects.get(s);
        }


        return jsonObject;
    }

    @Override
    public BookRecord mapFieldSet(FieldSet fieldSet) throws BindException {
        try {
            JSONObject jsonObject = getGson();
//            BookRecord bookRecord = new BookRecord();
//            bookRecord.setBookName(fieldSet.readString(jsonObject.get(TOKENS[1]).get("bookname").toString()));
//            bookRecord.setBookAuthor(fieldSet.readString(jsonObject.get("bookauthor").toString()));
//            bookRecord.setBookFormat(fieldSet.readString(jsonObject.get("bookformat").toString()));
//            bookRecord.setBookISBN(fieldSet.readString(jsonObject.get("isbn").toString()));
//            System.out.println(fieldSet.readInt(jsonObject.get("publishyear").toString()));
//            bookRecord.setPublishingYear(fieldSet.readString(jsonObject.get("publishyear").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookName(fieldSet.readString("bookname"));
        bookRecord.setBookAuthor(fieldSet.readString("bookauthor"));
        bookRecord.setBookFormat(fieldSet.readString("bookformat"));
        bookRecord.setBookISBN(fieldSet.readString("isbn"));
        bookRecord.setPublishingYear(fieldSet.readString("publishyear"));
        return bookRecord;
    }
}
