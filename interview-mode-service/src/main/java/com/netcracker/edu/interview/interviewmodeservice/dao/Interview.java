package com.netcracker.edu.interview.interviewmodeservice.dao;

import com.netcracker.edu.interview.interviewmodeservice.dao.enums.InterviewStatus;
import com.netcracker.edu.interview.interviewmodeservice.dao.enums.InterviewType;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Interview {
    @Id
    @NonNull private UUID uid;
    @NonNull private UUID interviewer;
    @NonNull private UUID applicant;
    @NonNull private String outlookId;
    @NonNull private LocalDateTime startDateTime;
    @NonNull private InterviewStatus interviewStatus;
    @NonNull private InterviewType interviewType;
    private LocalDateTime endDateTime;
    private String interviewReport;

    @OneToMany(cascade = CascadeType.ALL)
    private List<InterviewTask> interviewTasks = new ArrayList<>();

    @OneToMany
    private List<Note> notes = new ArrayList<>();



}
