package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.dtos.request.NoteRequest;
import com.rony.notepadbackend.dtos.response.NoteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/notes")
public interface NotepadController {

    @PostMapping("/add")
    ResponseEntity<Void> add(@Valid @ModelAttribute NoteRequest noteDto, BindingResult error, HttpServletRequest request);

    @GetMapping("/all")
    ResponseEntity<List<NoteResponse>> getAllNotes();

    @GetMapping("/{id}")
    ResponseEntity<NoteResponse> getById(@PathVariable long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable long id ,@Valid @ModelAttribute NoteRequest noteDto, BindingResult error);

}
