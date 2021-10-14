package com.rony.notepadbackend.controllersimpl;

import com.rony.notepadbackend.controllers.NotepadController;
import com.rony.notepadbackend.dtos.NoteDto;
import com.rony.notepadbackend.responseDto.NoteRespDto;
import com.rony.notepadbackend.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotepadControllerImpl implements NotepadController {

    @Autowired
    private NoteService noteService;

    public NotepadControllerImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    public ResponseEntity<Void> add(NoteDto noteDto) {
        noteService.save(noteDto);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<NoteRespDto>> getAllNotes() {
        return ResponseEntity.ok(noteService.allNotes());
    }

    @Override
    public ResponseEntity<NoteRespDto> getById(long id) {
        return ResponseEntity.ok(noteService.getById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {
       noteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> update(long id, NoteDto noteDto) {
        noteService.update(id, noteDto);
        return ResponseEntity.ok().build();
    }
}
