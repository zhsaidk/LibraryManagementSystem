package com.zhsaidk.mapper;

import com.zhsaidk.database.Entity.Book;
import com.zhsaidk.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookReadMapper implements Mapper<Book, BookDto>{
    @Override
    public BookDto map(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getYear(),
                book.getAuthor()
        );
    }
}
