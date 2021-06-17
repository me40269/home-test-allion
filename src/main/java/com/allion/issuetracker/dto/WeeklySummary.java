package com.allion.issuetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklySummary  implements Serializable {
    public String week;
    public List<StateSummary> state_summaries;
}
