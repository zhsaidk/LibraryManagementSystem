package com.zhsaidk.controller.rest;

import com.zhsaidk.Service.BookService;
import com.zhsaidk.Service.PersonService;
import com.zhsaidk.database.Entity.Book;
import com.zhsaidk.dto.BookCreateEditDto;
import com.zhsaidk.dto.BookDto;
import com.zhsaidk.dto.PersonCreateEditDto;
import com.zhsaidk.dto.PersonReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> create(@RequestBody BookCreateEditDto bookCreateEditDto) {
        BookDto bookDto = bookService.create(bookCreateEditDto);
        URI uri = URI.create("/rest/books/" + bookDto.getId());

        return ResponseEntity.created(uri).body(bookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable("id") Long id,
                                          @RequestBody BookCreateEditDto bookCreateEditDto) {
        return ResponseEntity.ok(bookService.update(id, bookCreateEditDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return bookService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
