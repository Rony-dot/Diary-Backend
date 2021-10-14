package com.rony.notepadbackend.responseDto;

import lombok.Data;

@Data
public class NoteRespDto {
    private long id;
    private String title;
    private String body;
}
