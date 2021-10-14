package com.rony.notepadbackend.services;
import com.rony.notepadbackend.dtos.NoteDto;
import com.rony.notepadbackend.entities.Note;
import com.rony.notepadbackend.exception.ResourceNotFoundException;
import com.rony.notepadbackend.repository.NoteRepository;
import com.rony.notepadbackend.responseDto.NoteRespDto;
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

    public List<NoteRespDto> allNotes() {
        return this.noteRepository.findAll().stream()
                .map(note -> {
                    var noteRespDto = new NoteRespDto();
                    BeanUtils.copyProperties(note, noteRespDto);
                    return noteRespDto;
                })
                .collect(Collectors.toList());
    }

    public NoteRespDto getById(long id) throws ResourceNotFoundException{
        var note =  this.noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found for this id :: " + id));
        var noteRespDto = new NoteRespDto();
        BeanUtils.copyProperties(note, noteRespDto);
        return noteRespDto;
    }

    public boolean deleteById(long id) throws ResourceNotFoundException{
      if(noteRepository.existsById(id)){
          noteRepository.deleteById(id);
          return true;
      }
      throw new ResourceNotFoundException("Diary not found for this id :: " + id);
    }

    public void update(long id, NoteDto noteDto) throws ResourceNotFoundException{
        var note =  this.noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found for this id :: " + id));
        BeanUtils.copyProperties(noteDto, note);
        this.noteRepository.save(note);
    }
}
