package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @NotBlank(message = "Employee Id – Not null")
    @Column(name = "employee_id", length = 20)
    private String employeeId;

    @NotBlank(message = "Employee Name – Not null")
    @Column(name = "employee_name", length = 64)
    private String employeeName;

    @NotNull(message = "Birthday – Not null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotBlank(message = "Phone Number – Not null")
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @NotBlank(message = "Email – Not null")
    @Email(message = "Email is incorrect format")
    private String email;
}