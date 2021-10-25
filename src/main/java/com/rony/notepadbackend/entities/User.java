package com.rony.notepadbackend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @NotBlank(message = "name cannot be null")
    @Size(min = 4, max = 10, message
            = "Name must be between 4 and 10 characters")
    private String name;

    private int age;

    @Email(message = "email invalid")
    @NotBlank(message = "email cannot be empty")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "mobile is required")
    private String mobile;

    @Column( name = "token")
    private String jwtToken;
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
//    @JsonFormat(pattern = "yyyy-MM-dd ")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    @NotBlank(message = "gender is required")
    private String gender;

    @NotBlank(message = "salutation is required")
    private String salutation;

    @NotBlank(message = "hometown is required")
    private String homeTown;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> roles;

    @OneToOne
    @JoinColumn(name = "country_code")
//    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private Country country;
}
