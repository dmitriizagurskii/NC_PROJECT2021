package com.netcracker.edu.interview.interviewmodeservice.dao;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
public class InterviewTask {
    @Id
    @NonNull private UUID uid;
    @NonNull private UUID taskId;
    @NonNull private String name;

    @NonNull private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull private Interview interview;




}
