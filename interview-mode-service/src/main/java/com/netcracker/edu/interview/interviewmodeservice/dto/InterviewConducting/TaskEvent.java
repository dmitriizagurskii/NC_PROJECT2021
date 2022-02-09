package com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class TaskEvent {
    private UUID interviewId;
    private UUID taskId;
    private String name;
    private LocalTime startTime;

}
