package com.example.demo.repository;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> findByName(String name);
}
