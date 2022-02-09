package com.netcracker.edu.interview.interviewmodeservice.repository;

import com.netcracker.edu.interview.interviewmodeservice.dao.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NoteRepository extends CrudRepository<Note, UUID> {

}
