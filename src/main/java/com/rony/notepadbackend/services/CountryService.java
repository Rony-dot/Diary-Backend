package com.rony.notepadbackend.services;

import com.rony.notepadbackend.entities.Country;
import com.rony.notepadbackend.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {

    @Autowired
    private final CountryRepository countryRepository;

    public void addCountry(Country country){
        countryRepository.save(country);
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    public Country getById(long id){
        return countryRepository.getById(id);
    }

    public void deleteCountry(long id){
        countryRepository.deleteById(id);
    }


}
