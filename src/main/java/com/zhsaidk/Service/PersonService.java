package com.zhsaidk.Service;

import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.database.dto.PersonDto;
import com.zhsaidk.database.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> findAllPersons(){
        List<Person> all = personRepository.findAll();
        System.out.println();
        return all;
    }
}
