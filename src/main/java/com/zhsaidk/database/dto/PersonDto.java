package com.zhsaidk.database.dto;

import com.zhsaidk.database.Entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class PersonDto {
    Long id;
    String firstname;
    String lastname;
    String email;
    String phoneNumber;
    Role role;
    LocalDate birthDate;
}
