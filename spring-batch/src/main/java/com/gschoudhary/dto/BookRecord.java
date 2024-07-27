package com.gschoudhary.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRecord {

    private String bookName;

    private String bookAuthor;

    private String bookFormat;

    private String bookISBN;

    private String publishingYear;

    @Override
    public String toString() {
        return "BookRecord [bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookFormat=" + bookFormat + ", bookISBN=" + bookISBN + ", publishingYear=" + publishingYear + "]";
    }
}
