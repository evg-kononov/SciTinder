package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.SourceCategoryDTO;
import com.example.scopusservice.model.entity.SourceCategory;
import org.springframework.stereotype.Component;

@Component
public class SourceCategoryConverter {
    public SourceCategory fromSourceCategoryDTOToSourceCategory(SourceCategoryDTO sourceCategoryDTO) {
        SourceCategory sourceCategory = new SourceCategory();
        sourceCategory.setCategoryId(sourceCategoryDTO.getCategoryId());
        sourceCategory.setPublicationSourceId(sourceCategoryDTO.getPublicationSourceId());
        sourceCategory.setDateYear(sourceCategoryDTO.getDateYear());
        sourceCategory.setQuartile(sourceCategoryDTO.getQuartile());
        return sourceCategory;
    }

    public SourceCategoryDTO fromSourceCategoryToSourceCategoryDTO(SourceCategory sourceCategory) {
        SourceCategoryDTO sourceCategoryDTO = new SourceCategoryDTO();
        sourceCategoryDTO.setCategoryId(sourceCategory.getCategoryId());
        sourceCategoryDTO.setPublicationSourceId(sourceCategory.getPublicationSourceId());
        sourceCategoryDTO.setDateYear(sourceCategory.getDateYear());
        sourceCategoryDTO.setQuartile(sourceCategory.getQuartile());
        return sourceCategoryDTO;
    }
}
