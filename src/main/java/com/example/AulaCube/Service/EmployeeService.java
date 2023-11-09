package com.example.AulaCube.Service;

import com.example.AulaCube.DTOS.EmployeeDetails;
import com.example.AulaCube.Entities.Employee;
import com.example.AulaCube.Exceptions.EmployeeAlreadyPresent;
import com.example.AulaCube.Exceptions.EmployeeNotFoundException;
import com.example.AulaCube.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public String addEmployee(Employee employee) throws EmployeeAlreadyPresent{
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        if(employeeOptional.isPresent()){
            throw new EmployeeAlreadyPresent("Employee Already exists.");
        }
        employee.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        employeeRepository.save(employee);
        return "Employee has been created Successfully.";
    }

    public EmployeeDetails getEmployee(Long employeeId) throws EmployeeNotFoundException{
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if(!employeeOptional.isPresent()){
            throw new EmployeeNotFoundException("There is no employee with given employeeId :" + employeeId);
        }

        Employee employee = employeeOptional.get();

        EmployeeDetails employeeDetails = new EmployeeDetails();
//        Setting employeeDetails
        employeeDetails.setId(employeeId);
        employeeDetails.setEmail(employee.getEmail());
        employeeDetails.setDepartmentId(employee.getDepartmentId());
        employeeDetails.setFirstName(employee.getFirstName());
        employeeDetails.setLastName(employee.getLastName());

        return employeeDetails;
    }


    public Employee updateEmployee(Long employeId, Employee updatedEmployee) throws EmployeeNotFoundException{
        Optional<Employee> employeeOptional = employeeRepository.findById(employeId);

        if(!employeeOptional.isPresent()){
            throw new EmployeeNotFoundException("There is no employee with given employee id :" + employeId);
        }

        Employee employeeToBeUpdated = employeeOptional.get();

        //Updating the employee
        employeeToBeUpdated.setId(updatedEmployee.getId());
        employeeToBeUpdated.setFirstName(updatedEmployee.getFirstName());
        employeeToBeUpdated.setLastName(updatedEmployee.getLastName());
        employeeToBeUpdated.setEmail(updatedEmployee.getEmail());
        employeeToBeUpdated.setDepartmentId(updatedEmployee.getDepartmentId());
        employeeToBeUpdated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return employeeToBeUpdated;
    }


    public String deleteEmployee(Long employeeId) throws EmployeeNotFoundException{
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if(!employeeOptional.isPresent()){
            throw new EmployeeNotFoundException("There is no employee with give employeeId :" + employeeId);
        }

        Employee employee = employeeOptional.get();

        employeeRepository.delete(employee);
        return "Employee with given employeeId : "+employeeId+ " has been Successfully deleted.";
    }
}
