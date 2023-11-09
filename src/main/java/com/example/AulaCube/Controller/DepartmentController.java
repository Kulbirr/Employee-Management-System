package com.example.AulaCube.Controller;

import com.example.AulaCube.DTOS.DepartmentDetails;
import com.example.AulaCube.Entities.Department;
import com.example.AulaCube.Exceptionss.DepartmentExceptions.DepartmentAlreadyExistsException;
import com.example.AulaCube.Exceptionss.DepartmentExceptions.DepartmentDoseNotExistException;
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
    private ResponseEntity getDepartmentDetails(@RequestParam("id") String departmentId){
        try{
            DepartmentDetails result = departmentService.getDepartmentDetails(departmentId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (DepartmentDoseNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{Id}")
    private ResponseEntity updateDepartment(@RequestBody Department updatedDepartment, @PathVariable("Id") String departmentId){
        try{
            Department department = departmentService.updateDepartment(updatedDepartment, departmentId);
            return new ResponseEntity<>(department, HttpStatus.OK);
        }
        catch (DepartmentDoseNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete")
    private ResponseEntity deleteDepartment(@RequestParam("Id")String departmentId){
        try{
            String result = departmentService.deleteDepartment(departmentId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (DepartmentDoseNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
