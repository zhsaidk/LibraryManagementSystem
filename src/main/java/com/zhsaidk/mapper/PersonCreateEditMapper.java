package com.zhsaidk.mapper;

import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.dto.PersonReadDto;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonCreateEditMapper implements Mapper<PersonReadDto, Person>{
    private final PasswordEncoder passwordEncoder;

    @Override
    public Person map(PersonReadDto obj) {
        Person person = new Person();
        return copy(obj, person);
    }

    @Override
    public Person map(PersonReadDto from, Person to) {
        return copy(from, to);
    }

    @NotNull
    private Person copy(PersonReadDto from, Person to) {
        to.setFirstname(from.getFirstname());
        to.setLastname(from.getLastname());
        to.setEmail(from.getEmail());
        to.setPhoneNumber(from.getPhoneNumber());
        to.setRole(from.getRole());
        to.setBirthDate(from.getBirthDate());
        return to;
    }
}
