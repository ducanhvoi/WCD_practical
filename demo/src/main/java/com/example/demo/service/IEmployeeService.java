package com.example.demo.service;

import com.example.demo.entity.Employee;
import java.util.List;

public interface IEmployeeService {
    List<Employee> listEmployees();
    Employee getEmployeeDetail(String id);
    void createEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void removeEmployee(String id);
    List<Employee> findEmployees(String keyword);
}