package com.allion.issuetracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Document(collection = "request.info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeeklySummaryInfo {
    private String projectId;

    private String fromWeek;

    private String toWeek;

    private List<String> types;

    private List<String> states;
}
