package com.zhsaidk.Service;

import com.zhsaidk.database.Entity.Book;
import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.database.repository.BookRepository;
import com.zhsaidk.database.repository.PersonRepository;
import com.zhsaidk.dto.BookReadDto;
import com.zhsaidk.mapper.BookReadMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookReadMapper bookReadMapper;
    private final PersonService personService;
    private final PersonRepository personRepository;

    public List<BookReadDto> findFreeBooks(){
        return bookRepository.findFreeBooks()
                .stream()
                .map(bookReadMapper::map)
                .toList();
    };

    public void assignBook(Long bookId, Long personId){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("UserNotFound!"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("BookNotFound!"));

        book.setPerson(person);

        bookRepository.saveAndFlush(book);
    }


    public List<Book> findNotFreeBooks(){
        return bookRepository.findNotFreeBooks();
    }

    public void unassignBook(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found!"));

        book.setPerson(null);
        bookRepository.saveAndFlush(book);

    }


    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Book findBookById(Long id){
        return Optional.of(bookRepository.findById(id))
                .get()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
