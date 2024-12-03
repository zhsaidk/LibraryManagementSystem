package com.zhsaidk.mapper;

import com.zhsaidk.database.Entity.Book;
import com.zhsaidk.dto.BookReadDto;
import org.springframework.stereotype.Component;

@Component
public class BookReadMapper implements Mapper<Book, BookReadDto>{
    @Override
    public BookReadDto map(Book book) {
        return new BookReadDto(
                book.getId(),
                book.getTitle(),
                book.getYear()
        );
    }
}
