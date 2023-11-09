package com.example.AulaCube.Service;

import com.example.AulaCube.DTOS.DepartmentDetails;
import com.example.AulaCube.Entities.Department;
import com.example.AulaCube.Exceptionss.DepartmentExceptions.DepartmentAlreadyExistsException;
import com.example.AulaCube.Exceptionss.DepartmentExceptions.DepartmentDoseNotExistException;
import com.example.AulaCube.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public String addDepartment(Department department) throws DepartmentAlreadyExistsException{
        boolean isDepartment = departmentRepository.existsById(department.getId());
        if(isDepartment){
            throw new DepartmentAlreadyExistsException("Department is already present in the company.");
        }

        department.setCreatedAt(new Timestamp(System.currentTimeMillis()));
         departmentRepository.save(department);
         return "Department created Successfully.";
    }

    public DepartmentDetails getDepartmentDetails(String departmentId) throws DepartmentDoseNotExistException{
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);

        if(!departmentOptional.isPresent()){
            throw new DepartmentDoseNotExistException("No department exists with given departmentId : " + departmentId);
        }

        Department department = departmentOptional.get();

        DepartmentDetails departmentDetails = new DepartmentDetails();
//        setting values
        departmentDetails.setDepartmentName(department.getDepartmentName());
        departmentDetails.setCreatedAt(department.getCreatedAt());
        departmentDetails.setUpdatedAT(department.getUpdatedAT());

        return departmentDetails;
    }


    public Department updateDepartment(Department updatedDepartment, String departmentId) throws DepartmentDoseNotExistException{
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);

        if(!departmentOptional.isPresent()){
            throw new DepartmentDoseNotExistException("No department exists with given departmentId : "+ departmentId);
        }

        Department departmentToBeUpdated = departmentOptional.get();

//        updating the department
        departmentToBeUpdated.setDepartmentName(updatedDepartment.getDepartmentName());
        departmentToBeUpdated.setUpdatedAT(new Timestamp(System.currentTimeMillis()));

        departmentRepository.save(departmentToBeUpdated);

        return departmentToBeUpdated;
    }

    public String deleteDepartment(String departmentId) throws DepartmentDoseNotExistException{
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);

        if(!departmentOptional.isPresent()){
            throw new DepartmentDoseNotExistException("No department exists with given departmentId : "+ departmentId);
        }

        Department department = departmentOptional.get();
        departmentRepository.delete(department);
        return "Department with departmentId : " + departmentId + " Successfully deleted";
    }
}
