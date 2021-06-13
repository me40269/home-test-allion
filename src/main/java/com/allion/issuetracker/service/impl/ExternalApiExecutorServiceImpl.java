package com.allion.issuetracker.service.impl;

import com.allion.issuetracker.dto.GenericResponse;
import com.allion.issuetracker.dto.IssueTrackingResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiExecutorServiceImpl {

    Logger logger = LoggerFactory.getLogger(ExternalApiExecutorServiceImpl.class);

    @Value("${issue.tracking.url}")
    private String issueTrackingUrl;

    @Autowired
    RestTemplate restTemplate;

    @CircuitBreaker(name = "service1",fallbackMethod = "fallbackForThirdPartyIssueTracking")
    @RateLimiter(name = "service1")
    public GenericResponse getIssueInfo(String projectId){
        logger.info("calling to the external issue tracking service as {} is not in the local database",projectId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("projectId",projectId);
        IssueTrackingResponseDto issueTrackingResponseDto = restTemplate.postForObject(issueTrackingUrl,jsonObject.toString(),IssueTrackingResponseDto.class);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponse(issueTrackingResponseDto);
       return genericResponse;
    }


    public GenericResponse fallbackForThirdPartyIssueTracking(String projectId, Throwable throwable){
        logger.info("Inside the fallback for Third Party Issue Tracking System, error {}" ,throwable.toString());
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setErrorMsg(throwable.getMessage());
        return genericResponse;
    }
}
