package com.rony.notepadbackend.initialize;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rony.notepadbackend.dtos.request.UserInfoDto;
import com.rony.notepadbackend.entities.Country;
import com.rony.notepadbackend.entities.Role;
import com.rony.notepadbackend.repository.CountryRepository;
import com.rony.notepadbackend.repository.RoleRepository;
import com.rony.notepadbackend.repository.UserRepository;
import com.rony.notepadbackend.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Profile("prod")
@Component
public class InitializeProdData implements InitializeData {

    private final UserService userService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CountryRepository countryRepository;

    private final PasswordEncoder passwordEncoder;

//    private final TokenService tokenService;

    private final ResourceLoader resourceLoader;

    public InitializeProdData(UserService userService, UserRepository userRepository, RoleRepository roleRepository,
                              CountryRepository countryRepository, PasswordEncoder passwordEncoder,
//                              TokenService tokenService,
                              ResourceLoader resourceLoader) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
        this.passwordEncoder = passwordEncoder;
//        this.tokenService = tokenService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void initialize() {
        addCountries ();
        addRoles();
        addUsers();
    }

    private void addUsers() {
        try {
            var userModels = new ObjectMapper()
                    .readValue(
                            resourceLoader.getResource("classpath:users.json").getInputStream(),
                            new TypeReference<ArrayList<UserInfoDto>>() {
                            }
                    );
            userModels.forEach(userModel -> {
                boolean found = userRepository
                        .findByUsernameOrEmail(userModel.getUsername(), userModel.getEmail())
                        .size() > 0;
                if (!found) {
//                    tokenService.generateToken(userModel);
                    userService.addUser (userModel);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addRoles() {
        try {
            List<Role> roleModels = new ObjectMapper()
                    .readValue(
                            resourceLoader.getResource("classpath:roles.json").getInputStream(),
                            new TypeReference<ArrayList<Role>>() {
                            }
                    );
            roleModels.forEach(roleModel -> {
                if (roleRepository.findByRole(roleModel.getRole()).isEmpty ()) {
                    roleRepository.saveAndFlush(roleModel);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCountries() {
        try {
            var countries = new ObjectMapper()
                    .readValue(
                            resourceLoader.getResource("classpath:countries.json").getInputStream(),
                            new TypeReference<ArrayList<Country>>() {
                            }
                    );
            countries.forEach(countryModel -> {
                if (countryRepository.getCountryByCountryCode(countryModel.getCountryCode ()) == null) {
                    countryRepository.saveAndFlush(countryModel);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
