package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.entities.Country;
import com.rony.notepadbackend.exception.model.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.management.Query;
import java.util.List;

@Api(tags = "Country", value = "Country")
@RequestMapping("/countries")
public interface CountryController {

    @ApiOperation(
            value = "Get All Countries",
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
    })
    @GetMapping("/all")
    ResponseEntity<List<Country>> getAll();

    @PostMapping("/add")
    ResponseEntity<Void> add(@RequestBody Country country);

    @GetMapping("/{id}")
    ResponseEntity<Country> getById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@RequestBody Country country);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);


}
