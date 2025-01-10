package com.zhsaidk.integration.Service;

import com.zhsaidk.Service.AuthorService;
import com.zhsaidk.Service.BookService;
import com.zhsaidk.Service.PersonService;
import com.zhsaidk.database.Entity.Author;
import com.zhsaidk.database.Entity.Book;
import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.database.repository.AuthorRepository;
import com.zhsaidk.database.repository.BookRepository;
import com.zhsaidk.database.repository.PersonRepository;
import com.zhsaidk.dto.BookCreateEditDto;
import com.zhsaidk.dto.BookDto;
import com.zhsaidk.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@Transactional
class BookServiceIT {
    private final BookService bookService;
    private final BookRepository bookRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    private static final Long BOOK_ID = 1L;
    private static final Long AUTHOR_ID = 1L;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @Test
    void findFreeBooks() {
        List<Book> freeBooks = bookRepository.findFreeBooks();

        freeBooks.forEach(book->{
            assertNull(book.getPerson());
        });
    }

    @Test
    void assignBook(){
        bookService.assignBook(1L, 1L);

        Person person = personRepository.findById(1L)
                        .orElseThrow(()->new IllegalArgumentException("Person not found!"));
        Book book = bookRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Book not found!"));

        assertTrue(person.getBooks().contains(book));
    }

    @Test
    void findNotFreeBooks(){
        List<Book> books = bookService.findNotFreeBooks();
        assertFalse(books.isEmpty(), "Список не должен быть пустым");
        books.forEach(book -> {
            assertNotNull(book.getPerson(), "Книга должна быть назначена пользователю ");
        });
    }

    @Test
    void findAllBooks(){
        List<BookDto> allBooks = bookService.findAllBooks();

        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
    }

    @Test
    void findBookById(){
        BookDto book = bookService.findBookById(BOOK_ID);

        assertNotNull(book);

        assertEquals("Война и мир", book.getTitle());
        assertEquals(1863, book.getYear());
        assertEquals("Лев Толстой", book.getAuthor().getName());
    }

    @Test
    void create(){
        Author author = authorRepository.findById(AUTHOR_ID)
                .orElseThrow(() -> new IllegalArgumentException("AuthorNotFound!"));

        Book book = new Book();
        book.setTitle("TestBook");
        book.setYear(2002);
        book.setAuthor(author);
        bookRepository.save(book);

        Book testBook = bookRepository.findByTitle("TestBook")
                .orElse(null);

        assertNotNull(testBook);
        assertEquals("TestBook", testBook.getTitle());
    }

    @Test
    void update(){
        Book originalBook = bookRepository.findById(BOOK_ID)
                .orElseThrow(() -> new IllegalArgumentException("BookNotFound!"));

        assertEquals("Война и мир", originalBook.getTitle());
        assertEquals(1863, originalBook.getYear());

        BookCreateEditDto bookCreateEditDto = new BookCreateEditDto("UpdatedBook", 2003, 1L);

        bookService.update(BOOK_ID, bookCreateEditDto);

        Book book = bookRepository.findById(BOOK_ID)
                .orElse(null);


        assertNotNull(book);
        assertEquals("UpdatedBook", book.getTitle(), "Название не изменился!");
        assertEquals(2003, book.getYear(), "Год не изменился!");
    }

    @Test
    void deleteById(){
        bookService.deleteById(BOOK_ID);

        Book book = bookRepository.findById(BOOK_ID)
                .orElse(null);

        assertNull(book);
    }
}























































