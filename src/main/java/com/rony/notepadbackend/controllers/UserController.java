package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.dtos.request.UserInfoDto;
import com.rony.notepadbackend.dtos.request.UserLoginDto;
import com.rony.notepadbackend.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
public interface UserController {

    @PostMapping("/add")
    ResponseEntity<Void> add(@Valid @RequestBody UserInfoDto userInfoDto, BindingResult error);

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable long id);

    @GetMapping("/all")
    ResponseEntity<List<User>> getAll();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@Valid @RequestBody User user, BindingResult error, @PathVariable long id);
}
