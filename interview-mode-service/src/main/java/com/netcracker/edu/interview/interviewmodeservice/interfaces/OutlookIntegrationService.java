package com.netcracker.edu.interview.interviewmodeservice.interfaces;

import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.ScheduleInterviewRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import java.time.LocalDateTime;


public interface OutlookIntegrationService {

    String scheduleMeeting(ScheduleInterviewRequest scheduleInterviewRequest, OAuth2AuthorizedClient graphAuthorizedClient);

    void rescheduleMeeting(String outlookId, LocalDateTime dateTime, OAuth2AuthorizedClient graphAuthorizedClient);

    void cancelMeeting(String outlookId, OAuth2AuthorizedClient graphAuthorizedClient);
}
