package com.allion.issuetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    private String issue_id;
    private String type;
    private String current_state;
    private List<ChangeLog> changelogs;
}
