package com.allion.issuetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeLog {
    private String changed_on;
    private String from_state;
    private String to_state;
    private String current_state;
}
