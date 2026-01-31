package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository repo;

    @Override
    public List<Employee> listEmployees() {
        return repo.findAll();
    }

    @Override
    public Employee getEmployeeDetail(String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public void createEmployee(Employee employee) {
        repo.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        repo.save(employee);
    }

    @Override
    public void removeEmployee(String id) {
        repo.deleteById(id);
    }

    @Override
    public List<Employee> findEmployees(String keyword) {
        return repo.findByEmployeeIdContainingOrEmployeeNameContaining(keyword, keyword);
    }
}