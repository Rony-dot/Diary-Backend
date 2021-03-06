package com.rony.notepadbackend.controllers.controllersimpl;

import com.rony.notepadbackend.controllers.CountryController;
import com.rony.notepadbackend.entities.Country;
import com.rony.notepadbackend.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CountryControllerImpl implements CountryController {

    @Autowired
    private final CountryService countryService;

    @Override
    public ResponseEntity<List<Country>> getAll() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @Override
    public ResponseEntity<Void> add(Country country) {
        countryService.addCountry(country);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Country> getById(Long id) {
        return ResponseEntity.ok(countryService.getById(id));
    }

    @Override
    public ResponseEntity<Void> update(Country country) {
        countryService.addCountry(country);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.ok().build();
    }
}
