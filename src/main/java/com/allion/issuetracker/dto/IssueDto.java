package com.allion.issuetracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(
        description = " This data object contains attributes for  Issue Response")
public class IssueDto {
    @JsonProperty( "issue_id")
    public String id;
    public String type;
}
