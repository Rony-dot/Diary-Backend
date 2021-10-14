package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.dtos.NoteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/notes")
public interface NotepadController {

    @PostMapping("/add")
    ResponseEntity<Void> add(@RequestBody NoteDto noteDto);

    @GetMapping("/all")
    ResponseEntity<List<NoteDto>> getAllNotes();

//    ResponseEntity<NoteDto> getById(long id)
}
