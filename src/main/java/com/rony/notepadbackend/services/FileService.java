package com.rony.notepadbackend.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String writeToDirectory(MultipartFile file);

    byte[] readFromPath(String path);
}
