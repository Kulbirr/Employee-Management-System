package com.example.AulaCube.Controller;

import com.example.AulaCube.DTOS.EmployeeDetails;
import com.example.AulaCube.Entities.Employee;
import com.example.AulaCube.Exceptions.EmployeeAlreadyPresent;
import com.example.AulaCube.Exceptions.EmployeeNotFoundException;
import com.example.AulaCube.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("add")
    private ResponseEntity addEmloyee(@RequestBody Employee employee) {
        try {
            String result = employeeService.addEmployee(employee);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (EmployeeAlreadyPresent e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get")
    private ResponseEntity getEmployee(@RequestParam("id") Long employeeId) {
        try {
            EmployeeDetails employeeDetails = employeeService.getEmployee(employeeId);
            return new ResponseEntity<>(employeeDetails, HttpStatus.OK);
        }
        catch (EmployeeNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{employeeId}")
    private ResponseEntity updateEmployee(@PathVariable("employeeId") Long employeId, @RequestBody Employee updatedEmployee){
        try{
            Employee employee = employeeService.updateEmployee(employeId, updatedEmployee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        catch (EmployeeNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete")
    private ResponseEntity deleteEmployee(@RequestParam("id") Long employeeId){
        try{
            String result = employeeService.deleteEmployee(employeeId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (EmployeeNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
