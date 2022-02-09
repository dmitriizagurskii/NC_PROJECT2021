package com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting;

import com.netcracker.edu.interview.interviewmodeservice.dao.enums.Topic;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class NoteResponse {

    private UUID id;

    private LocalDateTime dateTime;
    private String text;
    private Topic topic;
}
