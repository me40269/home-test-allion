package com.allion.issuetracker.repository;


import com.allion.issuetracker.aggregation.ProjectIssueAggregation;
import com.allion.issuetracker.dto.StateSummary;
import com.allion.issuetracker.model.IssueTracker;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProjectIssueInfoRepository {

    private static final String COLLECTION_NAME = "issue.tracker";

    private static final String PROJECT_ID = "project_id";


    @Autowired
    ProjectIssueAggregation projectIssueAggregation;

    @Autowired
    private MongoTemplate mongoTemplate;

    //Retrieve the weekly summary against the week number
    public Map<String, List<StateSummary>> getProjectIssues(String projectId, List<String> states, List<String> types, Date fromDate, Date toDate) {
        List<Bson> pipeline =
                projectIssueAggregation.getIssueTrackingMappingAggregationPipeline(projectId, states, types, fromDate, toDate);
        List<AggregationOperation> aggregationOperations = new ArrayList<>();
        pipeline.forEach(doc -> aggregationOperations.add(aggregationOperation -> (Document) doc));
        List<StateSummary> stateSummaryList = mongoTemplate.aggregate(Aggregation.newAggregation(aggregationOperations),
                COLLECTION_NAME, StateSummary.class).getMappedResults();
        Map<String, List<StateSummary>> weekSummaryMap = stateSummaryList.stream().collect(Collectors.groupingBy(ws -> ws.getWeek()));
        return weekSummaryMap;
    }

    //check the project id exists in the collection
    public boolean checkExists(String projectId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROJECT_ID).in(projectId));
        return mongoTemplate.find(query, IssueTracker.class).size() != 0;
    }


    //save  the projects in the collection
    public void saveProjectIssues(List<IssueTracker> projectIssues) {
        mongoTemplate.insertAll(projectIssues);
    }


    //Update with the latest changes id exists in the collection
    public void updateProjectIssues(List<IssueTracker> issueTrackerList) {
        issueTrackerList.stream().forEach(issueTracker -> {
            Document doc = new Document();
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(issueTracker.getIssueId()));
            mongoTemplate.getConverter().write(issueTracker, doc);
            Update update = new Update();
            doc.forEach(update::set);
            mongoTemplate.updateFirst(query,  update,IssueTracker.class);
        });


    }

}
