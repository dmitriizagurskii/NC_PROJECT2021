package com.netcracker.edu.interview.controller;

import com.netcracker.edu.interview.entity.CustomerInformation;


import com.netcracker.edu.interview.entity.CustomerInformationFile;
import com.netcracker.edu.interview.service.CustomerInformationService;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.UUID;


@RestController

public class CustomerInformationController {

    private final CustomerInformationService customerInformationService;

    public CustomerInformationController(CustomerInformationService customerInformationService) {

        this.customerInformationService = customerInformationService;
    }

    @PostMapping("/delete/{customId}")
    public void deleteUser(@PathVariable String customId) {
        customerInformationService.deleteUser(customId);
    }


    @PostMapping("/update")
    public void putUser(@RequestBody CustomerInformation customerInformation) {
        customerInformationService.update(customerInformation);
    }

    @PostMapping()
    public UUID addCustomInformation(@RequestBody CustomerInformation customerInformation) {


        return customerInformationService.saveCustomInformation(customerInformation);
    }

    @PostMapping("/{customId}/addFile")
    public void addCustomInformationFile(@RequestParam("file") MultipartFile file, @PathVariable String customId) throws IOException {
        //
        customerInformationService.saveCustomInformationFile(file, customId);

    }

    @PostMapping("/{customId}/getFile")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable String customId) throws IOException {

        ByteArrayInputStream bis = customerInformationService.getFile(customId);
        return ResponseEntity.ok()
                .body(new InputStreamResource(bis));
    }


}
