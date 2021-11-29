package com.netcracker.edu.interview.service;

import com.netcracker.edu.interview.entity.CustomerInformation;
import com.netcracker.edu.interview.entity.CustomerInformationFile;
import com.netcracker.edu.interview.repository.CustomerInformationFileRepository;
import com.netcracker.edu.interview.repository.CustomerInformationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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
            CustomerInformationFile customerInformationFile = new CustomerInformationFile(file.getOriginalFilename(), file.getBytes());
            customerInformationFileRepository.save(customerInformationFile);

            Iterable<CustomerInformation> interviewer = customerInformationRepository.findByFileId(UUID.fromString(inputeUuid));
            CustomerInformation customerInformation = interviewer.iterator().next();
            customerInformation.addFile(customerInformationFile);

            customerInformationRepository.save(customerInformation);
        }

    }

    public byte[] getFile(String fileName) {
        Iterable<CustomerInformation> interviewer = customerInformationRepository.findByFileId(UUID.fromString(fileName));
        CustomerInformation customerInformation = interviewer.iterator().next();
        // Collection<CustomerInformationFile> a=customerInformation.getFile();

        Iterable<CustomerInformationFile> a = customerInformationFileRepository.findAll();
        CustomerInformationFile file = a.iterator().next();
         return file.getData();

    }
}
