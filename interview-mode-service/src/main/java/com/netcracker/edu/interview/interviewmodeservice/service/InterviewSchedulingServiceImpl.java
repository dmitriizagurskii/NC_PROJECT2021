package com.netcracker.edu.interview.interviewmodeservice.service;


import com.netcracker.edu.interview.interviewmodeservice.dao.Interview;
import com.netcracker.edu.interview.interviewmodeservice.dao.enums.InterviewStatus;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.CalendarInterview;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.RescheduleInterviewRequest;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.ScheduleInterviewRequest;
import com.netcracker.edu.interview.interviewmodeservice.interfaces.OutlookIntegrationService;
import com.netcracker.edu.interview.interviewmodeservice.repository.InterviewRepository;
import com.netcracker.edu.interview.interviewmodeservice.interfaces.InterviewSchedulingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InterviewSchedulingServiceImpl implements InterviewSchedulingService {

    private InterviewRepository interviewRepository;
    private OutlookIntegrationService outlookIntegrationService;

    @Autowired
    private void setInterviewRepository(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

   @Autowired
   private void setOutlookIntegrationService(OutlookIntegrationService outlookIntegrationService) {
        this.outlookIntegrationService = outlookIntegrationService;
   }

    @Override
    public Interview scheduleInterview(ScheduleInterviewRequest scheduleReq, OAuth2AuthorizedClient graphAuthorizedClient) {
        String outlookId = outlookIntegrationService.scheduleMeeting(scheduleReq, graphAuthorizedClient);

        Interview interview = new Interview(
                UUID.randomUUID(),
                scheduleReq.getInterviewer(),
                scheduleReq.getApplicant(),
                outlookId,
                scheduleReq.getDate(),
                InterviewStatus.SCHEDULED,
                scheduleReq.getInterviewType()
        );

        interviewRepository.save(interview);
        return interview;
    }

    @Override
    public void rescheduleInterview(UUID id, RescheduleInterviewRequest rescheduleRequest,
                                    OAuth2AuthorizedClient graphAuthorizedClient) {
        Interview interview = interviewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        outlookIntegrationService.rescheduleMeeting(interview.getOutlookId(), rescheduleRequest.getDateTime(), graphAuthorizedClient);
        interviewRepository.updateInterviewDate(id, rescheduleRequest.getDateTime());
    }

    @Override
    public List<CalendarInterview> getCalendarForUser(UUID userId) {
        List<Interview> interviews =
                interviewRepository.findAllByInterviewerOrApplicantOrderByStartDateTimeAsc(userId, userId);
        List<CalendarInterview> calendarInterviews = interviews.stream()
                .map(interview -> new CalendarInterview(
                        interview.getUid(),
                        interview.getInterviewer(),
                        interview.getApplicant(),
                        interview.getStartDateTime(),
                        interview.getInterviewStatus(),
                        interview.getInterviewType()
                ))
                .collect(Collectors.toList());
        return calendarInterviews;
    }

    @Override
    public void cancelInterview(UUID interviewId, OAuth2AuthorizedClient graphAuthorizedClient) {
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new EntityNotFoundException());
        outlookIntegrationService.cancelMeeting(interview.getOutlookId(), graphAuthorizedClient);
        interviewRepository.deleteById(interviewId);
    }



}
