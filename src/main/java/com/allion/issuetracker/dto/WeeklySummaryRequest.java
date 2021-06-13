package com.allion.issuetracker.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(
        description = " This data object contains attributes for  Weekly Summary Request")
public class WeeklySummaryRequest {


    @JsonProperty("project_id")
    @NotNull(message = "project_id  cannot be null or empty")
    private String projectId;

    @JsonProperty("from_week")
    @NotNull(message = "from_week  cannot be null or empty")
    private String fromWeek;

    @JsonProperty("to_week")
    @NotNull(message = "to_week  cannot be null or empty")
    private String toWeek;

    @Size(min = 1, message = "At least one type has to be specified")
    private List<String> types;

    private List<String> states;

}
