package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.dtos.request.UserInfoDto;
import com.rony.notepadbackend.dtos.request.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface HomeController {


    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody UserInfoDto userInfoDto);

    @PostMapping("/login")
    ResponseEntity<UserInfoDto> login(@RequestBody UserLoginDto userLoginDto);
}
