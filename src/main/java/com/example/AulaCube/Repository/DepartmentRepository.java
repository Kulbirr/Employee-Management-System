package com.example.AulaCube.Repository;

import com.example.AulaCube.Entities.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department, String> {

}
