package com.zhsaidk.dto;

import com.zhsaidk.database.Entity.Role;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Value
public class PersonReadDto {
    Long id;
    String firstname;
    String lastname;
    String email;
    String phoneNumber;
    Role role;
    LocalDate birthDate;
    String image;
    List<BookDto> books;
}
