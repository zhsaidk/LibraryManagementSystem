package com.zhsaidk.http.rest;

import com.zhsaidk.Service.PersonService;
import com.zhsaidk.dto.PersonCreateEditDto;
import com.zhsaidk.dto.PersonReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/persons")
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
        return ResponseEntity.ok(personService.findById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<PersonReadDto> update(@RequestBody PersonCreateEditDto personCreateEditDto,
                         @PathVariable("id") Long id) {

        return ResponseEntity.ok(personService.update(id, personCreateEditDto));
    }

    @PostMapping("/create")
    public ResponseEntity<PersonReadDto> create(@RequestBody PersonCreateEditDto personCreateEditDto){
        PersonReadDto createdUser = personService.create(personCreateEditDto);
        URI location = URI.create("/rest/persons/" + createdUser.getId());

        return ResponseEntity.created(location).body(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return personService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


}





























