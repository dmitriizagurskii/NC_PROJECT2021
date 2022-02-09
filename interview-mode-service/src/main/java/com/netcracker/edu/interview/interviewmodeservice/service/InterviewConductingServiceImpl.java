package com.netcracker.edu.interview.interviewmodeservice.service;
import com.netcracker.edu.interview.interviewmodeservice.dao.Interview;
import com.netcracker.edu.interview.interviewmodeservice.dao.InterviewTask;
import com.netcracker.edu.interview.interviewmodeservice.dao.Note;
import com.netcracker.edu.interview.interviewmodeservice.dao.enums.InterviewStatus;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting.NoteRequest;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting.NoteResponse;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting.TaskEvent;
import com.netcracker.edu.interview.interviewmodeservice.interfaces.InterviewConductingService;
import com.netcracker.edu.interview.interviewmodeservice.repository.InterviewTaskRepository;
import com.netcracker.edu.interview.interviewmodeservice.repository.InterviewRepository;
import com.netcracker.edu.interview.interviewmodeservice.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InterviewConductingServiceImpl implements InterviewConductingService {
    InterviewRepository interviewRepository;
    InterviewTaskRepository interviewTaskRepository;
    NoteRepository noteRepository;

    @Autowired
    public void setInterviewRepository(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Autowired
    public void setInterviewEventRepository(InterviewTaskRepository interviewTaskRepository) {
        this.interviewTaskRepository = interviewTaskRepository;
    }

    @Autowired
    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public UUID startTask(TaskEvent taskEvent) {
        UUID interviewId = taskEvent.getInterviewId();
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new EntityNotFoundException("Interview with id " + interviewId + " not found"));
        InterviewTask interviewTask = new InterviewTask(
                UUID.randomUUID(),
                taskEvent.getTaskId(),
                taskEvent.getName(),
                LocalDateTime.now(),
                interview
        );

        interviewTaskRepository.save(interviewTask);
        return interviewTask.getUid();
    }

    @Override
    public void endTask(UUID id) {
        InterviewTask interviewTask = interviewTaskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        interviewTask.setEndTime(LocalDateTime.now());
        interviewTaskRepository.save(interviewTask);
    }

    @Override
    public UUID makeNote(UUID id, NoteRequest noteRequest) {
        Interview interview = interviewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        Note note = new Note(
                UUID.randomUUID(),
                noteRequest.getDateTime(),
                noteRequest.getNote(),
                noteRequest.getTopic(),
                interview
        );
        noteRepository.save(note);
        return note.getId();
    }

    @Override
    public void finishInterview(UUID id) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interview with id " + id + " not found"));
        interview.setEndDateTime(LocalDateTime.now());
        interview.setInterviewStatus(InterviewStatus.WAITING_FOR_FEEDBACK);
        interviewRepository.save(interview);
    }

    @Override
    public List<NoteResponse> getInterviewNotes(UUID interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new EntityNotFoundException("Interview with id " + interviewId + " not found"));
        List<Note> notes = interview.getNotes();
        List<NoteResponse> noteResponseList = notes.stream()
                .map(note -> new NoteResponse(note.getId(), note.getDateTime(), note.getText(), note.getTopic()))
                .collect(Collectors.toList());
        return noteResponseList;
    }

    @Override
    public void saveInterviewReport(UUID id, String report) {
        interviewRepository.updateInterviewReport(id, report);
    }

    @Override
    public void submitInterviewReport(UUID id) {
        interviewRepository.updateInterviewStatus(id, InterviewStatus.FEEDBACK_RECEIVED);
    }


}
