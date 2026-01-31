package com.example.demo.controller.api;

import ch.qos.logback.core.model.Model;
import com.example.demo.entity.Employee;
import com.example.demo.service.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService service;

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
        // 1. Validate: Nếu thiếu thông tin thì trả về view soạn thảo (Add/Edit)
        if (result.hasErrors()) {
            return "employee-form";
        }

        // 2. Kiểm tra sự tồn tại để quyết định Create hay Update
        // Dùng get theo nguyên tắc của bạn: phải có dữ liệu mới không văng lỗi
        boolean exists = false;
        try {
            service.getEmployeeDetail(employee.getEmployeeId());
            exists = true;
        } catch (Exception e) {
            exists = false;
        }

        if (exists) {
            // Thực hiện hành động Update (thay đổi thực sự dữ liệu cũ)
            service.updateEmployee(employee);
        } else {
            // Thực hiện hành động Create (commit nhân viên mới vào DB)
            service.createEmployee(employee);
        }

        return "redirect:/employee/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.removeEmployee(id);
        return "redirect:/employee/list";
    }
}