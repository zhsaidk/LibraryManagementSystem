package com.zhsaidk.database.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "person")
public class Person extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String firstname;

    @Column(nullable = false)
    @Size(max = 100)
    private String lastname;

    @Email
    @NotBlank
    private String email;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String password;

    private String image;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Book> books;
}
