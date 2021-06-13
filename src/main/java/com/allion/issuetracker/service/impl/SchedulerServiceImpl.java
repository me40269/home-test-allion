package com.allion.issuetracker.service.impl;


import com.allion.issuetracker.model.IssueTracker;
import com.allion.issuetracker.repository.ProjectIssueInfoRepository;
import com.allion.issuetracker.repository.ProjectTrackingInfoRepository;
import com.allion.issuetracker.service.WeeklySummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerServiceImpl {
    Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    @Value("${issue.tracking.cronExpression}")
    private String runInterval;

    @Autowired
    ProjectTrackingInfoRepository projectTrackingInfoRepository;

    @Autowired
    ProjectIssueInfoRepository projectIssueInfoRepository;

    @Autowired
    WeeklySummaryService weeklySummaryService;

    @Scheduled(cron=  "${issue.tracking.cronExpression}")
    public void executeThirdPartyIssueTracking(){
        List<String> projectIds = projectTrackingInfoRepository.getAllProjectId();
        // No point of calling the external api unless until user is looking for it.Once it is retrieved,then need to sync latest data from third party
        if(projectIds.size() > 0) {
            logger.info("calling to third party API to sync the latest project issue information ");
            projectTrackingInfoRepository.getAllProjectId().forEach(projectId -> {
                List<IssueTracker> issueTrackerList = weeklySummaryService.constructProjectIssueSummary(projectId);
                projectIssueInfoRepository.updateProjectIssues(issueTrackerList);
            });
        }
        else {
            logger.debug("No need to call to the third party api as no project are retrieved yet");
        }
    }
}
