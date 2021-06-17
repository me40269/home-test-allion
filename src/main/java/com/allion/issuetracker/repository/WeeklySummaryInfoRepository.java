package com.allion.issuetracker.repository;

import com.allion.issuetracker.model.ProjectTrackingInfo;
import com.allion.issuetracker.model.WeeklySummaryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeeklySummaryInfoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<WeeklySummaryInfo> getAllSummaryInfoRequest(){
        List<WeeklySummaryInfo> weeklySummaryInfos = mongoTemplate.findAll(WeeklySummaryInfo.class);
        return weeklySummaryInfos;
    }

    public void saveWeeklySummaryInfoRequests(WeeklySummaryInfo weeklySummaryInfo){
        mongoTemplate.save(weeklySummaryInfo);
    }
}
