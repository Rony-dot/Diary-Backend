package com.rony.notepadbackend.dtos.request;

import com.rony.notepadbackend.entities.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoDto {

    private long id;
    private String name;
    private int age;
    private String username;
    private List<String> roles;
    private String email;
    private String password;
    private String homeTown;
    private String countryCode;
    private String mobile;
    private String salutation;
    private String gender;
    private String dateOfBirth;
    private String jwtToken;

    public UserInfoDto() {
    }

}
