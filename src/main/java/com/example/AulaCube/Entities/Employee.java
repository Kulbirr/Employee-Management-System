package com.example.AulaCube.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String departmentId; //foreign key

    private Timestamp createdAt;

    private Timestamp updatedAt;

//    @PrePersist
//    public void prePersist() {
//        if (createdAt == null) {
//            createdAt = new Timestamp(Instant.now().toEpochMilli());
//        }
//    }
//
//    @PreUpdate
//    public void PreUpdate(){
//        updatedAt = new Timestamp(Instant.now().EPOCH.toEpochMilli())
//    }

}
