package com.gschoudhary.service;

import com.gschoudhary.domain.BookEntity;
import com.gschoudhary.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl {

    @Autowired
    private BookRepository bookRepository;


    public void saveBook(){
        BookEntity bookEntity = BookEntity.builder()
                .bookAuthor("bookRecord.getBookAuthor()")
                .bookFormat("bookRecord.getBookFormat()")

                .build();
        bookEntity=bookRepository.save(bookEntity);
    }
}
