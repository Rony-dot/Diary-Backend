package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.dtos.request.UserInfoDto;
import com.rony.notepadbackend.dtos.request.UserLoginDto;
import com.rony.notepadbackend.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserController {

    @PostMapping("/add")
    @PreAuthorize ("hasAnyRole('ADMIN')")
    ResponseEntity<Void> add(@RequestBody UserInfoDto userInfoDto);

    @GetMapping("/{id}")
    @PreAuthorize ("hasAnyRole('USER')")
    ResponseEntity<User> getById(@PathVariable long id);

    @GetMapping("/all")
    @PreAuthorize ("hasAnyRole('USER')")
    ResponseEntity<List<User>> getAll();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@RequestBody User user, @PathVariable long id);
}
