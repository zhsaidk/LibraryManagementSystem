package com.zhsaidk.Service;

import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.database.repository.PersonRepository;
import com.zhsaidk.dto.PersonCreateEditDto;
import com.zhsaidk.dto.PersonReadDto;
import com.zhsaidk.mapper.PersonCreateEditMapper;
import com.zhsaidk.mapper.PersonReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final PersonReadMapper personReadMapper;
    private final PersonCreateEditMapper personCreateEditMapper;

    public List<PersonReadDto> findAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(personReadMapper::map)
                .toList();

    }

    public PersonReadDto findById(Long id) {
        return personRepository.findById(id)
                .map(personReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PersonReadDto create(PersonCreateEditDto personCreateEditDto) {
        return Optional.ofNullable(personCreateEditDto)
                .map(personCreateEditMapper::map)
                .map(personRepository::saveAndFlush)
                .map(personReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public PersonReadDto update(Long id,
                                PersonCreateEditDto personCreateEditDto) {
        return personRepository.findById(id)
                .map(person -> {
                    personCreateEditMapper.map(personCreateEditDto, person);
                    personRepository.saveAndFlush(person);
                    return personReadMapper.map(person);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean deleteById(Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    personRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByEmail(username)
                .map(person -> new User(
                        person.getEmail(),
                        person.getPassword(),
                        Collections.singleton(person.getRole())
                ))
                .orElseThrow(()->new UsernameNotFoundException("UserNotFound!"));
    }

    public void save(Person person){
        personRepository.saveAndFlush(person);
    }
}
