package com.zhsaidk.controller.rest;

import com.zhsaidk.Service.PersonService;
import com.zhsaidk.dto.PersonCreateEditDto;
import com.zhsaidk.dto.PersonReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController("/rest/persons")
@RequiredArgsConstructor
public class PersonRestController {
    private final PersonService personService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonReadDto> findAll(){
        return personService.findAllPersons();
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonReadDto> findById(@PathVariable Long id) {
        return Optional.ofNullable(personService.findById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<PersonReadDto> update(@RequestBody PersonCreateEditDto personCreateEditDto,
                         @PathVariable("id") Long id) {

        return Optional.ofNullable(personService.update(id, personCreateEditDto))
                .map(ResponseEntity::ok)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonNotFound"));
    }

    public ResponseEntity<PersonReadDto> create(@RequestBody PersonCreateEditDto personCreateEditDto){
        personService.create(personCreateEditDto);
        return null;
    }

}





























