package com.allion.issuetracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "project.tracker")
@Builder
@AllArgsConstructor
public class ProjectTrackingInfo {
    @Id
    @Field("project_id")
    private String projectId;
}
