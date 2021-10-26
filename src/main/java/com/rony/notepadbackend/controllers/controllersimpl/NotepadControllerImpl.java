package com.rony.notepadbackend.controllers.controllersimpl;

import com.rony.notepadbackend.controllers.NotepadController;
import com.rony.notepadbackend.dtos.request.NoteRequest;
import com.rony.notepadbackend.dtos.response.NoteResponse;
import com.rony.notepadbackend.services.FileService;
import com.rony.notepadbackend.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotepadControllerImpl implements NotepadController {

    private final NoteService noteService;
    private final FileService fileService;

    public NotepadControllerImpl(NoteService noteService, FileService fileService) {
        this.noteService = noteService;
        this.fileService = fileService;
    }

    public ResponseEntity<Void> add(NoteRequest noteDto) {
        var fileName = fileService.writeToDirectory ( noteDto.getImage() );
        noteService.save(noteDto, fileName);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<NoteResponse>> getAllNotes() {
        var notes = noteService.allNotes();
        return ResponseEntity.ok(notes);
    }

    @Override
    public ResponseEntity<NoteResponse> getById(long id) {
        return ResponseEntity.ok(noteService.getById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {
       noteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> update(long id, NoteRequest noteDto) {
        var fileName = fileService.writeToDirectory ( noteDto.getImage() );
        noteService.update(id, noteDto, fileName);
        return ResponseEntity.ok().build();
    }
}
