package com.rony.notepadbackend.controllers.controllersimpl;

import com.rony.notepadbackend.controllers.UserController;
import com.rony.notepadbackend.dtos.request.UserInfoDto;
import com.rony.notepadbackend.dtos.request.UserLoginDto;
import com.rony.notepadbackend.entities.User;
import com.rony.notepadbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<Void> add(UserInfoDto userInfoDto) {
        userService.addUser(userInfoDto);
        return ResponseEntity.ok().build();
    }



    @Override
    public ResponseEntity<User> getById(long id) {
        var user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
       return  ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> update(User user, long id) {
        return null;
    }
}
