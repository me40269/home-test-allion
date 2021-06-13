package com.allion.issuetracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@ApiModel
public class StateSummary {
    public int count;

    public List<IssueDto> issues;

    @JsonIgnore
    public String week;

    public String state;

    public static void main(String[] args) {
        StateSummary stateSummary2 =new StateSummaryBuilder().week("2017W01").count(2).issues(Arrays.asList(new IssueDto("issue1","bug"),new IssueDto("issue2","bug"))).state("open").build();

        StateSummary stateSummary =new StateSummaryBuilder().week("2017W01").count(2).issues(Arrays.asList(new IssueDto("issue3","bug"),new IssueDto("issue4","bug"))).state("open").build();
        StateSummary stateSummary3 =new StateSummaryBuilder().week("2017W03").count(2).issues(Arrays.asList(new IssueDto("issue1","bug"),new IssueDto("issue2","bug"))).state("open").build();

        List<StateSummary> stateSummaryList = new ArrayList<>();
        stateSummaryList.add(stateSummary);
        stateSummaryList.add(stateSummary2);
        stateSummaryList.add(stateSummary3);
        Map<String,List<StateSummary>> mm = stateSummaryList.stream().collect(Collectors.groupingBy(ws -> ws.getWeek()));
        System.out.println(mm);
    }
}
