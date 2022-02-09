package com.netcracker.edu.interview.interviewmodeservice.interfaces;

import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting.NoteRequest;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting.NoteResponse;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting.TaskEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface InterviewConductingService {

    UUID startTask(TaskEvent taskEvent);
    void endTask(UUID id);
    UUID makeNote(UUID id, NoteRequest noteRequest);
    void finishInterview(UUID id);
    List<NoteResponse> getInterviewNotes(UUID interviewId);

    void saveInterviewReport(UUID id, String report);

    void submitInterviewReport(UUID id);
}
