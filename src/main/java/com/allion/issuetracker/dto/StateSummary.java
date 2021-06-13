package com.allion.issuetracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@ApiModel
public class StateSummary {
    public int count;

    public List<IssueDto> issues;
    @JsonIgnore
    public String week;

    public String state;

}
