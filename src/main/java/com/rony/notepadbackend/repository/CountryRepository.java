package com.rony.notepadbackend.repository;

import com.rony.notepadbackend.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country getCountryByCountryCode(String countryCode);
}