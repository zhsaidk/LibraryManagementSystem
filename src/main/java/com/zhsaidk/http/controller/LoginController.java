package com.zhsaidk.http.controller;

import com.zhsaidk.Service.PersonService;
import com.zhsaidk.database.Entity.Role;
import com.zhsaidk.dto.PersonCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final PersonService personService;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("roles", Role.values());

        return "account/registration";
    }

    @PostMapping("/registration")
    public String create(PersonCreateEditDto personCreateEditDto) {
        return "redirect:/persons/" + personService.create(personCreateEditDto).getId();
    }
}
