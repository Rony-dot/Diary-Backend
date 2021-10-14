package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.dtos.NoteDto;
import com.rony.notepadbackend.responseDto.NoteRespDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notes")
public interface NotepadController {

    @PostMapping("/add")
    ResponseEntity<Void> add(@RequestBody NoteDto noteDto);

    @GetMapping("/all")
    ResponseEntity<List<NoteRespDto>> getAllNotes();

    @GetMapping("/{id}")
    ResponseEntity<NoteRespDto> getById(@PathVariable long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable long id ,@RequestBody NoteDto noteDto);

}
