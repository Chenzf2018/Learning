package com.example.dao;

import com.example.entity.Employee;

import java.util.List;

/**
 * @author Chenzf
 * @date 2020/10/26
 */

public interface EmployeeDAO {
    /**
     * 查询所有员工信息
      * @return 所有员工信息
     */
    List<Employee> findAllEmployee();

    /**
     * 添加员工
     * @param employee 待添加员工
     */
    void addEmployee(Employee employee);

    /**
     * 删除员工信息
     * @param id 待删除员工的id
     */
    void deleteEmployee(String id);

    /**
     * 查询待修改员工
     * @param id 待修改员工id
     * @return 待修改员工
     */
    Employee findEmployee(String id);

    /**
     * 更新员工信息
     * @param employee 待更新员工
     */
    void update(Employee employee);
}
