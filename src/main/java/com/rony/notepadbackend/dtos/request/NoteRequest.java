package com.rony.notepadbackend.dtos.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NoteRequest {

    @NotBlank(message = "title cannot be null")
    @Size(min = 3, max = 35, message
            = "Title must be between 3 and 30 characters")
    private String title;

    @NotBlank(message = "body cannot be null")
    @Size(min = 3, message
            = "body at least 3 characters")
    private String body;

    private MultipartFile image;
}
