package com.netcracker.edu.interview.repository;

import com.netcracker.edu.interview.entity.CustomerInformation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CustomerInformationRepository extends CrudRepository<CustomerInformation, Integer> {
    Iterable<CustomerInformation> findByFileId(@Param("fileId") UUID fileId);

    boolean existsByFileId(@Param("fileId") UUID fileId);



    void deleteByFileId(@Param("fileId") UUID fileId);

    @Modifying
    @Query("update CustomerInformation u set u.firstName = ?1, u.secondName = ?2,u.years = ?3,u.education = ?4,u.employer = ?5 where u.email = ?6")
    void setUserInfoById(String firstname, String secondName,String years,String education,String employer, String email);
}
