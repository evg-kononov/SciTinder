package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.KeywordDTO;
import com.example.scopusservice.model.entity.Keyword;
import org.springframework.stereotype.Component;

@Component
public class KeywordConverter {
    public Keyword fromKeywordDTOToKeyword(KeywordDTO keywordDTO) {
        Keyword keyword = new Keyword();
        keyword.setId(keywordDTO.getId());
        keyword.setWord(keywordDTO.getWord());
        return keyword;
    }

    public KeywordDTO fromKeywordToKeywordDTO(Keyword keyword) {
        KeywordDTO keywordDTO = new KeywordDTO();
        keywordDTO.setId(keyword.getId());
        keywordDTO.setWord(keyword.getWord());
        return keywordDTO;
    }
}
