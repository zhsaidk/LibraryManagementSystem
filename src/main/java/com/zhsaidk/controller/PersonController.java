package com.zhsaidk.controller;

import com.zhsaidk.Service.PersonService;
import com.zhsaidk.database.Entity.Role;
import com.zhsaidk.database.repository.PersonRepository;
import com.zhsaidk.dto.PersonReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(path = "/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("persons", personService.findAllPersons());
        return "person/persons";
    }

    @GetMapping("/{id}")
    public String findPersonById(@PathVariable("id") Long id,
                                 Model model) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("person", personService.findById(id));
        return "person/person";
    }



    @PostMapping("/{id}/update")
    public String update(PersonReadDto personReadDto,
                         @PathVariable("id") Long id,
                         Model model) {
        return "redirect:/persons/" + personService.update(id, personReadDto).getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        if(!personService.deleteById(id)){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return "redirect:/persons";
    }
}
