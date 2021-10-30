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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "tbl_users")
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
    @Size(min = 3, max = 35, message
            = "Name must be between 3 and 30 characters")
    private String name;

    private int age;

    @Email(message = "email invalid")
    @NotBlank(message = "email cannot be empty")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "username is required")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "password is required")
    @Column(columnDefinition="TEXT")
    private String password;

    @NotBlank(message = "mobile is required")
    private String mobile;

    @Column( name = "token", columnDefinition = "TEXT")
    private String jwtToken;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    @NotBlank(message = "gender is required")
    private String gender;

    @NotBlank(message = "salutation is required")
    private String salutation;

//    @ManyToMany(fetch = LAZY)
//    private Collection<Role> roles = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> roles = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "country_code")
    private Country country;


}
