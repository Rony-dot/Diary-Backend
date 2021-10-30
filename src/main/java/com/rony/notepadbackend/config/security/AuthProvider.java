package com.rony.notepadbackend.config.security;

import com.rony.notepadbackend.entities.User;
import com.rony.notepadbackend.exception.InvalidTokenException;
import com.rony.notepadbackend.repository.RoleRepository;
import com.rony.notepadbackend.repository.UserRepository;
import com.rony.notepadbackend.services.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    public AuthProvider(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username,
                                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        final String token = (String) usernamePasswordAuthenticationToken.getCredentials();
        if (token.isEmpty()) {
            return new org.springframework.security.core.userdetails.User
                    (username, "", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
        }
        Optional<User> optionalMarkdownUserModel = userRepository.findByUsername(username);

        if (optionalMarkdownUserModel.isPresent()) {
            User user = optionalMarkdownUserModel.get();
            try {
                tokenService.validateToken(user.getJwtToken());
            } catch (InvalidTokenException e) {
                user.setJwtToken(null);
                userRepository.save(user);
                return null;
            }

            return new org.springframework.security.core.userdetails.User(username, "",
                    AuthorityUtils.createAuthorityList(user.getRoles().stream().map(s -> "ROLE_" + s)
                            .toArray(String[]::new))
            );
        }
        throw new AuthenticationException ("No such user found with this username") {
            /**
             * Returns the detail message string of this throwable.
             *
             * @return the detail message string of this {@code Throwable} instance
             * (which may be {@code null}).
             */
            @Override
            public String getMessage() {
                return super.getMessage ();
            }
        };
    }
}
