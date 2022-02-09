package com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling;

import com.netcracker.edu.interview.interviewmodeservice.dao.enums.InterviewStatus;
import com.netcracker.edu.interview.interviewmodeservice.dao.enums.InterviewType;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class CalendarInterview {
    UUID interviewId;
    UUID interviewer;
    UUID applicant;
    LocalDateTime dateTime;
    InterviewStatus interviewStatus;
    InterviewType interviewType;
}
