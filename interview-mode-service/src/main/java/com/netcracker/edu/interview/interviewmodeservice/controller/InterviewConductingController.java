package com.netcracker.edu.interview.interviewmodeservice.controller;

import com.azure.core.annotation.Patch;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting.NoteRequest;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting.NoteResponse;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewConducting.TaskEvent;
import com.netcracker.edu.interview.interviewmodeservice.interfaces.InterviewConductingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
public class InterviewConductingController {

    private InterviewConductingService interviewConductingService;

    @Autowired
    private void setInterviewingService(InterviewConductingService interviewConductingService) {
        this.interviewConductingService = interviewConductingService;
    }

    @PutMapping ("/task/start")
    public UUID startTask(@RequestBody TaskEvent taskEvent) {
        return interviewConductingService.startTask(taskEvent);
    }

    @PutMapping("/task/{id}/end")
    public void endTask(@PathVariable UUID id) {
        interviewConductingService.endTask(id);
    }

    @PostMapping("/interview/{id}/note")
    public UUID makeNote(@PathVariable UUID id, @RequestBody NoteRequest noteRequest) {
        return interviewConductingService.makeNote(id, noteRequest);
    }

    @PatchMapping("/interview/{id}/finish")
    public void finishInterview(@PathVariable UUID id) {
        interviewConductingService.finishInterview(id);
    }

    @PatchMapping("/interview/{id}/report/save")
    public void saveInterviewReport (@PathVariable UUID id, @RequestBody String report) {
        interviewConductingService.saveInterviewReport(id, report);
    }

    @PatchMapping("/interview/{id}/report/submit")
    public void submitReport(@PathVariable UUID id) {
        interviewConductingService.submitInterviewReport(id);
    }

    @GetMapping("/interview/{id}/notes")
    public List<NoteResponse> getAllNotesForInterview(@PathVariable UUID id) {
        return interviewConductingService.getInterviewNotes(id);
    }

}