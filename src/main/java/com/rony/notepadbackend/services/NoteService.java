package com.rony.notepadbackend.services;
import com.rony.notepadbackend.dtos.request.NoteRequest;
import com.rony.notepadbackend.dtos.response.NoteResponse;
import com.rony.notepadbackend.entities.Note;
import com.rony.notepadbackend.exception.ResourceNotFoundException;
import com.rony.notepadbackend.repository.NoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final FileService fileService;

    public NoteService(NoteRepository noteRepository, FileService fileService) {
        this.noteRepository = noteRepository;
        this.fileService = fileService;
    }

    public void save(NoteRequest noteDto, String fileName) {
        var note = new Note();
        BeanUtils.copyProperties(noteDto, note);
        note.setImagePath (fileName);
        this.noteRepository.save(note);
    }

    public List<NoteResponse> allNotes() {
        List<NoteResponse> result = this.noteRepository.findAll().stream()
                .map(note -> {
                    var noteRespDto = new NoteResponse();
                    BeanUtils.copyProperties(note, noteRespDto);
                    noteRespDto.setImage(fileService.readFromPath(note.getImagePath()));
                    return noteRespDto;
                })
                .collect(Collectors.toList());
        return result;
    }

    public NoteResponse getById(long id) throws ResourceNotFoundException{
        var note =  this.noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found for this id :: " + id));
        var noteRespDto = new NoteResponse();
        BeanUtils.copyProperties(note, noteRespDto);
        noteRespDto.setImage(fileService.readFromPath(note.getImagePath()));
        return noteRespDto;
    }

    public boolean deleteById(long id) throws ResourceNotFoundException{
      if(noteRepository.existsById(id)){
          noteRepository.deleteById(id);
          return true;
      }
      throw new ResourceNotFoundException("Diary not found for this id :: " + id);
    }

    public void update(long id, NoteRequest noteDto, String fileName) throws ResourceNotFoundException{
        var note =  this.noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found for this id :: " + id));
        BeanUtils.copyProperties(noteDto, note);
        note.setImagePath (fileName);
        this.noteRepository.save(note);
    }
}
