package com.zhsaidk.mapper;

import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.dto.PersonCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonCreateEditMapper implements Mapper<PersonCreateEditDto, Person>{
    private final PasswordEncoder passwordEncoder;

    @Override
    public Person map(PersonCreateEditDto from) {
        Person person = new Person();
        Person copy = copy(from, person);
        copy.setPassword(passwordEncoder.encode(from.getPassword()));
        return copy;
    }

    private Person copy(PersonCreateEditDto from, Person to){
        to.setFirstname(from.getFirstname());
        to.setLastname(from.getLastname());
        to.setEmail(from.getEmail());
        to.setPhoneNumber(from.getPhoneNumber());
        to.setRole(from.getRole());
        to.setBirthDate(from.getBirthDate());
        return to;
    }

    @Override
    public Person map(PersonCreateEditDto from, Person to) {
        copy(from, to);
        return to;
    }
}
