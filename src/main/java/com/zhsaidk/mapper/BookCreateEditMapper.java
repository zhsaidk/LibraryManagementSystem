package com.zhsaidk.mapper;

import com.zhsaidk.database.Entity.Author;
import com.zhsaidk.database.Entity.Book;
import com.zhsaidk.database.repository.AuthorRepository;
import com.zhsaidk.database.repository.BookRepository;
import com.zhsaidk.dto.BookCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookCreateEditMapper implements Mapper<BookCreateEditDto, Book>{
    private final AuthorRepository authorRepository;
    @Override
    public Book map(BookCreateEditDto from) {
        System.out.println(from.getAuthor_id());
        Author author = authorRepository.findById(from.getAuthor_id())
                .orElse(null);
        Book book = new Book();
        book.setTitle(from.getTitle());
        book.setYear(from.getYear());
        book.setAuthor(author);

        return book;
    }

    @Override
    public Book map(BookCreateEditDto from, Book to) {
        Author author = authorRepository.findById(from.getAuthor_id())
                .orElse(null);
        to.setTitle(from.getTitle());
        to.setYear(from.getYear());
        to.setAuthor(author);
        return to;
    }
}
