package com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class RescheduleInterviewRequest {
    private LocalDateTime dateTime;
}
