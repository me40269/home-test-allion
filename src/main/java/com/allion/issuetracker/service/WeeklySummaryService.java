package com.allion.issuetracker.service;

import com.allion.issuetracker.dto.ProjectIssueSummary;
import com.allion.issuetracker.dto.WeeklySummaryRequest;
import com.allion.issuetracker.model.IssueTracker;

import java.util.List;

public interface WeeklySummaryService {
  ProjectIssueSummary getWeeklySummary(WeeklySummaryRequest weeklySummaryRequest);

  boolean checkExists(String projectId);

  ProjectIssueSummary getAlternateWeeklySummary(WeeklySummaryRequest weeklySummaryRequest);

  List<IssueTracker> constructProjectIssueSummary(String projectId);


}
