package com.zhsaidk.http.controller;

import com.zhsaidk.Service.PersonService;
import com.zhsaidk.database.Entity.Role;
import com.zhsaidk.database.repository.PersonRepository;
import com.zhsaidk.dto.PersonCreateEditDto;
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
    private final PersonRepository personRepository;

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
    public String update(PersonCreateEditDto personCreateEditDto,
                         @PathVariable("id") Long id) {
        return "redirect:/persons/" + personService.update(id, personCreateEditDto).getId();
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        if(!personService.deleteById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/persons";
    }
}
