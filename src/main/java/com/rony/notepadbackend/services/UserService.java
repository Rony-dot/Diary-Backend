package com.rony.notepadbackend.services;

import com.rony.notepadbackend.dtos.request.UserInfoDto;
import com.rony.notepadbackend.dtos.request.UserLoginDto;
import com.rony.notepadbackend.entities.Role;
import com.rony.notepadbackend.entities.User;
import com.rony.notepadbackend.exception.ResourceNotFoundException;
import com.rony.notepadbackend.repository.RoleRepository;
import com.rony.notepadbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CountryService countryService;

//    @Autowired
//    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(UserInfoDto userInfoDto){
        var user = new User();
        BeanUtils.copyProperties(userInfoDto,user);
        user.setCountry(countryService.getByCountryCode(userInfoDto.getCountryCode()));
        String dateOfBirth = userInfoDto.getDateOfBirth() == null ? "2017-11-15" : userInfoDto.getDateOfBirth();
        user.setDateOfBirth(LocalDate.parse(dateOfBirth));
        // Bcrypt password
        user.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        userRepository.save(user);
    }

    public UserInfoDto loginUser(UserLoginDto userLoginDto){

        Optional<User> optionalUser = userRepository
                .findByUsername(userLoginDto.getUsername());

        if (optionalUser.isPresent()) {
            User userModel = optionalUser.get();
            if (passwordEncoder.matches(userLoginDto.getPassword(), userModel.getPassword())) {
                var userInfoDto = new UserInfoDto();
                BeanUtils.copyProperties(userModel, userInfoDto);
                userInfoDto
                        .setCountryCode(countryService.getById(userModel.getCountry().getId()).getCountryCode());
                return userInfoDto;
            } else {
                throw new BadCredentialsException("Password mismatched");
            }
        } else {
            throw new BadCredentialsException("Invalid username");
        }
    }


    public User getById(long id) throws ResourceNotFoundException{
        var user =  this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserById(long id) throws ResourceNotFoundException{
        var user =  this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.deleteById(id);
    }

    public void updateUser(User user, long id) throws ResourceNotFoundException{
        var prevUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.save(user);
    }

}
