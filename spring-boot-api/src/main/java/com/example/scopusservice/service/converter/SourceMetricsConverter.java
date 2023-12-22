package com.example.scopusservice.service.converter;


import com.example.scopusservice.model.dto.SourceMetricsDTO;
import com.example.scopusservice.model.entity.SourceMetrics;
import org.springframework.stereotype.Component;

@Component
public class SourceMetricsConverter {
    public SourceMetrics fromSourceMetricsDTOToSourceMetrics(SourceMetricsDTO sourceMetricsDTO) {
        SourceMetrics sourceMetrics = new SourceMetrics();
        sourceMetrics.setPublicationSourceId(sourceMetricsDTO.getPublicationSourceId());
        sourceMetrics.setDateYear(sourceMetricsDTO.getDateYear());
        sourceMetrics.setSnip(sourceMetricsDTO.getSnip());
        sourceMetrics.setSjr(sourceMetricsDTO.getSjr());
        sourceMetrics.setCitescore(sourceMetricsDTO.getCitescore());
        sourceMetrics.setSjrBestQuartile(sourceMetricsDTO.getSjrBestQuartile());
        sourceMetrics.setH_index(sourceMetricsDTO.getH_index());
        return sourceMetrics;
    }

    public SourceMetricsDTO fromSourceMetricsToSourceMetricsDTO(SourceMetrics sourceMetrics) {
        SourceMetricsDTO sourceMetricsDTO = new SourceMetricsDTO();
        sourceMetricsDTO.setPublicationSourceId(sourceMetrics.getPublicationSourceId());
        sourceMetricsDTO.setDateYear(sourceMetrics.getDateYear());
        sourceMetricsDTO.setSnip(sourceMetrics.getSnip());
        sourceMetricsDTO.setSjr(sourceMetrics.getSjr());
        sourceMetricsDTO.setCitescore(sourceMetrics.getCitescore());
        sourceMetricsDTO.setSjrBestQuartile(sourceMetrics.getSjrBestQuartile());
        sourceMetricsDTO.setH_index(sourceMetrics.getH_index());
        return sourceMetricsDTO;
    }
}
