package com.example.demo.controller.view;

import com.example.demo.service.IEmployeeService;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeViewController {
    @Autowired
    private IEmployeeService service;

    @GetMapping("/list")
    public String listPage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            // find: tìm kiếm theo input (Yêu cầu số 5)
            model.addAttribute("employees", service.findEmployees(keyword));
        } else {
            // list: trả về toàn bộ
            model.addAttribute("employees", service.listEmployees());
        }
        return "employee-list";
    }

    @GetMapping("/add")
    public String addModal(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @GetMapping("/edit/{id}")
    public String editModal(@PathVariable String id, Model model) {
        model.addAttribute("employee", service.getEmployeeDetail(id));
        return "employee-form";
    }
}