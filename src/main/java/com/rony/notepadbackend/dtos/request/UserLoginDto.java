package com.rony.notepadbackend.dtos.request;

import lombok.Data;

@Data
public class UserLoginDto {
    private String username;
    private String password;
}
