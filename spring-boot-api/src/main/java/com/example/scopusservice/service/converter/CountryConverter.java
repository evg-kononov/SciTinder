package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.CountryDTO;
import com.example.scopusservice.model.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryConverter {
    public Country fromCountryDTOToCountry(CountryDTO countryDTO) {
        Country country = new Country();
        country.setId(countryDTO.getId());
        country.setName(countryDTO.getName());
        return country;
    }

    public CountryDTO fromCountryToCountryDTO(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());
        return countryDTO;
    }
}
