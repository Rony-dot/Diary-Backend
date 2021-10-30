package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.dtos.request.UserInfoDto;
import com.rony.notepadbackend.dtos.request.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping
public interface HomeController {


    @PostMapping("/register")
    ResponseEntity<Void> register(@Valid @RequestBody UserInfoDto userInfoDto, BindingResult error);

    @PostMapping("/login")
    ResponseEntity<UserInfoDto> login(@Valid @RequestBody UserLoginDto userLoginDto, BindingResult error);
}
