package com.netcracker.edu.interview.interviewmodeservice.repository;

import com.netcracker.edu.interview.interviewmodeservice.dao.Interview;
import com.netcracker.edu.interview.interviewmodeservice.dao.InterviewTask;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InterviewTaskRepository extends CrudRepository<InterviewTask, UUID> {

    InterviewTask findByInterviewAndTaskId(Interview interview, UUID taskId);
}
