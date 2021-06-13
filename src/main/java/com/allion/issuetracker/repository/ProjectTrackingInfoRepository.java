package com.allion.issuetracker.repository;

import com.allion.issuetracker.aggregation.ProjectIssueAggregation;
import com.allion.issuetracker.model.IssueTracker;
import com.allion.issuetracker.model.ProjectTrackingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProjectTrackingInfoRepository {
    private static final String COLLECTION_NAME = "project.tracker";
    private static final String PROJECT_ID = "project_id";

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveProjectId(ProjectTrackingInfo projectTrackingInfo) {
        if (notExists(projectTrackingInfo.getProjectId())) {
            mongoTemplate.save(projectTrackingInfo);
        }
    }

    public List<String> getAllProjectId(){
        List<ProjectTrackingInfo> projectTrackingInfoList = mongoTemplate.findAll(ProjectTrackingInfo.class);
        return projectTrackingInfoList.stream().map(projectTrackingInfo -> projectTrackingInfo.getProjectId()).collect(Collectors.toList());

    }

    //check the project id exists in the collection
    public boolean notExists(String projectId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROJECT_ID).in(projectId));
        return mongoTemplate.find(query, ProjectTrackingInfo.class).size() == 0;
    }
}
