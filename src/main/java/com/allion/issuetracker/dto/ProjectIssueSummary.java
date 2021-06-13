package com.allion.issuetracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProjectIssueSummary {
    public String project_id;
    public List<WeeklySummary> weekly_summaries;
}
