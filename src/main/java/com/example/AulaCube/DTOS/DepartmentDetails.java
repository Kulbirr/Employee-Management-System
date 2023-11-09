package com.example.AulaCube.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDetails {

    private String departmentName;

    private Timestamp createdAt;

    private Timestamp updatedAT;
}
