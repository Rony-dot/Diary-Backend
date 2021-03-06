package com.rony.notepadbackend.services;
import com.rony.notepadbackend.dtos.request.NoteRequest;
import com.rony.notepadbackend.dtos.response.NoteResponse;
import com.rony.notepadbackend.entities.Note;
import com.rony.notepadbackend.exception.ResourceNotFoundException;
import com.rony.notepadbackend.repository.NoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final FileService fileService;
    private final UserService userService;

    public void save(NoteRequest noteDto, String fileName, long userId) {
        var note = new Note();
        BeanUtils.copyProperties(noteDto, note);
        note.setImagePath (fileName);
        note.setUser(userService.getById( userId ));
        this.noteRepository.save(note);
        log.info("saved note : {}, for user id : {}", noteDto.getTitle(), userId);
    }

    public List<NoteResponse> allNotes() {
        List<NoteResponse> result = this.noteRepository.findAll().stream()
                .map(note -> {
                    var noteRespDto = new NoteResponse();
                    BeanUtils.copyProperties(note, noteRespDto);
                    noteRespDto.setImage(fileService.readFromPath(note.getImagePath()));
                    noteRespDto.setUserId(note.getUser().getId());
                    return noteRespDto;
                })
                .collect(Collectors.toList());
        log.info("getting all notes");
        return result;
    }

    public NoteResponse getById(long id) throws ResourceNotFoundException{
        var note =  this.noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found for this id :: " + id));
        var noteRespDto = new NoteResponse();
        BeanUtils.copyProperties(note, noteRespDto);
        noteRespDto.setImage(fileService.readFromPath(note.getImagePath()));
        noteRespDto.setUserId(note.getUser().getId());
        log.info("getting note {}, of id {}", note.getTitle(), id);
        return noteRespDto;
    }

    public boolean deleteById(long id) throws ResourceNotFoundException{
      if(noteRepository.existsById(id)){
          noteRepository.deleteById(id);
          log.info("note deleted of id : {}", id);
          return true;
      }
      log.error("note not found with this id : {}", id);
      throw new ResourceNotFoundException("Diary not found for this id :: " + id);
    }

    public void update(long id, NoteRequest noteDto, String fileName) throws ResourceNotFoundException{
        var note =  this.noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found for this id :: " + id));
        BeanUtils.copyProperties(noteDto, note);
        note.setImagePath (fileName);
        log.info("note : {}, is updated with id : {}", noteDto.getTitle(), id);
        this.noteRepository.save(note);
    }
}
