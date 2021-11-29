package com.rony.notepadbackend.controllers.controllersimpl;

import com.rony.notepadbackend.controllers.NotepadController;
import com.rony.notepadbackend.dtos.request.NoteRequest;
import com.rony.notepadbackend.dtos.response.NoteResponse;
import com.rony.notepadbackend.exception.DataValidationException;
import com.rony.notepadbackend.services.FileService;
import com.rony.notepadbackend.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NotepadControllerImpl implements NotepadController {

    private final NoteService noteService;
    private final FileService fileService;

    public NotepadControllerImpl(NoteService noteService, FileService fileService) {
        this.noteService = noteService;
        this.fileService = fileService;
    }

    @Override
    public ResponseEntity<Void> add(@Valid NoteRequest noteDto, BindingResult error, HttpServletRequest request) {

        if(error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            String errorStrings = "";
            for(ObjectError e : errorList){
                errorStrings += e.getDefaultMessage() + "\n";
            }
            System.out.println(errorStrings);
            throw new DataValidationException(errorStrings);
        }
        String fileName = "";
        if(noteDto.getImage() != null && !noteDto.getImage().isEmpty()){
            fileName = fileService.writeToDirectory(noteDto.getImage());
        }
        var userId = (long) request.getSession().getAttribute("user_id");
        noteService.save(noteDto, fileName, userId);
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
    public ResponseEntity<Void> update(long id, @Valid NoteRequest noteDto, BindingResult error) {
        if(error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            String errorStrings = "";

            for(ObjectError e : errorList){
                errorStrings += e.getDefaultMessage() + "\n";
            }

            System.out.println(errorStrings);
            throw new DataValidationException(errorStrings);
        }
        String fileName = "";
        if(!noteDto.getImage().isEmpty()){
            fileName = fileService.writeToDirectory ( noteDto.getImage() );
        }
        noteService.update(id, noteDto, fileName);
        return ResponseEntity.ok().build();
    }
}
