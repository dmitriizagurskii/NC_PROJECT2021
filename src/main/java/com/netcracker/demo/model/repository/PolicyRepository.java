package com.netcracker.demo.model.repository;

import com.netcracker.demo.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findPoliciesByResourceAndAlgorithm(String resource, String algorithm);
}
