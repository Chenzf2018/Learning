package com.example.service;

import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Chenzf
 * @date 2020/10/26
 */

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Employee> findAllEmployee() {
        return employeeDAO.findAllEmployee();
    }

    @Override
    public void addEmployee(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        employeeDAO.addEmployee(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        employeeDAO.deleteEmployee(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Employee findEmployee(String id) {
        return employeeDAO.findEmployee(id);
    }

    @Override
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }
}
