package com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting;

import com.netcracker.edu.interview.interviewmodeservice.dao.enums.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class NoteRequest {
    private LocalDateTime dateTime;
    private Topic topic;
    private String note;
}
