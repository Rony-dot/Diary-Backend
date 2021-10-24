package com.rony.notepadbackend.services;

import io.jsonwebtoken.SigningKeyResolver;

import javax.crypto.SecretKey;

public interface AuthSigninKeyResolver extends SigningKeyResolver {

    SecretKey getSecretKey();
}
