package com.allion.issuetracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ProjectIssueSummary implements Serializable {
    public String project_id;
    public List<WeeklySummary> weekly_summaries;
}
