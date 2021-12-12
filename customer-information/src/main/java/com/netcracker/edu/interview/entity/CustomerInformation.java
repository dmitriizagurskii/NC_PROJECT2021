package com.netcracker.edu.interview.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "customerInformation")
@Data
public class CustomerInformation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "email")
    private String email;
    @Column(name = "years")
    private String years;
    @Column(name = "education")
    private String education;
    @Column(name = "experience")
    private String employer;
    @Column(name = "file_id")
    private UUID fileId;





    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

   // public void addFile(CustomerInformationFile customerInformationFile){
   //     files.add(customerInformationFile);
   // }
 //   public Collection<CustomerInformationFile> getFile(){
  //      return files;
  //  }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }
    public long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public UUID getFileId() {
        return fileId;
    }
}