package com.netcracker.edu.interview.interviewmodeservice.dao;

import com.netcracker.edu.interview.interviewmodeservice.dao.enums.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    private UUID id;

    @NonNull private LocalDateTime dateTime;
    @NonNull private String text;
    @NonNull private Topic topic;
    @ManyToOne
    private Interview interview;
}
