package com.netcracker.edu.interview.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "customerInformationFile")
@Data
public class CustomerInformationFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;

    @Column(name="content")
    @Lob
    private byte[] data;


    @ManyToMany(mappedBy = "files")
    private Collection<CustomerInformation> users;

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public CustomerInformationFile(String name) {
        this.name = name;

    }

    public CustomerInformationFile(String name, byte[] data) {
        this.name = name;

        this.data = data;
    }

    public CustomerInformationFile() {
    }

    public byte[] getData() {
        return data;
    }

    public CustomerInformationFile(long id) {
        this.id = id;
    }


}
