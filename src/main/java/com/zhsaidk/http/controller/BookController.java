package com.zhsaidk.http.controller;

import com.zhsaidk.Service.BookService;
import com.zhsaidk.Service.PersonService;
import com.zhsaidk.database.Entity.Book;
import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.database.repository.BookRepository;
import com.zhsaidk.database.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final PersonService personService;
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;

    @GetMapping
    public String books(Model model){
        model.addAttribute("books", bookService.findAllBooks());
        System.out.println();
        return "book/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") Long id,
                          Model model){
        model.addAttribute("book", bookService.findBookByIdForWeb(id));
        model.addAttribute("persons", personService.findAllPersons());
        return "book/book";
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") Long id,
                             @RequestParam(value = "personId", required = false) Long personId){
        if (personId!=null){
            Person person = personRepository.findById(personId)
                    .orElseThrow(() -> new IllegalArgumentException("UserNotFound!"));
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("BookNotFound"));

            book.setPerson(person);
            bookRepository.saveAndFlush(book);
        }
        else{
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("BookNotFound"));
            book.setPerson(null);
            bookRepository.saveAndFlush(book);
        }

        return "redirect:/books/" + id;
    }

}
