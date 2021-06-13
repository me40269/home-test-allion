package com.allion.issuetracker.controller;

import com.allion.issuetracker.api.IssueSummaryApi;
import com.allion.issuetracker.dto.IssueTrackingResponseDto;
import com.allion.issuetracker.dto.ProjectIssueSummary;
import com.allion.issuetracker.dto.WeeklySummaryRequest;
import com.allion.issuetracker.model.Issue;
import com.allion.issuetracker.service.WeeklySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import java.util.Objects;

@RestController
public class IssueWeekSummaryController implements IssueSummaryApi {
    @Autowired
    WeeklySummaryService weeklySummaryService;


    @Override
    public ResponseEntity<ProjectIssueSummary> getWeeklySummary(WeeklySummaryRequest weeklySummaryRequest) {
        ProjectIssueSummary projectIssueSummary;
        if (weeklySummaryService.checkExists(weeklySummaryRequest.getProjectId())) {
            projectIssueSummary = weeklySummaryService.getWeeklySummary(weeklySummaryRequest);

        } else {
            projectIssueSummary = weeklySummaryService.getAlternateWeeklySummary(weeklySummaryRequest);

        }
        if (Objects.isNull(projectIssueSummary))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(projectIssueSummary, HttpStatus.OK);

    }


}
