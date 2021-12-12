package com.netcracker.edu.interview.service;

import com.netcracker.edu.interview.entity.CustomerInformation;
import com.netcracker.edu.interview.entity.CustomerInformationFile;
import com.netcracker.edu.interview.repository.CustomerInformationFileRepository;
import com.netcracker.edu.interview.repository.CustomerInformationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class CustomerInformationService {
    private final CustomerInformationFileRepository customerInformationFileRepository;
    private final CustomerInformationRepository customerInformationRepository;

    public CustomerInformationService(CustomerInformationFileRepository customerInformationFileRepository, CustomerInformationRepository customerInformationRepository) {
        this.customerInformationFileRepository = customerInformationFileRepository;
        this.customerInformationRepository = customerInformationRepository;
    }

    public UUID saveCustomInformation(CustomerInformation customerInformation) {
        UUID idFile = UUID.randomUUID();
        customerInformation.setFileId(idFile);
        customerInformationRepository.save(customerInformation);

        return idFile;
    }

    @Transactional
    public void update(CustomerInformation customerInformation) {
        customerInformationRepository.setUserInfoById(customerInformation.getFirstName(),
                customerInformation.getFirstName(), customerInformation.getYears(), customerInformation.getEducation(),
                customerInformation.getEmployer(), customerInformation.getEmail());

    }

    @Transactional
    public void deleteUser(String uuid) {
        UUID a = UUID.fromString(uuid);
        customerInformationRepository.deleteByFileId(a);
    }


    public void saveCustomInformationFile(MultipartFile file, String inputeUuid) throws IOException {

        if (customerInformationRepository.existsByFileId(UUID.fromString(inputeUuid))) {
            Iterable<CustomerInformation> interviewer = customerInformationRepository.findByFileId(UUID.fromString(inputeUuid));
            CustomerInformation customerInformation = interviewer.iterator().next();
            CustomerInformationFile customerInformationFile = new CustomerInformationFile(file.getOriginalFilename(), file.getBytes());
            customerInformationFile.setIdCustomer(customerInformation);
            customerInformationFileRepository.save(customerInformationFile);
            customerInformationRepository.save(customerInformation);
        }

    }

    @Transactional
    public ByteArrayInputStream getFile(String fileName) throws IOException {
        Iterable<CustomerInformation> interviewer = customerInformationRepository.findByFileId(UUID.fromString(fileName));
        CustomerInformation customerInformation = interviewer.iterator().next();
        Iterable<CustomerInformationFile> a = customerInformationFileRepository.findByIdCustomer(customerInformation);
        ByteArrayInputStream bis = null;

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        for (CustomerInformationFile file : a) {

            outStream.write(file.getData());

        }

        byte[] mass = outStream.toByteArray();
        bis = new ByteArrayInputStream(mass);

        return bis;

    }
}
