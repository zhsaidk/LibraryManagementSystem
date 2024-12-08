package com.zhsaidk.mapper;

import com.zhsaidk.database.Entity.Person;
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
    public PersonReadDto map(Person from) {
        return new PersonReadDto(
                from.getId(),
                from.getFirstname(),
                from.getLastname(),
                from.getEmail(),
                from.getPhoneNumber(),
                from.getRole(),
                from.getBirthDate(),
                Optional.ofNullable(from.getBooks())
                        .orElse(List.of())
                        .stream().map(bookReadMapper::map)
                        .toList()
        );
    }
}
