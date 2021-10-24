//package com.rony.notepadbackend.initialize;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rony.notepadbackend.entities.Role;
//import com.rony.notepadbackend.entities.User;
//import com.rony.notepadbackend.repository.RoleRepository;
//import com.rony.notepadbackend.repository.UserRepository;
//import com.rony.notepadbackend.services.TokenService;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Profile("prod")
//@Component
//public class InitializeProdData implements InitializeData {
//
//    private final UserRepository userRepository;
//
//    private final RoleRepository roleRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
////    private final TokenService tokenService;
//
//    private final ResourceLoader resourceLoader;
//
//    public InitializeProdData(UserRepository userRepository, RoleRepository roleRepository,
//                              PasswordEncoder passwordEncoder,
////                              TokenService tokenService,
//                              ResourceLoader resourceLoader) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
////        this.tokenService = tokenService;
//        this.resourceLoader = resourceLoader;
//    }
//
//    @Override
//    public void initialize() {
//        addRoles();
//        addUsers();
//    }
//
//    private void addUsers() {
//        try {
//            List<User> userModels = new ObjectMapper()
//                    .readValue(
//                            resourceLoader.getResource("classpath:users.json").getInputStream(),
//                            new TypeReference<ArrayList<User>>() {
//                            }
//                    );
//            userModels.forEach(userModel -> {
//                boolean found = userRepository
//                        .findByUsernameOrEmail(userModel.getUsername(), userModel.getEmail())
//                        .size() > 0;
//                if (!found) {
////                    tokenService.generateToken(userModel);
//                    userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
//                    userRepository.saveAndFlush(userModel);
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void addRoles() {
//        try {
//            List<Role> roleModels = new ObjectMapper()
//                    .readValue(
//                            resourceLoader.getResource("classpath:roles.json").getInputStream(),
//                            new TypeReference<ArrayList<Role>>() {
//                            }
//                    );
//            roleModels.forEach(roleModel -> {
//                if (!roleRepository.findByRole(roleModel.getRole()).isPresent()) {
//                    roleRepository.saveAndFlush(roleModel);
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
