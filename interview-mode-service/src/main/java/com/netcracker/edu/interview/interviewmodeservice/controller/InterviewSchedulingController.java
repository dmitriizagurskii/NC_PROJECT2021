package com.netcracker.edu.interview.interviewmodeservice.controller;

import com.netcracker.edu.interview.interviewmodeservice.dao.Interview;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.CalendarInterview;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.RescheduleInterviewRequest;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.ScheduleInterviewRequest;
import com.netcracker.edu.interview.interviewmodeservice.interfaces.InterviewSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class InterviewSchedulingController {

    InterviewSchedulingService interviewSchedulingService;

    @Autowired
    private void setScheduleInterviewService(InterviewSchedulingService interviewSchedulingService) {
        this.interviewSchedulingService = interviewSchedulingService;
    }


    @PostMapping("/interview/schedule")
    public Interview scheduleInterview(@RequestBody ScheduleInterviewRequest scheduleReq,
                                       @RegisteredOAuth2AuthorizedClient("graph") OAuth2AuthorizedClient graphAuthorizedClient) {
        Interview interviewResp = interviewSchedulingService.scheduleInterview(scheduleReq, graphAuthorizedClient);
        return interviewResp;
    }

    @PatchMapping("/interview/{id}")
    public void rescheduleInterview(@PathVariable UUID id, @RequestBody RescheduleInterviewRequest rescheduleRequest,
                                    @RegisteredOAuth2AuthorizedClient("graph") OAuth2AuthorizedClient graphAuthorizedClient) {
        interviewSchedulingService.rescheduleInterview(id, rescheduleRequest, graphAuthorizedClient);
    }



    @DeleteMapping("/interview/{id}")
    public void cancelInterview(@PathVariable UUID id,
                                @RegisteredOAuth2AuthorizedClient("graph") OAuth2AuthorizedClient graphAuthorizedClient) {
        interviewSchedulingService.cancelInterview(id, graphAuthorizedClient);
    }

    @GetMapping("/calendar/user/{userId}")
    public List<CalendarInterview> getScheduledInterviewForUser(@PathVariable UUID userId) {
        return interviewSchedulingService.getCalendarForUser(userId);
    }




}
