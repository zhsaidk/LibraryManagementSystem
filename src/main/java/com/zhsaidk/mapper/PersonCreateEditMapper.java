package com.zhsaidk.mapper;

import com.zhsaidk.Service.ImageService;
import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.dto.PersonCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class PersonCreateEditMapper implements Mapper<PersonCreateEditDto, Person>{
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    @Override
    public Person map(PersonCreateEditDto from) {
        Person person = new Person();
        Person copy = copy(from, person);
        copy.setPassword(passwordEncoder.encode(from.getPassword()));

        recordImage(from, person);
        return copy;
    }

    @Override
    public Person map(PersonCreateEditDto from, Person to) {
        copy(from, to);
        recordImage(from, to);
        return to;
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

    public void recordImage(PersonCreateEditDto from, Person to){
        Optional.ofNullable(from.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(file -> {
                    to.setImage(file.getOriginalFilename());
                    try {
                        imageService.update(file.getOriginalFilename(), file.getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
