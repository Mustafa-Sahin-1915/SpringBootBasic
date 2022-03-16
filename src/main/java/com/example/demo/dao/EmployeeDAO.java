package com.example.demo.dao;

import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> get();

    Employee get(int id);

    void save(Employee employee);

    void delete(int id);
}
