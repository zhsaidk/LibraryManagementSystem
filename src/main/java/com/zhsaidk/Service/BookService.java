package com.zhsaidk.Service;

import com.zhsaidk.database.Entity.Book;
import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.database.repository.BookRepository;
import com.zhsaidk.database.repository.PersonRepository;
import com.zhsaidk.dto.BookCreateEditDto;
import com.zhsaidk.dto.BookDto;
import com.zhsaidk.mapper.BookCreateEditMapper;
import com.zhsaidk.mapper.BookReadMapper;
import lombok.RequiredArgsConstructor;
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
    private final BookCreateEditMapper bookCreateEditMapper;

    public List<BookDto> findFreeBooks() {
        return bookRepository.findFreeBooks()
                .stream()
                .map(bookReadMapper::map)
                .toList();
    }

    ;

    public void assignBook(Long bookId, Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("UserNotFound!"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("BookNotFound!"));

        book.setPerson(person);

        bookRepository.saveAndFlush(book);
    }


    public List<Book> findNotFreeBooks() {
        return bookRepository.findNotFreeBooks();
    }

    public void unassignBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found!"));

        book.setPerson(null);
        bookRepository.saveAndFlush(book);
    }


    public List<BookDto> findAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookReadMapper::map)
                .toList();
    }

    public BookDto findBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }// Для того что бы избежать циклической зависимости вернул BookDto


    public Book findBookByIdForWeb(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }// Для чтого чтобы из Thymeleaf достучится до person


    public BookDto create(BookCreateEditDto bookCreateEditDto) {
        return Optional.of(bookCreateEditDto)
                .map(bookCreateEditMapper::map)
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public BookDto update(Long id, BookCreateEditDto bookCreateEditDto) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookCreateEditMapper.map(bookCreateEditDto, book);
                    bookRepository.saveAndFlush(book);
                    return bookReadMapper.map(book);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean deleteById(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
