package com.rony.notepadbackend.dtos.response;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NoteResponse {
    private String title;
    private String body;
    private byte[] image;
}
