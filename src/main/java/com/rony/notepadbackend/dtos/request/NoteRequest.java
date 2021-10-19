package com.rony.notepadbackend.dtos.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NoteRequest {
    private String title;
    private String body;
    private MultipartFile image;
}
