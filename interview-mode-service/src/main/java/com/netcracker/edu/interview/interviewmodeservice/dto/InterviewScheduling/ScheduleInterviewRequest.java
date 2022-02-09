package com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling;

import com.netcracker.edu.interview.interviewmodeservice.dao.enums.InterviewType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ScheduleInterviewRequest {
    private UUID interviewer;
    private UUID applicant;
    private LocalDateTime date;
    private InterviewType interviewType;
}
