package com.zhsaidk.dto;

import com.zhsaidk.database.Entity.Book;
import com.zhsaidk.database.Entity.Role;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Value
public class PersonReadDto {
    Long id;
    String firstname;
    String lastname;
    String email;
    String phoneNumber;
    String password;
    Role role;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    List<BookReadDto> books;
}
