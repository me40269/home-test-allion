package com.allion.issuetracker.dto;

import com.allion.issuetracker.model.ISSUE_STATE;
import com.allion.issuetracker.model.ISSUE_TYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@ApiModel
public class StateSummary implements Serializable {
    public int count;

    public List<IssueDto> issues;
    @JsonIgnore
    public String week;

    public String state;

}
