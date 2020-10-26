package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Employee {
    private String id;
    private String employeename;
    private Double salary;
    private Integer age;
    private Date birthday;
}
