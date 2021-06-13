package com.allion.issuetracker.aggregation;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProjectIssueAggregation {
    public static final String MATCH = "$match";
    public static final String PUSH = "$push";
    public static final String GROUP = "$group";
    public static final String PROJECT = "$project";
    public static final String PROJECT_ID = "project_id";
    public static final String CURRENT_STATE="$state";
    public static final String WEEK_NUMBER="$week";
    public static final String TYPE="$type";




    public List<Bson> getIssueTrackingMappingAggregationPipeline(String projectId, List<String> states , List<String> types, Date fromDate, Date toDate ) {
        List<Bson> pipeLineStages = new ArrayList<>();
        Document document = new Document().append(PROJECT_ID, projectId);

        document.append("state",new Document().append("$in",states));
        document.append("type",new Document().append("$in",types));
        document.append("changeOn",new Document("$gte", fromDate).append("$lt", toDate));

        pipeLineStages.add(new Document().append(MATCH, document));
        pipeLineStages.add(new Document().append(GROUP, constructGroupStages()));
        pipeLineStages.add(new Document().append(PROJECT, constructProjectStage()));
        return pipeLineStages;
    }

    private Object constructProjectStage() {
        return new Document().append("_id", 1).append("week", "$_id.week").append("state", "$_id.state")
                .append("issues.type", 1).append("issues._id",1).append("count",1);
    }

    private Object constructGroupStages() {
        return new Document()
                .append("_id",
                        new Document().append("state", CURRENT_STATE)
                                .append("week", WEEK_NUMBER).append("type", TYPE))
                .append("count",new Document().append("$sum", 1))
                .append("issues", new Document().append(PUSH, "$$ROOT"));
    }
}
