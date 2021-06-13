package com.allion.issuetracker.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "issue.tracker")
@Builder
@AllArgsConstructor
public class IssueTracker {
    @Id
    @Field("issue_id")
    private String issueId;

    @Field("project_id")
    private String projectId;

    @Field("state")
    private String currentState;

    private String type;

    @Field("week")
    private String weekNumber;

    private Date changeOn;


}
