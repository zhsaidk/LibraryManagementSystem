package com.zhsaidk.mapper;

import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.dto.BookReadDto;
import com.zhsaidk.dto.PersonReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonReadMapper implements Mapper<Person, PersonReadDto> {
    private final BookReadMapper bookReadMapper;


    @Override
    public PersonReadDto map(Person person) {
        List<BookReadDto> books = Optional.ofNullable(person.getBooks())
                .orElse(List.of())
                .stream()
                .map(bookReadMapper::map)
                .toList();



        return new PersonReadDto(
                person.getId(),
                person.getFirstname(),
                person.getLastname(),
                person.getEmail(),
                person.getPhoneNumber(),
                null,
                person.getRole(),
                person.getBirthDate(),
                books
        );
    }


}
