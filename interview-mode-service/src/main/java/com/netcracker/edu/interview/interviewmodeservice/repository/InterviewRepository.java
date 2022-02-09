package com.netcracker.edu.interview.interviewmodeservice.repository;

import com.netcracker.edu.interview.interviewmodeservice.dao.Interview;
import com.netcracker.edu.interview.interviewmodeservice.dao.enums.InterviewStatus;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface InterviewRepository extends CrudRepository<Interview, UUID> {

    @Modifying
    @Query("update Interview u set u.startDateTime = :dateTime where u.uid = :id")
    void updateInterviewDate(@Param(value="id") UUID uid, @Param(value="dateTime") LocalDateTime dateTime);

    List<Interview> findAllByInterviewerOrApplicantOrderByStartDateTimeAsc(@NonNull UUID interviewer, @NonNull UUID applicant);

    @Modifying
    @Query("update Interview u set u.interviewReport = :report where u.uid = :id")
    void updateInterviewReport(@Param(value="id") UUID id, @Param(value="report") String report);

    @Modifying
    @Query("update Interview u set u.interviewStatus = :status where u.uid = :id")
    void updateInterviewStatus(@Param(value="id") UUID id, @Param(value="status") InterviewStatus status);

}
