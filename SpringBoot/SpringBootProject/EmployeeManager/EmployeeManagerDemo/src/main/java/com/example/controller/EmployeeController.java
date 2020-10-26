package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Chenzf
 */

@Controller
@RequestMapping("/EmployeeController")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询所有员工信息
     * @param model
     * @return employeeList.html
     */
    @GetMapping("/findAllEmployee")
    public String findAllEmployee(Model model) {
        List<Employee> employees = employeeService.findAllEmployee();
        model.addAttribute("employees", employees);
        return "/employeeList";
    }

    /**
     * 添加员工
     * @param employee 待添加员工
     * @return 员工信息列表
     */
    @PostMapping("/AddEmployee")
    public String addEmployee(Employee employee) {
        employeeService.addEmployee(employee);
        // 跳转到当前控制器findAllEmployee，由控制器去访问模板
        return "redirect:/EmployeeController/findAllEmployee";
    }

    /**
     * 删除员工信息
     * @param id 待删除员工id
     * @return 员工信息列表
     */
    @GetMapping("/DeleteEmployee")
    public String deleteEmployee(String id) {
        employeeService.deleteEmployee(id);
        // 跳转到当前控制器findAllEmployee，由控制器去访问模板
        return "redirect:/EmployeeController/findAllEmployee";
    }

    /**
     * 根据id查找员工
     * @param id 待查找员工id
     * @param model
     * @return updateEmployee.html
     */
    @GetMapping("/FindEmployee")
    public String findEmployee(String id, Model model) {
        Employee employee = employeeService.findEmployee(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    /**
     * 更新员工信息
     * @param employee 待更新员工
     * @return 员工信息列表
     */
    @PostMapping("/Update")
    public String update(Employee employee) {
        employeeService.update(employee);
        return "redirect:/EmployeeController/findAllEmployee";
    }
}
