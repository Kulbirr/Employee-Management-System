package com.example.AulaCube.Controller;

import com.example.AulaCube.DTOS.DepartmentDetails;
import com.example.AulaCube.Entities.Department;
import com.example.AulaCube.Exceptions.EmployeeExceptions.DepartmentExceptions.DepartmentAlreadyExistsException;
import com.example.AulaCube.Exceptions.EmployeeExceptions.DepartmentExceptions.DepartmentDoseNotExistException;
import com.example.AulaCube.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;


    @PostMapping("add")
    private ResponseEntity addDepartment(@RequestBody Department department){
        try{
            String result = departmentService.addDepartment(department);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (DepartmentAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get")
    private ResponseEntity getDepartmentDetails(@RequestParam String departmentId){
        try{
            DepartmentDetails result = departmentService.getDepartmentDetails(departmentId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (DepartmentDoseNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
