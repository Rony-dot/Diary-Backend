package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.entities.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/countries")
public interface CountryController {

    @GetMapping("/all")
    ResponseEntity<List<Country>> getAll();

    @PostMapping("/add")
    ResponseEntity<Void> add(@RequestBody Country country);

    @GetMapping("/{id}")
    ResponseEntity<Country> getById(@PathVariable long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@RequestBody Country country);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);


}
