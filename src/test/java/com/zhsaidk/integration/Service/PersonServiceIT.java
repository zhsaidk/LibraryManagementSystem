package com.zhsaidk.integration.Service;

import com.zhsaidk.Service.PersonService;
import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.database.Entity.Role;
import com.zhsaidk.database.repository.PersonRepository;
import com.zhsaidk.dto.PersonCreateEditDto;
import com.zhsaidk.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@Transactional
@Rollback
class PersonServiceIT {
    private final PersonRepository personRepository;
    public static final Long USER_ID = 1L;
    @Autowired
    private PersonService personService;

    @Test
    void findAllPersons() {
        List<Person> all = personRepository.findAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
    }

    @Test
    void findById(){
        Optional<Person> user = personRepository.findById(USER_ID);

        assertFalse(user.isEmpty());
        assertNotNull(user);
        user.ifPresent(person->{
            assertEquals(person.getEmail(), "ivan@gmail.com");
            assertEquals(person.getFirstname(), "Ivan");
        });
    }

    @Test
    void create(){
        Person person = new Person();
        person.setFirstname("TestUserFirstname");
        person.setLastname("TestUserLastname");
        person.setEmail("test@gmail.com");
        person.setPhoneNumber("+77784053500");
        person.setRole(Role.USER);
        person.setBirthDate(LocalDate.now().minusYears(20).minusMonths(8).minusDays(12));
        person.setPassword("123");
        personRepository.saveAndFlush(person);

        Optional<Person> testPerson = personRepository.findByEmail("test@gmail.com");
        assertNotNull(testPerson);
        assertFalse(testPerson.isEmpty());

        testPerson.ifPresent(user->{
            assertEquals(user.getFirstname(), "TestUserFirstname");
            assertEquals(user.getLastname(), "TestUserLastname");
            assertEquals(user.getRole(), Role.USER);
        });
    }

    @Test
    void update(){
        personService.update(1L, new PersonCreateEditDto(
                "Anton",
                "Novikov",
                "novikov@gmail.com",
                "+77785565656",
                Role.ADMIN,
                LocalDate.now().minusYears(20),
                "123",
                null
        ));

        Optional<Person> person = personRepository.findById(1L);
        assertFalse(person.isEmpty());

        person.ifPresent(user->{
            assertEquals(user.getFirstname(), "Anton");
            assertEquals(user.getLastname(), "Novikov");
            assertEquals(user.getEmail(), "novikov@gmail.com");
            assertEquals(user.getPhoneNumber(), "+77785565656");
        });
    }

    @Test
    void deletePersonById(){
        personService.deleteById(USER_ID);
        assertTrue(personRepository.findById(USER_ID).isEmpty());
    }
}


















