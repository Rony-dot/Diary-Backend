package com.rony.notepadbackend.controllers.controllersimpl;

import com.rony.notepadbackend.controllers.HomeController;
import com.rony.notepadbackend.dtos.request.UserInfoDto;
import com.rony.notepadbackend.dtos.request.UserLoginDto;
import com.rony.notepadbackend.entities.User;
import com.rony.notepadbackend.exception.DataValidationException;
import com.rony.notepadbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class HomeControllerImpl implements HomeController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserInfoDto> login(@Valid UserLoginDto userLoginDto, BindingResult error, HttpServletRequest request) {
        if(error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            String errorStrings = "";

            for(ObjectError e : errorList){
                errorStrings += e.getDefaultMessage() + "\n";
            }

            System.out.println(errorStrings);
            throw new DataValidationException(errorStrings);
        }

        var userId = userService.getByUserName(userLoginDto.getUsername()).getId();
        request.getSession().setAttribute("user_id",userId);
        System.out.println("username "+userLoginDto.getUsername());
        System.out.println("password "+userLoginDto.getPassword());

        return ResponseEntity.ok(userService.loginUser(userLoginDto));
    }

    @Override
    public ResponseEntity<Void> register(@Valid UserInfoDto userInfoDto, BindingResult error) {
        if(error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            String errorStrings = "";

            for(ObjectError e : errorList){
                errorStrings += e.getDefaultMessage() + "\n";
            }

            System.out.println(errorStrings);
            throw new DataValidationException(errorStrings);
        }
        userService.addUser(userInfoDto);
        return ResponseEntity.ok().build();
    }

}
