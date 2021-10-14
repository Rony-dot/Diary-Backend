package com.rony.notepadbackend.controllersimpl;

import com.rony.notepadbackend.controllers.NotepadController;
import com.rony.notepadbackend.dtos.NoteDto;
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
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<List<NoteDto>> getAllNotes() {
        return ResponseEntity.ok(noteService.allNotes());
    }

}
