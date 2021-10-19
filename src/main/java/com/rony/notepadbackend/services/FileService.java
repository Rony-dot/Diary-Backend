package com.rony.notepadbackend.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String writeToDirectory(MultipartFile file);

    byte[] readFromPath(String path);
}
