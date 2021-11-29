package com.netcracker.edu.interview.serviceforauth.model.repository;

import com.netcracker.edu.interview.serviceforauth.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findPoliciesByResourceAndAlgorithm(String resource, String algorithm);
}
