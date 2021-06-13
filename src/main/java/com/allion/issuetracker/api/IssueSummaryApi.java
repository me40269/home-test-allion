package com.allion.issuetracker.api;

import com.allion.issuetracker.dto.WeeklySummaryRequest;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "Issue  Tracker")
@RequestMapping(path = "/allion")
public interface IssueSummaryApi {
    @PostMapping(value = "/issue/summary/weeks", produces = {"application/json"})
    ResponseEntity<?> getWeeklySummary( @RequestBody WeeklySummaryRequest weeklySummaryRequest);
}
