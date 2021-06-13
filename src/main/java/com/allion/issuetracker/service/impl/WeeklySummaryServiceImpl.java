package com.allion.issuetracker.service.impl;

import com.allion.issuetracker.dto.*;
import com.allion.issuetracker.exception.CustomIssueException;
import com.allion.issuetracker.model.*;
import com.allion.issuetracker.repository.ProjectIssueInfoRepository;
import com.allion.issuetracker.repository.ProjectTrackingInfoRepository;
import com.allion.issuetracker.service.WeeklySummaryService;
import com.allion.issuetracker.utils.WeekNumberUtil;
import com.allion.issuetracker.validator.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

@Service
public class WeeklySummaryServiceImpl implements WeeklySummaryService {

    Logger logger = LoggerFactory.getLogger(WeeklySummaryServiceImpl.class);

    @Autowired
    ProjectIssueInfoRepository projectIssueInfoRepository;

    @Autowired
    ProjectTrackingInfoRepository projectTrackingInfoRepository;

    @Autowired
    ExternalApiExecutorServiceImpl externalApiExecutorServiceImpl;

    @Override
    public ProjectIssueSummary getWeeklySummary(WeeklySummaryRequest weeklySummaryRequest) {

        Date fromDate = WeekNumberUtil.getFromYearWeek(weeklySummaryRequest.getFromWeek());
        Date toDate = WeekNumberUtil.getFromYearWeek(weeklySummaryRequest.getToWeek());
        String projectId = weeklySummaryRequest.getProjectId();
        if (!RequestValidator.checkValidEnum(weeklySummaryRequest.getStates(), ISSUE_STATE.class)) {
            throw new CustomIssueException(MessageFormat.format("states can have either {0}", Arrays.toString(ISSUE_STATE.values())));
        }
        if (!RequestValidator.checkValidEnum(weeklySummaryRequest.getTypes(), ISSUE_TYPE.class)) {
            throw new CustomIssueException(MessageFormat.format("types can have  either {0}", Arrays.toString(ISSUE_TYPE.values())));
        }
        Map<String, List<StateSummary>> listMap = projectIssueInfoRepository.getProjectIssues(projectId, weeklySummaryRequest.getStates(), weeklySummaryRequest.getTypes(), fromDate, toDate);
        List<WeeklySummary> weeklySummaries = new ArrayList<>();
        listMap.entrySet().stream().forEach(e -> {
            WeeklySummary weeklySummary = new WeeklySummary();
            weeklySummary.setState_summaries(e.getValue());
            weeklySummary.setWeek(e.getKey());
            weeklySummaries.add(weeklySummary);
        });
        ProjectIssueSummary projectIssueSummary = new ProjectIssueSummary();
        projectIssueSummary.setProject_id(projectId);
        projectIssueSummary.setWeekly_summaries(weeklySummaries);
        return projectIssueSummary;

    }


    @Override
    public boolean checkExists(String projectId) {
        return projectIssueInfoRepository.checkExists(projectId);
    }


    @Override
    public ProjectIssueSummary getAlternateWeeklySummary(WeeklySummaryRequest weeklySummaryRequest) {
        String projectId = weeklySummaryRequest.getProjectId();
        logger.info("calling to third party API to retrieve project information");
        List<IssueTracker> issueTrackerList = constructProjectIssueSummary(projectId);
        if (Objects.isNull(projectId) || issueTrackerList.size() == 0)
            return null;
        projectIssueInfoRepository.saveProjectIssues(issueTrackerList);

        //Saving the project id into this collection to call third party api time to time via scheduler
        projectTrackingInfoRepository.saveProjectId(new ProjectTrackingInfo(projectId));
        return getWeeklySummary(weeklySummaryRequest);
    }

    @Override
    public List<IssueTracker> constructProjectIssueSummary(String projectId) {
        List<IssueTracker> issueTrackerList = new ArrayList<>();
        IssueTrackingResponseDto issueTrackingResponseDto = externalApiExecutorServiceImpl.getIssueInfo(projectId);
        List<Issue> issueDtoList = issueTrackingResponseDto.getIssues();
        System.out.println(issueDtoList);

        issueDtoList.forEach(issue -> {
            ChangeLog changeLog = issue.getChangelogs().get(issue.getChangelogs().size() - 1);
            IssueTracker projectIssue = new IssueTracker(issue.getIssue_id(), issueTrackingResponseDto.getProject_id(), issue.getCurrent_state(), issue.getType(), WeekNumberUtil.getWeekNumber(changeLog.getChanged_on()), WeekNumberUtil.getDate(changeLog.getChanged_on()));
            issueTrackerList.add(projectIssue);
        });
        return issueTrackerList;
    }


}
