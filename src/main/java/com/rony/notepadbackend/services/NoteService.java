package com.rony.notepadbackend.services;
import com.rony.notepadbackend.dtos.NoteDto;
import com.rony.notepadbackend.entities.Note;
import com.rony.notepadbackend.repository.NoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void save(NoteDto noteDto) {
        var note = new Note();
        BeanUtils.copyProperties(noteDto, note);
        this.noteRepository.save(note);
    }

    public List<NoteDto> allNotes() {
        return this.noteRepository.findAll().stream()
                .map(note -> {
                    var noteDto = new NoteDto();
                    BeanUtils.copyProperties(note, noteDto);
                    return noteDto;
                })
                .collect(Collectors.toList());
    }
}
