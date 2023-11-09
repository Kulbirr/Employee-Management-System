package com.example.AulaCube.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentId; //foreign key
}
