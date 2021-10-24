//package com.rony.notepadbackend.services.impl;
//
//
//import com.rony.notepadbackend.entities.User;
//import com.rony.notepadbackend.exception.InvalidTokenException;
//import com.rony.notepadbackend.services.AuthSigninKeyResolver;
//import com.rony.notepadbackend.services.TokenService;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.AllArgsConstructor;
//import org.hibernate.procedure.UnknownSqlResultSetMappingException;
//import org.springframework.stereotype.Service;
//
//import java.security.SignatureException;
//
//@AllArgsConstructor
//@Service
//public class TokenServiceImpl implements TokenService {
//
//    private AuthSigninKeyResolver authSigninKeyResolver;
//
//    @Override
//    public void validateToken(String jwtToken) throws InvalidTokenException {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKeyResolver(authSigninKeyResolver)
//                    .build().parse(jwtToken);
//        } catch (ExpiredJwtException | MalformedJwtException | IllegalArgumentException e) {
//            throw new InvalidTokenException("Invalid token", (RuntimeException) e);
//        }
//    }
//
//    @Override
//    public void generateToken(User userModel) {
//        //TODO: Replace token with JWT
//        String jwtToken = Jwts.builder()
//                .setSubject(userModel.getUsername())
////                .setAudience(userModel.getRoles().toString())
//                .setIssuer(String.valueOf(userModel.getId()))
//                .signWith(authSigninKeyResolver.getSecretKey(), SignatureAlgorithm.HS512)
//                .compact();
////        userModel.setJwtToken(jwtToken);
//    }
//}
