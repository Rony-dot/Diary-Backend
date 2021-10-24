package com.rony.notepadbackend.services;


import com.rony.notepadbackend.entities.User;
import com.rony.notepadbackend.exception.InvalidTokenException;

public interface TokenService {

    void validateToken(String jwtToken) throws InvalidTokenException;

    void generateToken(User userMdoel);
}
