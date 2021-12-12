package com.netcracker.edu.interview.repository;

import com.netcracker.edu.interview.entity.CustomerInformation;
import com.netcracker.edu.interview.entity.CustomerInformationFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CustomerInformationFileRepository extends CrudRepository<CustomerInformationFile, Integer> {

    Iterable<CustomerInformationFile> findByIdCustomer(@Param("id_cust") CustomerInformation idCustomer);
}
