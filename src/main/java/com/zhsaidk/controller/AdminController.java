package com.zhsaidk.controller;

import com.zhsaidk.Service.BookService;
import com.zhsaidk.Service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final PersonService personService;
    private final BookService bookService;

    @GetMapping("/assign")
    public String assignPage(Model model){
        model.addAttribute("persons",personService.findAllPersons());
        model.addAttribute("books", bookService.findFreeBooks());

        return "admin/assign";
    }

    @PostMapping("/assign")
    public String assign(@RequestParam Long personId,
                        @RequestParam Long bookId){

        bookService.assignBook(bookId, personId);

        return "redirect:/assign";
    }

    @GetMapping("/unassign")
    public String unassignPage(Model model){

        model.addAttribute("books", bookService.findNotFreeBooks());
        return "admin/unassign";
    }

    @PostMapping("/unassign")
    public String unassignBook(@RequestParam Long bookId){

        bookService.unassignBook(bookId);
        return "redirect:/unassign";
    }
}
