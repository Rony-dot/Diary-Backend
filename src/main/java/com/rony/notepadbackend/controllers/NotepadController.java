package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.dtos.request.NoteRequest;
import com.rony.notepadbackend.dtos.response.NoteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notes")
public interface NotepadController {

    @PostMapping("/add")
    ResponseEntity<Void> add(@ModelAttribute NoteRequest noteDto);

    @GetMapping("/all")
    ResponseEntity<List<NoteResponse>> getAllNotes();

    @GetMapping("/{id}")
    ResponseEntity<NoteResponse> getById(@PathVariable long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable long id ,@RequestBody NoteRequest noteDto);

}
