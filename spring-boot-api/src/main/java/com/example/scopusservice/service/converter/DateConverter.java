package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.DateDTO;
import com.example.scopusservice.model.entity.Date;
import org.springframework.stereotype.Component;

@Component
public class DateConverter {

    public Date fromDateDTOToDate(DateDTO dateDTO) {
        Date date = new Date();
        date.setYear(dateDTO.getYear());
        return date;
    }

    public DateDTO fromDateToDateDTO(Date date) {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setYear(date.getYear());
        return dateDTO;
    }
}
