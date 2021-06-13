package com.allion.issuetracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeekIssue {
        @JsonProperty("issue_id")
        public String issueId;
        public String type;
}
