package com.zhsaidk.dto;

import com.zhsaidk.database.Entity.Role;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
public class PersonCreateEditDto {
    String firstname;
    String lastname;
    String email;
    String phoneNumber;
    Role role;
    LocalDate birthDate;
    String password;
    MultipartFile image;
}
