package com.netcracker.edu.interview.interviewmodeservice.interfaces;

import com.netcracker.edu.interview.interviewmodeservice.dao.Interview;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.CalendarInterview;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.RescheduleInterviewRequest;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.ScheduleInterviewRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import java.util.List;
import java.util.UUID;

public interface InterviewSchedulingService {

    Interview scheduleInterview(ScheduleInterviewRequest scheduleReq, OAuth2AuthorizedClient graphAuthorizedClient);
    void cancelInterview(UUID interviewId, OAuth2AuthorizedClient graphAuthorizedClient);
    void rescheduleInterview(UUID id, RescheduleInterviewRequest rescheduleInterviewRequest, OAuth2AuthorizedClient graphAuthorizedClient);

    List<CalendarInterview> getCalendarForUser(UUID userId);
}
