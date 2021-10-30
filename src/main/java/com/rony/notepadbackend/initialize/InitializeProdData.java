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
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Profile({"prod", "postgres"})
@Component
@Slf4j
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
                    log.info("saving user : {}", userModel.getUsername());
                }
            });
        } catch (IOException e) {
            log.error("error in saving user : {}" , e.getMessage());
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
                if (roleRepository.findByName(roleModel.getName()).isEmpty ()) {
                    roleRepository.saveAndFlush(roleModel);
                    log.info("saving role : {}",roleModel.getName());
                }
            });
        } catch (IOException e) {
            log.error("error in saving role : {}" , e.getMessage());
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
                    log.info("saving country : {}", countryModel.getName());
                }
            });
        } catch (IOException e) {
            log.error("error in saving country : {}" , e.getMessage());
        }
    }
}
