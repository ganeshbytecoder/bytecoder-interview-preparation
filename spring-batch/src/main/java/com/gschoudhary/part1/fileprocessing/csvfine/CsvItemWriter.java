package com.gschoudhary.part1.fileprocessing.csvfine;

import com.gschoudhary.domain.BookEntity;
import com.gschoudhary.domain.BookRepository;
import com.gschoudhary.dto.BookRecord;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CsvItemWriter implements ItemWriter<BookRecord> {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public synchronized void write(List<? extends BookRecord> list) throws Exception {
        for (BookRecord bookRecord : list) {
            System.out.println(" writing records " );
            BookEntity bookEntity = BookEntity.builder()
                    .id(10L)
                    .bookAuthor(bookRecord.getBookAuthor())
                    .bookFormat(bookRecord.getBookFormat())
                    .bookISBN(bookRecord.getBookISBN())
                    .bookName(bookRecord.getBookName())
                    .build();
//            bookEntity = bookRepository.save(bookEntity);
            System.out.println(bookEntity.toString());
        }
    }
}
