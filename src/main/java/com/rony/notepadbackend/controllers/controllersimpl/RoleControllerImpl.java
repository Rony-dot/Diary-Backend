package com.rony.notepadbackend.controllers.controllersimpl;

import com.rony.notepadbackend.controllers.RoleController;
import com.rony.notepadbackend.entities.Role;
import com.rony.notepadbackend.exception.DataValidationException;
import com.rony.notepadbackend.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @Override
    public ResponseEntity<Void> add(@Valid Role role, BindingResult error) {
        if(error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            String errorStrings = "";

            for(ObjectError e : errorList){
                errorStrings += e.getDefaultMessage() + "\n";
            }

            System.out.println(errorStrings);
            throw new DataValidationException(errorStrings);
        }
        roleService.add(role);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Role> getById(long id) {
        return ResponseEntity.ok().body(roleService.getById(id));
    }

    @Override
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.ok().body(roleService.getAll());
    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> update(@Valid Role role, BindingResult error, long id) {
        return null;
    }
}
